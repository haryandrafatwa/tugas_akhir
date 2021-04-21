package org.d3ifcool.finpro.prodi.activities.detail;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import org.apache.commons.io.FileUtils;

import com.squareup.picasso.Picasso;

import org.d3ifcool.finpro.core.helpers.Constant;
import org.d3ifcool.finpro.core.interfaces.works.MahasiswaWorkView;
import org.d3ifcool.finpro.core.mediators.interfaces.prodi.ProdiDetailActivityMediator;
import org.d3ifcool.finpro.core.mediators.prodi.ProdiDetailActivityConcrete;
import org.d3ifcool.finpro.core.models.Mahasiswa;
import org.d3ifcool.finpro.core.models.Plotting;
import org.d3ifcool.finpro.core.presenters.MahasiswaPresenter;
import org.d3ifcool.finpro.prodi.activities.editor.update.KoorMahasiswaUbahActivity;
import org.d3ifcool.finpro.R;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static org.d3ifcool.finpro.core.api.ApiUrl.FinproUrl.URL_FOTO_MAHASISWA;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.FILE_TYPE_PDF;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.PICK_PDF_REQUEST;

public class KoorMahasiswaDetailActivity extends AppCompatActivity implements MahasiswaWorkView {

    public static final String EXTRA_MAHASISWA = "extra_mahasiswa";

    private Mahasiswa extraMahasiswa;
    private MahasiswaPresenter mahasiswaPresenter;
    private ProdiDetailActivityMediator mediator;
    private String displayName,size;
    private Uri docUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koor_mahasiswa_detail);

        setTitle(getString(R.string.title_mahasiswa_detail));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0f);

        mahasiswaPresenter = new MahasiswaPresenter(this);
        mahasiswaPresenter.initContext(this);

        mediator = new ProdiDetailActivityConcrete(this);
        mediator.message("ProgressDialog");
        mediator.Notify(R.id.act_koor_profil_foto);
        mediator.Notify(R.id.act_koor_profil_nama);
        mediator.Notify(R.id.act_koor_profil_nim);
        mediator.Notify(R.id.act_koor_profil_angkatan_mhs);
        mediator.Notify(R.id.act_koor_profil_email);
        mediator.Notify(R.id.act_koor_profil_kontak);
        mediator.Notify(R.id.act_koor_profil_status_sk);
        mediator.message("SetMahasiswa");
        mediator.Notify(R.id.tv_change_pembimbing);
        mediator.Notify(R.id.btn_update_status_sk);
        mediator.Notify(R.id.btn_plot_pembimbing);

        mahasiswaPresenter.getPembimbing(mediator.getMahasiswa().getPlot_id());
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
                String fileName = mediator.getMahasiswa().getMhs_nim()+"_skta.pdf";
                MultipartBody.Part part = MultipartBody.Part.createFormData("file",fileName,requestBody);
                mahasiswaPresenter.updateSKTA(mediator.getMahasiswa().getMhs_nim(),part);
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
            intent.putExtra(KoorMahasiswaUbahActivity.EXTRA_MAHASISWA, mediator.getMahasiswa());
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
    public void onSucces() {
        finish();
    }

    @Override
    public void onSuccesGetPlotting(Plotting plotting) {
        mediator.setPlotting(plotting);
        mediator.message("SetPlotting");
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
