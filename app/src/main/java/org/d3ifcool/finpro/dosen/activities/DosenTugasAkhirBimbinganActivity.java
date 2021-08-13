package org.d3ifcool.finpro.dosen.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import org.d3ifcool.finpro.core.helpers.Message;
import org.d3ifcool.finpro.core.interfaces.BimbinganContract;
import org.d3ifcool.finpro.core.mediators.Mediator;
import org.d3ifcool.finpro.core.mediators.ConcreteMediator;
import org.d3ifcool.finpro.core.models.Bimbingan;
import org.d3ifcool.finpro.core.models.Dosen;
import org.d3ifcool.finpro.core.models.Mahasiswa;
import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.databinding.ActivityDosenTugasAkhirBimbinganBinding;
import org.d3ifcool.finpro.mahasiswa.activities.editor.MahasiswaBimbinganEditorActivity;

import java.util.ArrayList;
import java.util.List;

import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.EXTRA_DEFAULT;

public class DosenTugasAkhirBimbinganActivity extends AppCompatActivity implements BimbinganContract.ViewModel {

    private ActivityDosenTugasAkhirBimbinganBinding mBinding;
    private Message message = new Message();
    private Mediator mediator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_dosen_tugas_akhir_bimbingan);
        mBinding.setLifecycleOwner(this);
        mediator = new ConcreteMediator(this);
        mediator.setBimbinganPresenter(this);
        mBinding.setPresenter(mediator.getBimbinganPresenter());

        setTitle(getString(R.string.title_bimbingan));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mediator.message(message.setComponent("ProgressDialog").setEvent("set"));
        mediator.message(message.setComponent("SessionManager").setEvent("set"));
        mediator.message(message.setComponent("DosenBimbinganViewAdapter").setEvent("set"));

        mediator.setRecyclerView(mBinding.actMhsPaBimbinganDetailRecyclerview);
        mediator.setRefreshLayout(mBinding.refresh);
        mBinding.setToken(mediator.getSessionToken());
        mBinding.setUsername(getIntent().getStringExtra(EXTRA_DEFAULT));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mediator.message(message.setComponent("BimbinganPresenter").setEvent("getBimbinganByUsername").setText(getIntent().getStringExtra(EXTRA_DEFAULT)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mediator.message(message.setComponent("Toolbar").setVisibility(item.getItemId()).setEvent("bimbingan"));
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onGetListBimbingan(List<Bimbingan> bimbinganList, Dosen dosen, Mahasiswa mahasiswa) {
        ArrayList<Bimbingan> arrayList= new ArrayList<>();
        arrayList.clear();
        arrayList.addAll(bimbinganList);
        mediator.message(message.setComponent("DosenBimbinganViewAdapter").setEvent("addItem").setItem(arrayList));
        mediator.message(message.setComponent("DosenBimbinganViewAdapter").setEvent("setAdapter"));
        mediator.message(message.setComponent("RefreshLayout").setEvent("setRefreshing").setEnabled(false));
        mediator.setRelativeLayout(mBinding.includeLayout.viewEmptyview);
        if (arrayList.size() == 0) {
            mediator.message(message.setComponent("RelativeLayout").setEvent("setVisibility").setVisibility(View.VISIBLE));
        } else {
            mediator.message(message.setComponent("RelativeLayout").setEvent("setVisibility").setVisibility(View.GONE));
        }
        mBinding.setModel(mahasiswa);
    }

    @Override
    public void onGetListMahasiswa(List<Mahasiswa> mahasiswaList) {

    }

    @Override
    public void onMessage(String messages) {
        switch (messages){
            case "onSuccess":
                onResume();
                break;
            case "onCreate":
                mediator.selectIntent(message.setaClass(MahasiswaBimbinganEditorActivity.class).setExtraIntent(mBinding.getUsername()));
                break;
            case "ShowProgressDialog":
                mediator.message(message.setComponent("ProgressDialog").setEvent("show"));
                break;
            case "HideProgressDialog":
                mediator.message(message.setComponent("ProgressDialog").setEvent("dismiss"));
                break;
            case "EmptyList":
                mediator.message(message.setComponent("RelativeLayout").setEvent("setVisibility").setVisibility(View.GONE));
                break;
            default:
                mediator.message(message.setComponent("Toasty").setEvent("Warning").setText(messages));
                break;
        }
    }
}
