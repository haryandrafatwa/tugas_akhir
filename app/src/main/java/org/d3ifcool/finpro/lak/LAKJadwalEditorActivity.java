package org.d3ifcool.finpro.lak;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.Message;
import org.d3ifcool.finpro.core.interfaces.DosenContract;
import org.d3ifcool.finpro.core.interfaces.JadwalContract;
import org.d3ifcool.finpro.core.interfaces.PlottingContract;
import org.d3ifcool.finpro.core.mediators.prodi.ConcreteMediator;
import org.d3ifcool.finpro.core.models.Dosen;
import org.d3ifcool.finpro.core.models.JadwalKegiatan;
import org.d3ifcool.finpro.core.models.Plotting;
import org.d3ifcool.finpro.core.presenters.DosenPresenter;
import org.d3ifcool.finpro.databinding.ActivityKoorPlottingTambahBinding;
import org.d3ifcool.finpro.databinding.ActivityLakJadwalEditorBinding;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;

public class LAKJadwalEditorActivity extends AppCompatActivity implements JadwalContract.ViewModel {

    private Message message = new Message();
    private ActivityLakJadwalEditorBinding mBinding;
    private ConcreteMediator mediator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_lak_jadwal_editor);
        mediator = new ConcreteMediator(this);
        mediator.setJadwalPresenter(this);
        mBinding.setPresenter(mediator.getJadwalPresenter());

        setTitle(getString(R.string.button_click_tambah_bimbingan));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
