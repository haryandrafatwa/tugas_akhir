package org.d3ifcool.finpro.prodi.activities.editor;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.Message;
import org.d3ifcool.finpro.core.interfaces.MahasiswaContract;
import org.d3ifcool.finpro.core.interfaces.PlottingContract;
import org.d3ifcool.finpro.core.interfaces.lists.PlottingListView;
import org.d3ifcool.finpro.core.interfaces.works.MahasiswaWorkView;
import org.d3ifcool.finpro.core.mediators.prodi.ConcreteMediator;
import org.d3ifcool.finpro.core.models.Mahasiswa;
import org.d3ifcool.finpro.core.models.Plotting;
import org.d3ifcool.finpro.databinding.ActivityKoorMahasiswaPlotPembimbingBinding;
import org.d3ifcool.finpro.prodi.activities.detail.ProdiMahasiswaDetailActivity;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;

import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.EXTRA_MAHASISWA;

public class ProdiMahasiswaPlotPembimbingActivity extends AppCompatActivity implements PlottingContract.ViewModel, MahasiswaContract.ViewModel {

    private ActivityKoorMahasiswaPlotPembimbingBinding mBinding;
    private Message message = new Message();
    private ConcreteMediator mediator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koor_mahasiswa_plot_pembimbing);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_koor_mahasiswa_plot_pembimbing);
        mBinding.setLifecycleOwner(this);
        mediator = new ConcreteMediator(this);
        mediator.setMahasiswaPresenter(this);
        mediator.setPlottingPresenter(this);
        mBinding.setPlotting(mediator.getPlottingPresenter());

        setTitle(getString(R.string.title_tambah_pembimbing));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        message.setMahasiswa(getIntent().getParcelableExtra(EXTRA_MAHASISWA));
        mediator.message(message.setComponent("ProgressDialog").setEvent("set"));
        mediator.message(message.setComponent("SessionManager").setEvent("set"));
        mediator.message(message.setComponent("FileHelper").setEvent("set"));
        mediator.message(message.setComponent("ProdiPlotPembimbingViewAdapter").setEvent("set"));
        mediator.message(message.setComponent("ProdiPlotPembimbingViewAdapter").setEvent("setPresenter"));
        mediator.message(message.setComponent("ProdiPlotPembimbingViewAdapter").setEvent("setToken"));
        mediator.setRecyclerView(mBinding.frgKoorDosenHomeRecyclerview);
        mediator.setRelativeLayout(mBinding.includeLayout.viewEmptyview);
        mediator.setRefreshLayout(mBinding.refresh);
        mBinding.setToken(mediator.getSessionToken());

        mediator.message(message.setComponent("PlottingPresenter").setEvent("getAllData"));

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
    public void onGetListPlotting(List<Plotting> plotting) {
        ArrayList<Plotting> arrayList = new ArrayList<>();
        arrayList.clear();
        arrayList.addAll(plotting);
        mediator.message(message.setComponent("ProdiPlotPembimbingViewAdapter").setEvent("addItem").setItem(arrayList));
        mediator.message(message.setComponent("ProdiPlotPembimbingViewAdapter").setEvent("setAdapter"));
        mediator.message(message.setComponent("RefreshLayout").setEvent("setRefreshing").setEnabled(false));

        if (arrayList.size() == 0) {
            mediator.message(message.setComponent("RelativeLayout").setEvent("setVisibility").setVisibility(View.VISIBLE));
        } else {
            mediator.message(message.setComponent("RelativeLayout").setEvent("setVisibility").setVisibility(View.GONE));
        }
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
            case "EmptyList":
                mediator.message(message.setComponent("RelativeLayout").setEvent("setVisibility").setVisibility(View.VISIBLE));
                break;
            case "onSuccess":
                mediator.message(message.setComponent("Toasty").setEvent("Success").setText("Tambah Pembimbing Berhasil!"));
                finish();
                break;
            default:
                mediator.message(message.setComponent("Toasty").setEvent("Warning").setText(messages));
                break;
        }
    }

    @Override
    public void onGetBody(ResponseBody body, String filename) {

    }

    @Override
    public void onGetObjectMahasiswa(Mahasiswa mahasiswa) {

    }

    @Override
    public void onGetListMahasiswa(List<Mahasiswa> mahasiswaList) {

    }

    @Override
    public void onSuccessGetPlotting(Plotting plotting) {

    }
}
