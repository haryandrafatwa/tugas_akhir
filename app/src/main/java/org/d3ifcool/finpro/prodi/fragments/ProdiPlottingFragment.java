package org.d3ifcool.finpro.prodi.fragments;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import org.apache.commons.io.FileUtils;
import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.interfaces.lists.PlottingListView;
import org.d3ifcool.finpro.core.mediators.interfaces.prodi.ProdiFragmentMediator;
import org.d3ifcool.finpro.core.mediators.prodi.ProdiFragmentConcrete;
import org.d3ifcool.finpro.core.models.Plotting;
import org.d3ifcool.finpro.core.presenters.PlottingPresenter;
import org.d3ifcool.finpro.prodi.activities.editor.create.ProdiPlottingTambahActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

import static android.app.Activity.RESULT_OK;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.FILE_TYPE_XLS;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.FILE_TYPE_XLSX;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.PICK_EXCEL_REQUEST;

public class ProdiPlottingFragment extends Fragment implements PlottingListView {

    private ArrayList<Plotting> arrayList = new ArrayList<>();
    private ProdiFragmentMediator mediator;
    private PlottingPresenter plottingPresenter;
    private Uri docUri;
    private boolean status;

    public ProdiPlottingFragment(){}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_koor_plotting,container,false);

        mediator = new ProdiFragmentConcrete(view);
        mediator.message("ProgressDialog");
        mediator.message("TVStatus");
        mediator.message("TVDownload");
        mediator.message("TVHapus");
        mediator.message("RefreshLayout");
        mediator.message("RecycleView");
        mediator.message("EmptyView");
        mediator.message("ProdiPlottingAdapter");
        mediator.message("FloatingAButton", ProdiPlottingTambahActivity.class);
        mediator.message("UploadFAB");

        plottingPresenter = new PlottingPresenter(this);
        plottingPresenter.initContext(getContext());
        plottingPresenter.getPlotting();
        plottingPresenter.checkFormPlot();

        mediator.getRefreshLayout().setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                plottingPresenter.getPlotting();
                plottingPresenter.checkFormPlot();
            }
        });

        mediator.getUploadFAB().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (status){
                    new AlertDialog
                            .Builder(getActivity())
                            .setTitle(R.string.dialog_upload_form_plot)
                            .setMessage(R.string.dialog_upload_text)
                            .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    showProgress();
                                    intentPickFile();
                                }
                            })

                            .setNegativeButton("Batal", null)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }else{
                    intentPickFile();
                }
            }
        });

        mediator.getTVDownload().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (status){
                    showProgress();
                    plottingPresenter.downloadFormPlot();
                }
            }
        });

        mediator.getTVHapus().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        new AlertDialog.Builder(getActivity())
                        .setTitle(R.string.dialog_hapus_form_plot)
                        .setMessage(R.string.dialog_hapus_form_plot_text)
                        .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                showProgress();
                                plottingPresenter.deleteFormPlot();
                            }
                        })

                        .setNegativeButton("Batal", null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

        return view;
    }

    private void intentPickFile(){
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            } else {
                Intent intent = new Intent();
                intent.setType(FILE_TYPE_XLS);
                String[] mimetypes = {FILE_TYPE_XLS,FILE_TYPE_XLSX};
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes);
                startActivityForResult(Intent.createChooser(intent, "Pilih file"), PICK_EXCEL_REQUEST);
            }
        }
    }

    private File copyToTempFile(Uri uri, File tempFile) throws IOException {
        InputStream inputStream = getActivity().getContentResolver().openInputStream(uri);
        if (inputStream == null) {
            throw new IOException("Unable to obtain input stream from URI");
        }
        FileUtils.copyInputStreamToFile(inputStream, tempFile);
        return tempFile;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_EXCEL_REQUEST && resultCode == RESULT_OK) {
            docUri = data.getData();
            File file = new File(docUri.getPath());
            String mimeType = getActivity().getContentResolver().getType(docUri);
            Log.e("TAG", "onActivityResult: "+mimeType+": "+file.getName());
            File outputDir = getActivity().getCacheDir(); // context being the Activity pointer
            File outputFile = null;
            try {
                outputFile = File.createTempFile("form","xlsx", outputDir);
                File fileCopy = copyToTempFile(docUri, outputFile);
                RequestBody requestBody = RequestBody.create(MediaType.parse(mimeType),fileCopy);
                String fileName = "form_plotting_pembimbing."+mimeType;
                MultipartBody.Part part = MultipartBody.Part.createFormData("file",fileName,requestBody);
                plottingPresenter.uploadFormPlotting(part);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        plottingPresenter.getPlotting();
    }

    @Override
    public void showProgress() {
        mediator.getProgressDialog().show();
    }

    @Override
    public void hideProgress() {
        mediator.getProgressDialog().dismiss();
    }

    @Override
    public void onGetListPlotting(List<Plotting> plotting) {

        arrayList.clear();
        arrayList.addAll(plotting);

        mediator.getKoorPlottingAdapter().addItem(arrayList);
        mediator.getRecycleView().setAdapter(mediator.getKoorPlottingAdapter());
        mediator.getRefreshLayout().setRefreshing(false);

        if (arrayList.size() == 0) {
            mediator.getView().setVisibility(View.VISIBLE);
        } else {
            mediator.getView().setVisibility(View.GONE);
        }

    }

    @Override
    public void onGetBody(ResponseBody body, String filename) {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + File.separator + filename);
        if (!file.exists()){
            boolean writtenToDisk = writeResponseBodyToDisk(body,filename);
            if (writtenToDisk)
                Log.e("TAG", "onGetBody: Hasil Download");
        }
        Intent target = new Intent(Intent.ACTION_VIEW);
        target.setDataAndType(FileProvider.getUriForFile(getActivity(),getActivity().getApplicationContext().getPackageName()+".fileprovider",file),FILE_TYPE_XLS);
        target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        target.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        startActivity(Intent.createChooser(target,"Open File"));
    }

    @Override
    public void isEmptyListPlotting() {
        mediator.getView().setVisibility(View.VISIBLE);
    }

    @Override
    public void onFailed(String message) {
        if (message.equalsIgnoreCase("File ditemukan.")){
            mediator.getTVHapus().setVisibility(View.VISIBLE);
            mediator.getTVStatus().setText(R.string.text_telah_diunggah);
            mediator.getTVStatus().setTextColor(getResources().getColor(R.color.colorTextGreen));
            mediator.getTVDownload().setTextColor(getResources().getColor(R.color.colorAccent));
            this.status = true;
        }else if (message.equalsIgnoreCase("File tidak ditemukan.")){
            mediator.getTVStatus().setText(R.string.text_belum_diunggah);
            mediator.getTVStatus().setTextColor(getResources().getColor(R.color.colorTextRed));
            mediator.getTVDownload().setTextColor(getResources().getColor(R.color.colorBackgroundDark));
            this.status = false;
        }else{
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.framelayout_container, new ProdiInformasiFragment()).addToBackStack("ProdiInformasiFragment")
                    .commit();
            getActivity().getSupportFragmentManager().popBackStack("ProdiInformasiFragment",FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    private boolean writeResponseBodyToDisk(ResponseBody body, String filename) {
        try {
            // todo change the file location/name according to your needs
//            File form = new File(Environment.getExternalStorageDirectory() +File.separator + "Download" + File.separator + filename);
            File form = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + File.separator + filename);

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[2048000];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(form);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;

                    Log.d("TAG", "file download: " + fileSizeDownloaded + " of " + fileSize);
                }

                outputStream.flush();

                return true;
            } catch (IOException e) {
                Log.e("TAG", "Error: "+e.getLocalizedMessage());
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }
}
