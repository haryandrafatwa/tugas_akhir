package org.d3ifcool.finpro.prodi.activities.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.apache.commons.io.FileUtils;

import org.d3ifcool.finpro.core.interfaces.MahasiswaContract;
import org.d3ifcool.finpro.core.mediators.interfaces.prodi.ProdiDetailActivityMediator;
import org.d3ifcool.finpro.core.mediators.interfaces.prodi.ProdiMediator;
import org.d3ifcool.finpro.core.mediators.prodi.ProdiConcrete;
import org.d3ifcool.finpro.core.mediators.prodi.ProdiDetailActivityConcrete;
import org.d3ifcool.finpro.core.models.Mahasiswa;
import org.d3ifcool.finpro.core.models.Plotting;
import org.d3ifcool.finpro.core.presenters.MahasiswaPresenter;
import org.d3ifcool.finpro.databinding.ActivityProdiMahasiswaDetailBinding;
import org.d3ifcool.finpro.prodi.activities.editor.update.KoorMahasiswaUbahActivity;
import org.d3ifcool.finpro.R;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import es.dmoral.toasty.Toasty;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static org.d3ifcool.finpro.core.api.ApiUrl.FinproUrl.URL_FOTO_MAHASISWA;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.FILE_TYPE_PDF;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.PICK_PDF_REQUEST;

public class ProdiMahasiswaDetailActivity extends AppCompatActivity implements MahasiswaContract.ViewModel {

    public static final String EXTRA_MAHASISWA = "extra_mahasiswa";

    private ActivityProdiMahasiswaDetailBinding binding;
    private MahasiswaPresenter mahasiswaPresenter;
    private ProdiConcrete mediator;

    private Mahasiswa extraMahasiswa;
    private String displayName,size;
    private Uri docUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_prodi_mahasiswa_detail);
        mahasiswaPresenter = new MahasiswaPresenter(this);

        setTitle(getString(R.string.title_mahasiswa_detail));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0f);

        extraMahasiswa = getIntent().getParcelableExtra(EXTRA_MAHASISWA);
        binding.setModel(extraMahasiswa);

        if (extraMahasiswa.getSk_status() == 1 || extraMahasiswa.getSk_status() == 3){
            binding.actKoorProfilStatusSk.setTextColor(Color.RED);
        }else{
            if(extraMahasiswa.getSk_status() == 2){
                binding.actKoorProfilStatusSk.setTextColor(getResources().getColor(R.color.colorTextGreen));
            }else{
                binding.actKoorProfilStatusSk.setTextColor(getResources().getColor(R.color.colorAccent));
            }
        }

        mediator = new ProdiConcrete(this);
        mediator.message("ProgressDialog","set");

        mahasiswaPresenter.getPembimbing(extraMahasiswa.getPlot_id());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_delete,menu);
        return super.onCreateOptionsMenu(menu);
    }

    private File copyToTempFile(Uri uri, File tempFile) throws IOException {
        InputStream inputStream = getContentResolver().openInputStream(uri);
        if (inputStream == null) {
            throw new IOException("Unable to obtain input stream from URI");
        }
        FileUtils.copyInputStreamToFile(inputStream, tempFile);
        return tempFile;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_PDF_REQUEST && resultCode == RESULT_OK) {
            docUri = data.getData();
            File file = new File(docUri.getPath());
            String mimeType = getContentResolver().getType(docUri);
            Log.e("TAG", "onActivityResult: "+mimeType+": "+file.getName());
            File outputDir = getCacheDir(); // context being the Activity pointer
            File outputFile = null;
            try {
                outputFile = File.createTempFile("skta","pdf", outputDir);
                File fileCopy = copyToTempFile(docUri, outputFile);
                RequestBody requestBody = RequestBody.create(MediaType.parse(mimeType),fileCopy);
                String fileName = extraMahasiswa.getMhs_nim()+"_skta.pdf";
                MultipartBody.Part part = MultipartBody.Part.createFormData("file",fileName,requestBody);
                mahasiswaPresenter.updateSKTA(extraMahasiswa.getMhs_nim(),part);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int i = item.getItemId();
        if (i == android.R.id.home) {
            finish();

        } else if (i == R.id.toolbar_menu_ubah) {
            Intent intent = new Intent(this, KoorMahasiswaUbahActivity.class);
            intent.putExtra(KoorMahasiswaUbahActivity.EXTRA_MAHASISWA, extraMahasiswa);
            startActivity(intent);
            finish();
        } else if (i == R.id.toolbar_menu_hapus) {
            new AlertDialog
                    .Builder(this)
                    .setTitle(getString(R.string.dialog_hapus_title))
                    .setMessage(getString(R.string.dialog_hapus_text))

                    .setPositiveButton(R.string.iya, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Continue with delete operation
                            mahasiswaPresenter.deleteMahasiswa(extraMahasiswa.getMhs_nim());
                        }
                    })

                    .setNegativeButton(R.string.tidak, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
        return super.onOptionsItemSelected(item);
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
    public void onGetObjectMahasiswa(Mahasiswa mahasiswa) {

    }

    @Override
    public void isEmptyObjectMahasiswa() {

    }

    @Override
    public void onGetListMahasiswa(List<Mahasiswa> mahasiswaList) {

    }

    @Override
    public void isEmptyListMahasiswa() {

    }

    @Override
    public void onSuccess() {
        finish();
    }

    @Override
    public void onSuccessGetPlotting(Plotting plotting) {
        binding.setPlot(plotting);
    }

    @Override
    public void onFailed(String message) {
        Toasty.error(this, message, Toasty.LENGTH_SHORT).show();
    }

    @Override
    public void btnSKUpdate() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            } else {
                Intent intent = new Intent();
                intent.setType(FILE_TYPE_PDF);
                String[] mimetypes = {FILE_TYPE_PDF};
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes);
                startActivityForResult(Intent.createChooser(intent, "Pilih file"), PICK_PDF_REQUEST);
            }
        }
    }
}
