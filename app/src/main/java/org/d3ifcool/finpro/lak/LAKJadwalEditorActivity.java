package org.d3ifcool.finpro.lak;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.Message;
import org.d3ifcool.finpro.core.interfaces.JadwalContract;
import org.d3ifcool.finpro.core.mediators.Mediator;
import org.d3ifcool.finpro.core.mediators.ConcreteMediator;
import org.d3ifcool.finpro.core.models.JadwalKegiatan;
import org.d3ifcool.finpro.databinding.ActivityLakJadwalEditorBinding;

import java.util.List;

public class LAKJadwalEditorActivity extends AppCompatActivity implements JadwalContract.ViewModel {

    private Message message = new Message();
    private ActivityLakJadwalEditorBinding mBinding;
    private Mediator mediator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_lak_jadwal_editor);
        mediator = new ConcreteMediator(this);
        mediator.setJadwalPresenter(this);
        mBinding.setPresenter(mediator.getJadwalPresenter());

        mediator.setTitleContextWithHomeAsUp("Tambah Jadwal Kegiatan");

        mediator.message(message.setComponent("ProgressDialog").setEvent("set"));
        mediator.message(message.setComponent("SessionManager").setEvent("set"));
        mediator.message(message.setComponent("MethodHelper").setEvent("set"));

        mediator.setTextView(mBinding.etTanggalMulai);
        mediator.message(message.setComponent("MethodHelper").setEvent("secondDataPicker"));
        mediator.setTextView(mBinding.etTanggalAkhir);
        mediator.message(message.setComponent("MethodHelper").setEvent("secondDataPicker"));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mediator.message(message.setComponent("Toolbar").setVisibility(item.getItemId()));
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onGetListJadwal(List<JadwalKegiatan> kegiatanList) {

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
            case "buttonCreate":
                mediator.message(message.setComponent("JadwalPresenter").setEvent("createJadwal"));
                break;
            case"onSuccess":
                finish();
                break;
            default:
                mediator.message(message.setComponent("Toasty").setEvent("Warning").setText(messages));
                break;
        }
    }

}
