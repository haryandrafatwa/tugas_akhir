package org.d3ifcool.finpro.dosen.activities.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.Message;
import org.d3ifcool.finpro.core.interfaces.BimbinganContract;
import org.d3ifcool.finpro.core.mediators.Mediator;
import org.d3ifcool.finpro.core.mediators.ConcreteMediator;
import org.d3ifcool.finpro.core.models.Bimbingan;
import org.d3ifcool.finpro.core.models.Dosen;
import org.d3ifcool.finpro.core.models.Mahasiswa;
import org.d3ifcool.finpro.databinding.ActivityDosenBimbinganDetailBinding;

import java.util.List;

import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.EXTRA_BIMBINGAN;

public class BimbinganDetailActivity extends AppCompatActivity implements BimbinganContract.ViewModel{

    private ActivityDosenBimbinganDetailBinding mBinding;
    private Message message = new Message();
    private Mediator mediator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_dosen_bimbingan_detail);
        mBinding.setLifecycleOwner(this);
        mediator = new ConcreteMediator(this);
        mediator.setBimbinganPresenter(this);
        mBinding.setPresenter(mediator.getBimbinganPresenter());

        setTitle(getString(R.string.title_bimbingan_detail));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mediator.message(message.setComponent("ProgressDialog").setEvent("set"));
        mediator.message(message.setComponent("SessionManager").setEvent("set"));
        message.setBimbingan(getIntent().getParcelableExtra(EXTRA_BIMBINGAN));
        mBinding.setModel(message.getBimbingan());
        if (message.getBimbingan().getBimbingan_status().equalsIgnoreCase("pending")){
            mediator.setFloatingActionButton(mBinding.fabBimbinganAccept);
            mediator.message(message.setComponent("FloatButton").setEvent("setVisibility").setVisibility(View.VISIBLE));
            mediator.setFloatingActionButton(mBinding.fabBimbinganDecline);
            mediator.message(message.setComponent("FloatButton").setEvent("setVisibility").setVisibility(View.VISIBLE));
        }

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
    public void onGetListBimbingan(List<Bimbingan> bimbinganList, Dosen dosen, Mahasiswa mahasiswa) {

    }

    @Override
    public void onGetListMahasiswa(List<Mahasiswa> mahasiswaList) {

    }

    @Override
    public void onMessage(String messages) {
        switch (messages){
            case "onSuccess":
                finish();
                break;
            case "onAccept":
                mediator.message(message.setComponent("BimbinganPresenter").setEvent("acceptBimbingan"));
                break;
            case "onReject":
                mediator.message(message.setComponent("BimbinganPresenter").setEvent("rejectBimbingan"));
                break;
            case "ShowProgressDialog":
                mediator.message(message.setComponent("ProgressDialog").setEvent("show"));
                break;
            case "HideProgressDialog":
                mediator.message(message.setComponent("ProgressDialog").setEvent("dismiss"));
                break;
            default:
                mediator.message(message.setComponent("Toasty").setEvent("Warning").setText(messages));
                break;
        }
    }
}
