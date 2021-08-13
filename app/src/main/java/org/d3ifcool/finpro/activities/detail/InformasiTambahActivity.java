package org.d3ifcool.finpro.activities.detail;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.Constant;
import org.d3ifcool.finpro.core.helpers.Message;
import org.d3ifcool.finpro.core.interfaces.InformasiContract;
import org.d3ifcool.finpro.core.interfaces.NotifikasiContract;
import org.d3ifcool.finpro.core.mediators.Mediator;
import org.d3ifcool.finpro.core.mediators.ConcreteMediator;
import org.d3ifcool.finpro.core.models.Informasi;
import org.d3ifcool.finpro.core.models.Notifikasi;
import org.d3ifcool.finpro.core.presenters.InformasiPresenter;
import org.d3ifcool.finpro.core.presenters.NotifikasiPresenter;
import org.d3ifcool.finpro.databinding.ActivityKoorInformasiTambahBinding;

import java.util.List;

import es.dmoral.toasty.Toasty;

import static org.d3ifcool.finpro.core.helpers.ConstantNotif.ConstantaNotif.NOTIF_KATEGORI_INFORMASI;
import static org.d3ifcool.finpro.core.helpers.ConstantNotif.ConstantaNotif.UNTUK_SEMUA;

public class InformasiTambahActivity extends AppCompatActivity implements InformasiContract.ViewModel, NotifikasiContract.ViewModel {

    private InformasiPresenter informasiPresenter;
    private NotifikasiPresenter notifikasiPresenter;

    private Mediator mediator;
    private ActivityKoorInformasiTambahBinding binding;
    private Message message = new Message();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_koor_informasi_tambah);
        informasiPresenter = new InformasiPresenter(this);
        binding.setPresenter(informasiPresenter);

        notifikasiPresenter = new NotifikasiPresenter(this);
        notifikasiPresenter.initContext(this);

        mediator = new ConcreteMediator(this);

        mediator.message(message.setComponent("SessionManager").setEvent("set"));
        mediator.message(message.setComponent("ProgressDialog").setEvent("set"));
        binding.setToken(mediator.getSessionManager().getSessionToken());

        if (mediator.getSessionManager().getSessionPengguna().equalsIgnoreCase(Constant.ObjectConstanta.ROLE_DOSEN)){
            informasiPresenter.setPenerbit(mediator.getSessionManager().getSessionDosenNama());
        }else{
            informasiPresenter.setPenerbit(mediator.getSessionManager().getSessionKoorNama());
        }


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
    public void onGetListNotifikasi(List<Notifikasi> notifikasiList) {

    }

    @Override
    public void isEmptyListNotifikasi() {

    }

    @Override
    public void onGetObjectNotifikasi(Notifikasi notifikasi) {

    }

    @Override
    public void isEmptyObjectNotifikasi() {

    }

    @Override
    public void onMessage(String messages) {
        switch (messages){
            case "ShowProgressDialog":
                mediator.message(message.setComponent("ProgressDialog").setEvent("show"));
                break;
            case "HideProgressDialog":
                mediator.message(message.setComponent("ProgressDialog").setEvent("dismiss"));
                break;
            case "onSuccess":
                if (mediator.getSessionManager().getSessionPengguna().equalsIgnoreCase(Constant.ObjectConstanta.ROLE_DOSEN)){
                    notifikasiPresenter.createNotifikasi(mediator.getSessionManager().getSessionToken(),NOTIF_KATEGORI_INFORMASI(mediator.getSessionManager().getSessionDosenNama()),
                            binding.actKoorEdittextJudul.getText().toString(), mediator.getSessionManager().getSessionDosenNama(), UNTUK_SEMUA);
                }else{
                    notifikasiPresenter.createNotifikasi(mediator.getSessionManager().getSessionToken(),NOTIF_KATEGORI_INFORMASI(mediator.getSessionManager().getSessionKoorNama()),
                            binding.actKoorEdittextJudul.getText().toString(), mediator.getSessionManager().getSessionKoorNama(), UNTUK_SEMUA);
                }
                break;
            case "onSuccessCreateNotif":
                finish();
                finish();
                break;
            default:
                mediator.message(message.setComponent("Toasty").setEvent("Warning").setText(messages));
                break;
        }
    }
}
