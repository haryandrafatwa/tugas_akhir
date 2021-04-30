package org.d3ifcool.finpro.prodi.activities.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static org.d3ifcool.finpro.core.api.ApiUrl.FinproUrl.URL_FOTO_MAHASISWA;
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

        setTitle(getString(R.string.title_mahasiswa_detail));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0f);

        mahasiswaPresenter = new MahasiswaPresenter(this);

        extraMahasiswa = getIntent().getParcelableExtra(EXTRA_MAHASISWA);
        binding.actKoorProfilNama.setText(extraMahasiswa.getMhs_nama());
        binding.actKoorProfilNim.setText(extraMahasiswa.getMhs_nim());
        if (extraMahasiswa.getMhs_email() == null){
            binding.actKoorProfilEmail.setText("-");
        }else{
            binding.actKoorProfilEmail.setText(extraMahasiswa.getMhs_email());
        }
        if (extraMahasiswa.getMhs_kontak() == null){
            binding.actKoorProfilKontak.setText("-");
        }else{
            binding.actKoorProfilKontak.setText(extraMahasiswa.getMhs_kontak());
        }
        if (extraMahasiswa.getSk_status() == 1){
            binding.actKoorProfilStatusSk.setText(R.string.status_sk_pasif);
            binding.actKoorProfilStatusSk.setTextColor(Color.RED);
        }else{
            Locale locale = new Locale("in", "ID");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", locale);
            try {
                Date date = format.parse(extraMahasiswa.getSk_expired());
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                String month_name = new SimpleDateFormat("MMMM", locale).format(calendar.getTime());
                String tempJudul = calendar.get(Calendar.DATE)+" "+month_name+" "+calendar.get(Calendar.YEAR);
                if(extraMahasiswa.getSk_status() == 2){
                    binding.actKoorProfilStatusSk.setText(getResources().getString(R.string.status_sk_aktif)+" "+tempJudul);
                    binding.actKoorProfilStatusSk.setTextColor(getResources().getColor(R.color.colorTextGreen));
                }else if(extraMahasiswa.getSk_status() == 3){
                    binding.actKoorProfilStatusSk.setText(getResources().getString(R.string.status_sk_kadaluwarsa)+" "+tempJudul+")");
                    binding.actKoorProfilStatusSk.setTextColor(Color.RED);
                }else{
                    binding.actKoorProfilStatusSk.setText(R.string.status_sk_perpanjang);
                    binding.actKoorProfilStatusSk.setTextColor(getResources().getColor(R.color.colorAccent));
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (extraMahasiswa.getPlot_id() > 0 ){
            binding.tvChangePembimbing.setVisibility(View.VISIBLE);
        }else{
            binding.tvChangePembimbing.setVisibility(View.GONE);
        }

        binding.actKoorProfilAngkatanMhs.setText(extraMahasiswa.getAngkatan());
        Picasso.get().load(URL_FOTO_MAHASISWA + extraMahasiswa.getMhs_foto()).into(binding.actKoorProfilFoto);

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
        if (extraMahasiswa.getPlot_id() > 0){
            binding.listPembimbing.setVisibility(View.VISIBLE);
            binding.linearLayout.setVisibility(View.GONE);
            binding.tvNamaPembimbing1.setText(plotting.getNama_pembimbing_1());
            binding.tvNamaPembimbing2.setText(plotting.getNama_pembimbing_2());
            binding.tvNipPembimbing1.setText(plotting.getNip_pembimbing_1());
            binding.tvNipPembimbing2.setText(plotting.getNip_pembimbing_2());
        }else{
            binding.listPembimbing.setVisibility(View.GONE);
            binding.linearLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
