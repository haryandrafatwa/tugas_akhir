package org.d3ifcool.finpro.prodi.activities.editor.create;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.d3ifcool.finpro.core.interfaces.works.InformasiWorkView;
import org.d3ifcool.finpro.core.interfaces.works.NotifikasiWorkView;
import org.d3ifcool.finpro.core.mediators.interfaces.prodi.ProdiActivityMediator;
import org.d3ifcool.finpro.core.mediators.prodi.ProdiActivityConcrete;
import org.d3ifcool.finpro.core.presenters.InformasiPresenter;
import org.d3ifcool.finpro.core.presenters.NotifikasiPresenter;
import org.d3ifcool.finpro.R;

import static org.d3ifcool.finpro.core.helpers.ConstantNotif.ConstantaNotif.NOTIF_KATEGORI_INFORMASI;
import static org.d3ifcool.finpro.core.helpers.ConstantNotif.ConstantaNotif.UNTUK_SEMUA;

public class ProdiInformasiTambahActivity extends AppCompatActivity implements InformasiWorkView, NotifikasiWorkView {

    private InformasiPresenter informasiPresenter;
    private NotifikasiPresenter notifikasiPresenter;
    private String et_judul, et_deskripsi;

    private ProdiActivityMediator prodiActivityMediator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koor_informasi_tambah);

        prodiActivityMediator = new ProdiActivityConcrete(this);

        prodiActivityMediator.message("SessionManager");
        prodiActivityMediator.message("ProgressDialog");

        prodiActivityMediator.Notify(R.id.act_koor_edittext_judul);
        prodiActivityMediator.Notify(R.id.act_koor_edittext_deskripsi);
        prodiActivityMediator.Notify(R.id.act_koor_info_button_simpan);

        informasiPresenter = new InformasiPresenter(this);
        notifikasiPresenter = new NotifikasiPresenter(this);

        informasiPresenter.initContext(this);
        notifikasiPresenter.initContext(this);

        setTitle(getString(R.string.title_informasi_tambah));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        prodiActivityMediator.getButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_judul = prodiActivityMediator.getETJudul().getText().toString();
                et_deskripsi = prodiActivityMediator.getETDeskripsi().getText().toString();
                if (et_judul.isEmpty()){
                    prodiActivityMediator.getETJudul().setError("judul tidak boleh kosong");
                }else if (et_deskripsi.isEmpty()){
                    prodiActivityMediator.getETDeskripsi().setError("deskripsi tidak boleh kosong");
                }else{
                    informasiPresenter.createInformasi(et_judul, et_deskripsi, prodiActivityMediator.getSessionManager().getSessionKoorNama());
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProgress() {
        prodiActivityMediator.getProgressDialog().show();
    }

    @Override
    public void hideProgress() {
        prodiActivityMediator.getProgressDialog().dismiss();
    }

    @Override
    public void onSuccesCreateNotifikasi() {
        finish();
    }

    @Override
    public void onSucces() {
        notifikasiPresenter.createNotifikasi(prodiActivityMediator.getSessionManager().getSessionToken(),NOTIF_KATEGORI_INFORMASI(prodiActivityMediator.getSessionManager().getSessionKoorNama()),
                et_judul, prodiActivityMediator.getSessionManager().getSessionKoorNama(), UNTUK_SEMUA);
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
