package org.d3ifcool.finpro.dosen.activities.editor.create;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.d3ifcool.finpro.core.interfaces.InformasiContract;
import org.d3ifcool.finpro.core.interfaces.works.NotifikasiWorkView;
import org.d3ifcool.finpro.core.models.Informasi;
import org.d3ifcool.finpro.core.presenters.NotifikasiPresenter;
import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.SessionManager;
import org.d3ifcool.finpro.core.interfaces.works.InformasiWorkView;
import org.d3ifcool.finpro.core.presenters.InformasiPresenter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import es.dmoral.toasty.Toasty;

import static org.d3ifcool.finpro.core.helpers.ConstantNotif.ConstantaNotif.NOTIF_KATEGORI_INFORMASI;
import static org.d3ifcool.finpro.core.helpers.ConstantNotif.ConstantaNotif.UNTUK_SEMUA;

public class DosenInformasiTambahActivity extends AppCompatActivity implements InformasiContract.ViewModel, NotifikasiWorkView {

    private ProgressDialog progressDialog;
    private InformasiPresenter informasiPresenter;
    private SessionManager sessionManager;
    private NotifikasiPresenter notifikasiPresenter;
    private String text_info_judul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_informasi_tambah);

        sessionManager = new SessionManager(this);
        progressDialog = new ProgressDialog(this);
        informasiPresenter = new InformasiPresenter(this);

        notifikasiPresenter = new NotifikasiPresenter(this);

        setTitle(R.string.title_informasi_tambah);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final EditText info_judul = findViewById(R.id.act_dsn_edittext_judul);
        final EditText info_deskripsi = findViewById(R.id.act_dsn_edittext_deskripsi);
        Button btn_simpan = findViewById(R.id.act_dsn_info_button_simpan);

        progressDialog.setMessage(getString(R.string.text_progress_dialog));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onGetObjectInformasi(Informasi informasi) {

    }

    @Override
    public void onGetListInformasi(List<Informasi> informasiList) {

    }

    @Override
    public void onMessage(String message) {
        switch (message){
            case "ShowProgressDialog":
                progressDialog.show();
                break;
            case "HideProgressDialog":
                progressDialog.dismiss();
                break;
            case "onSuccess":
                notifikasiPresenter.createNotifikasi(sessionManager.getSessionToken(),NOTIF_KATEGORI_INFORMASI(sessionManager.getSessionDosenNama()), text_info_judul, sessionManager.getSessionDosenNama(), UNTUK_SEMUA);
                break;
            case "onSuccessCreateNotif":
                finish();
                break;
            default:
                Toasty.error(this, message, Toasty.LENGTH_SHORT).show();
                break;
        }
    }
}