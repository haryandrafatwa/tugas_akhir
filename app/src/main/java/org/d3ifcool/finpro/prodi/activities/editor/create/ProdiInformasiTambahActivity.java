package org.d3ifcool.finpro.prodi.activities.editor.create;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.d3ifcool.finpro.core.interfaces.InformasiContract;
import org.d3ifcool.finpro.core.interfaces.works.InformasiWorkView;
import org.d3ifcool.finpro.core.interfaces.works.NotifikasiWorkView;
import org.d3ifcool.finpro.core.mediators.interfaces.prodi.ProdiActivityMediator;
import org.d3ifcool.finpro.core.mediators.prodi.ProdiActivityConcrete;
import org.d3ifcool.finpro.core.mediators.prodi.ProdiConcrete;
import org.d3ifcool.finpro.core.models.Informasi;
import org.d3ifcool.finpro.core.presenters.InformasiPresenter;
import org.d3ifcool.finpro.core.presenters.NotifikasiPresenter;
import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.databinding.ActivityKoorInformasiTambahBinding;

import java.util.List;

import es.dmoral.toasty.Toasty;

import static org.d3ifcool.finpro.core.helpers.ConstantNotif.ConstantaNotif.NOTIF_KATEGORI_INFORMASI;
import static org.d3ifcool.finpro.core.helpers.ConstantNotif.ConstantaNotif.UNTUK_SEMUA;

public class ProdiInformasiTambahActivity extends AppCompatActivity implements InformasiContract.ViewModel, NotifikasiWorkView {

    private InformasiPresenter informasiPresenter;
    private NotifikasiPresenter notifikasiPresenter;

    private ProdiConcrete mediator;
    private ActivityKoorInformasiTambahBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_koor_informasi_tambah);
        informasiPresenter = new InformasiPresenter(this);
        binding.setPresenter(informasiPresenter);

        notifikasiPresenter = new NotifikasiPresenter(this);
        notifikasiPresenter.initContext(this);

        mediator = new ProdiConcrete(this);

        mediator.message("SessionManager","set");
        mediator.message("ProgressDialog","set");

        informasiPresenter.setPenerbit(mediator.getSessionManager().getSessionKoorNama());

        setTitle("Tambah Informasi");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
    public void onGetObjectInformasi(Informasi informasi) {

    }

    @Override
    public void onGetListInformasi(List<Informasi> informasiList) {

    }

    @Override
    public void onMessage(String message) {
        switch (message){
            case "ShowProgressDialog":
                mediator.getProgressDialog().show();
                break;
            case "HideProgressDialog":
                mediator.getProgressDialog().dismiss();
                break;
            case "onSuccess":
                notifikasiPresenter.createNotifikasi(mediator.getSessionManager().getSessionToken(),NOTIF_KATEGORI_INFORMASI(mediator.getSessionManager().getSessionKoorNama()),
                        binding.actKoorEdittextJudul.getText().toString(), mediator.getSessionManager().getSessionKoorNama(), UNTUK_SEMUA);
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
