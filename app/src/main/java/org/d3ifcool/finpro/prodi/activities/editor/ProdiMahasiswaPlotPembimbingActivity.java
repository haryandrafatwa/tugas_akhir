package org.d3ifcool.finpro.prodi.activities.editor;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.interfaces.lists.PlottingListView;
import org.d3ifcool.finpro.core.interfaces.works.MahasiswaWorkView;
import org.d3ifcool.finpro.core.mediators.prodi.ConcreteMediator;
import org.d3ifcool.finpro.core.models.Mahasiswa;
import org.d3ifcool.finpro.core.models.Plotting;
import org.d3ifcool.finpro.prodi.activities.detail.ProdiMahasiswaDetailActivity;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;

import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.EXTRA_MAHASISWA;

public class ProdiMahasiswaPlotPembimbingActivity extends AppCompatActivity implements PlottingListView , MahasiswaWorkView{

    private ArrayList<Plotting> arrayList = new ArrayList<>();
    private ConcreteMediator mediator;
//    private PlottingPresenter plottingPresenter;
//    private MahasiswaPresenters mahasiswaPresenter;

    private Mahasiswa extraMahasiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koor_mahasiswa_plot_pembimbing);

        setTitle(getString(R.string.title_tambah_pembimbing));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mediator = new ConcreteMediator(this);
        mediator.message("RefreshLayout","set");
        mediator.message("RecycleView","set");
        mediator.message("EmptyView","set");
        mediator.message("ProgressDialog","set");
        mediator.message("ProdiPlotPembimbingAdapter","set");

        extraMahasiswa = getIntent().getParcelableExtra(EXTRA_MAHASISWA);

//        plottingPresenter = new PlottingPresenter(this);
//        plottingPresenter.initContext(this);
//        plottingPresenter.getPlotting();
/*
        mediator.getRefreshLayout().setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                plottingPresenter.getPlotting();
            }
        });*/

//        mahasiswaPresenter = new MahasiswaPresenters(this);
//        mahasiswaPresenter.initContext(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int i = item.getItemId();
        if (i == android.R.id.home) {
            finish();
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        intentToDetail();
    }

    private void intentToDetail(){
        Intent intent = new Intent(this, ProdiMahasiswaDetailActivity.class);
        Mahasiswa parcelMahasiswa = extraMahasiswa;
        intent.putExtra(EXTRA_MAHASISWA, parcelMahasiswa);
        startActivity(intent);
        finish();
    }

    @Override
    public void showProgress() {
        mediator.getProgressDialog().show();
    }

    @Override
    public void hideProgress() {
        mediator.getProgressDialog().dismiss();
    }

    @Override
    public void onSucces() {
        finish();
        intentToDetail();
    }

    @Override
    public void onSuccesGetPlotting(Plotting plotting) {

    }

    @Override
    public void onGetListPlotting(List<Plotting> plotting) {
        arrayList.clear();
        arrayList.addAll(plotting);

        /*mediator.getProdiPlotPembimbingAdapter().setMhs_nim(extraMahasiswa.getMhs_nim());
        mediator.getProdiPlotPembimbingAdapter().setMahasiswaPresenter(mahasiswaPresenter);
        mediator.getProdiPlotPembimbingAdapter().addItem(arrayList);
        mediator.getRecycleView().setAdapter(mediator.getProdiPlotPembimbingAdapter());
        mediator.getRefreshLayout().setRefreshing(false);

        if (arrayList.size() == 0) {
            mediator.getView().setVisibility(View.VISIBLE);
        } else {
            mediator.getView().setVisibility(View.GONE);
        }*/
    }

    @Override
    public void onGetBody(ResponseBody body, String filename) {

    }

    @Override
    public void isEmptyListPlotting() {
        //mediator.getView().setVisibility(View.VISIBLE);
    }


    @Override
    public void onFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
