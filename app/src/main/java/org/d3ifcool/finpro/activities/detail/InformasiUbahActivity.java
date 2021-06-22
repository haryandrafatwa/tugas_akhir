package org.d3ifcool.finpro.activities.detail;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.Message;
import org.d3ifcool.finpro.core.interfaces.InformasiContract;
import org.d3ifcool.finpro.core.mediators.interfaces.prodi.Mediator;
import org.d3ifcool.finpro.core.mediators.prodi.ConcreteMediator;
import org.d3ifcool.finpro.core.models.Informasi;
import org.d3ifcool.finpro.core.presenters.InformasiPresenter;
import org.d3ifcool.finpro.databinding.ActivityKoorInformasiUbahBinding;

import java.util.List;

import es.dmoral.toasty.Toasty;

public class InformasiUbahActivity extends AppCompatActivity implements InformasiContract.ViewModel {

    private InformasiPresenter informasiPresenter;
    private ActivityKoorInformasiUbahBinding mBinding;
    private Mediator mediator;
    private Message message = new Message();

    public static final String EXTRA_INFORMASI = "extra_informasi";
    private Informasi extraInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_koor_informasi_ubah);
        informasiPresenter = new InformasiPresenter(this);
        mBinding.setPresenter(informasiPresenter);

        setTitle(getString(R.string.title_informasi_ubah));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        extraInfo = getIntent().getParcelableExtra(EXTRA_INFORMASI);
        informasiPresenter.setJudul(extraInfo.getInfo_judul());
        informasiPresenter.setDeskripsi(extraInfo.getInfo_deskripsi());

        mediator = new ConcreteMediator(this);
        mediator.message(message.setComponent("SessionManager").setEvent("set"));
        mediator.message(message.setComponent("ProgressDialog").setEvent("set"));

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
    public void onMessage(String messages) {
        switch (messages){
            case "AlertUbah":
                mediator.message(message.setComponent("AlertDialog").setEvent("set"));
                mediator.message(message.setComponent("AlertDialog").setEvent("ubah"));
                mediator.getAlertDialog().setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        informasiPresenter.updateInformasi(mediator.getSessionManager().getSessionToken(),extraInfo.getId());
                    }
                }).show();
                break;
            case "ShowProgressDialog":
                mediator.message(message.setComponent("ProgressDialog").setEvent("show"));
                break;
            case "HideProgressDialog":
                mediator.message(message.setComponent("ProgressDialog").setEvent("dismiss"));
                break;
            case "onSuccess":
                finish();
                break;
            default:
                mediator.message(message.setComponent("Toasty").setEvent("Warning").setText(messages));
                break;
        }
    }
}
