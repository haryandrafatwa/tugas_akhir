package org.d3ifcool.finpro.mahasiswa.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.Message;
import org.d3ifcool.finpro.core.interfaces.BimbinganContract;
import org.d3ifcool.finpro.core.mediators.interfaces.prodi.Mediator;
import org.d3ifcool.finpro.core.mediators.prodi.ConcreteMediator;
import org.d3ifcool.finpro.core.models.Mahasiswa;
import org.d3ifcool.finpro.databinding.ActivityMahasiswaBimbinganBinding;
import org.d3ifcool.finpro.mahasiswa.activities.editor.MahasiswaBimbinganEditorActivity;
import org.d3ifcool.finpro.mahasiswa.activities.editor.MahasiswaPaBimbinganTambahActivity;
import org.d3ifcool.finpro.core.helpers.SessionManager;
import org.d3ifcool.finpro.core.models.Bimbingan;
import org.d3ifcool.finpro.mahasiswa.adapters.recyclerview.MahasiswaBimbinganViewAdapter;
import org.d3ifcool.finpro.core.models.Dosen;
import org.d3ifcool.finpro.core.models.ProyekAkhir;
import org.d3ifcool.finpro.core.presenters.BimbinganPresenter;

import java.util.ArrayList;
import java.util.List;

import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.EXTRA_DOSEN;

public class MahasiswaBimbinganActivity extends AppCompatActivity implements BimbinganContract.ViewModel {

    private ActivityMahasiswaBimbinganBinding mBinding;
    private Message message = new Message();
    private Mediator mediator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_mahasiswa_bimbingan);
        mBinding.setLifecycleOwner(this);
        mediator = new ConcreteMediator(this);
        mediator.setBimbinganPresenter(this);
        mBinding.setPresenter(mediator.getBimbinganPresenter());

        setTitle(getString(R.string.title_bimbingan));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mediator.message(message.setComponent("ProgressDialog").setEvent("set"));
        mediator.message(message.setComponent("SessionManager").setEvent("set"));
        mediator.message(message.setComponent("MahasiswaBimbinganViewAdapter").setEvent("set"));

        mediator.setRecyclerView(mBinding.actMhsPaBimbinganDetailRecyclerview);
        mediator.setRelativeLayout(mBinding.includeLayout.viewEmptyview);
        mediator.setRefreshLayout(mBinding.refresh);
        mBinding.setToken(mediator.getSessionToken());
        mBinding.setUsername(getIntent().getStringExtra(EXTRA_DOSEN));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mediator.message(message.setComponent("BimbinganPresenter").setEvent("getBimbinganByUsername").setText(getIntent().getStringExtra(EXTRA_DOSEN)));
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
        mediator.message(message.setComponent("MahasiswaBimbinganViewAdapter").setEvent("addItem").setItem(arrayList));
        mediator.message(message.setComponent("MahasiswaBimbinganViewAdapter").setEvent("setAdapter"));
        mediator.message(message.setComponent("RefreshLayout").setEvent("setRefreshing").setEnabled(false));
        if (arrayList.size() == 0) {
            mediator.message(message.setComponent("RelativeLayout").setEvent("setVisibility").setVisibility(View.VISIBLE));
        } else {
            mediator.message(message.setComponent("RelativeLayout").setEvent("setVisibility").setVisibility(View.GONE));
        }
        if (mahasiswa.getBimbingan_sum() > 0){
            mBinding.actMhsPaBimbinganDetailTextviewJumlah.setText(mahasiswa.getBimbingan_sum()+" pertemuan");
        }else{
            mBinding.actMhsPaBimbinganDetailTextviewJumlah.setText("Belum ada pertemuan");
        }
        mBinding.actMhsPaBimbinganDetailTextviewDosen.setText(dosen.getDsn_nama());
        mediator.message(message.setComponent("MahasiswaBimbinganViewAdapter").setEvent("setToken"));
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
