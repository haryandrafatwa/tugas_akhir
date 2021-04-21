package org.d3ifcool.finpro.core.mediators.prodi;


import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.mediators.interfaces.prodi.ProdiFragmentMediator;
import org.d3ifcool.finpro.prodi.adapters.KoorDosenViewAdapter;
import org.d3ifcool.finpro.prodi.adapters.ProdiInformasiViewAdapter;
import org.d3ifcool.finpro.prodi.adapters.ProdiMahasiswaViewAdapter;
import org.d3ifcool.finpro.prodi.adapters.ProdiPlotPembimbingViewAdapter;
import org.d3ifcool.finpro.prodi.adapters.ProdiPlottingViewAdapter;
import org.d3ifcool.finpro.prodi.adapters.ProdiSKTAViewAdapter;

import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.FILE_TYPE_PDF;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.FILE_TYPE_XLS;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.FILE_TYPE_XLSX;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.PICK_EXCEL_REQUEST;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.PICK_PDF_REQUEST;

public class ProdiFragmentConcrete implements ProdiFragmentMediator {

    private View view;

    private SwipeRefreshLayout refreshLayout;
    private FloatingActionButton floatingActionButton, uploadFAB;
    private RecyclerView recyclerView;
    private View empty_view;
    private ProgressDialog progressDialog;
    private TextView tv_status, tv_download, tv_hapus;

    private KoorDosenViewAdapter koorDosenViewAdapter;
    private ProdiMahasiswaViewAdapter prodiMahasiswaViewAdapter;
    private ProdiInformasiViewAdapter prodiInformasiViewAdapter;
    private ProdiPlottingViewAdapter prodiPlottingViewAdapter;
    private ProdiSKTAViewAdapter prodiSKTAViewAdapter;
    private ProdiPlotPembimbingViewAdapter prodiPlotPembimbingViewAdapter;

    public ProdiFragmentConcrete(View view) {
        this.view = view;
    }

    @Override
    public void Notify(int id) {

    }

    @Override
    public void message(String event) {
        switch (event){
            case "TVHapus":
                tv_hapus = view.findViewById(R.id.tv_hapus);
                break;
            case "TVStatus":
                tv_status = view.findViewById(R.id.tv_status);
                break;
            case "TVDownload":
                tv_download = view.findViewById(R.id.tv_download);
                tv_download.setText(R.string.text_download_disini);
                break;
            case "RefreshLayout":
                refreshLayout = view.findViewById(R.id.refresh);
                break;
            case "RecycleView":
                recyclerView = view.findViewById(R.id.frg_koor_dosen_home_recyclerview);
                recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
                break;
            case "EmptyView":
                empty_view = view.findViewById(R.id.view_emptyview);
                break;
            case "ProgressDialog":
                progressDialog = new ProgressDialog(view.getContext());
                progressDialog.setMessage(view.getContext().getString(R.string.text_progress_dialog));
                break;
            case "UploadFAB":
                uploadFAB = view.findViewById(R.id.act_prodi_form_excel_fab);
                break;
            case"ProdiDosenAdapter":
                koorDosenViewAdapter = new KoorDosenViewAdapter(view.getContext());
                koorDosenViewAdapter.setLayoutType(R.layout.content_list_koor_dosen);
                break;
            case "ProdiMahasiswaAdapter":
                prodiMahasiswaViewAdapter = new ProdiMahasiswaViewAdapter(view.getContext());
                prodiMahasiswaViewAdapter.setLayouyType(R.layout.content_list_koor_mahasiswa);
                break;
            case "ProdiInformasiAdapter":
                prodiInformasiViewAdapter = new ProdiInformasiViewAdapter(view.getContext());
                break;
            case "ProdiPlottingAdapter":
                prodiPlottingViewAdapter = new ProdiPlottingViewAdapter(view.getContext());
                prodiPlottingViewAdapter.setLayoutType(R.layout.content_list_koor_plotting);
                break;
            case "ProdiSKTAAdapter":
                prodiSKTAViewAdapter = new ProdiSKTAViewAdapter(view.getContext());
                prodiSKTAViewAdapter.setLayoutType(R.layout.content_list_koor_mahasiswa);
                break;
            case "ProdiPlotPembimbingAdapter":
                prodiPlotPembimbingViewAdapter = new ProdiPlotPembimbingViewAdapter(view.getContext());
                prodiPlotPembimbingViewAdapter.setLayoutType(R.layout.content_list_koor_plotting);
                break;
            default:break;
        }
    }

    @Override
    public void message(String event, Class target) {
        switch (event){
            case "FloatingAButton":
                floatingActionButton = view.findViewById(R.id.frg_koor_dosen_home_fab);

                this.floatingActionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(view.getContext(), target);
                        view.getContext().startActivity(intent);
                    }
                });
                break;
        }
    }

    @Override
    public TextView getTVStatus() {
        return this.tv_status;
    }

    @Override
    public TextView getTVDownload() {
        return this.tv_download;
    }

    @Override
    public TextView getTVHapus() {
        return this.tv_hapus;
    }

    @Override
    public FloatingActionButton getFloatingButton() {
        return this.floatingActionButton;
    }

    @Override
    public FloatingActionButton getUploadFAB() {
        return this.uploadFAB;
    }

    @Override
    public RecyclerView getRecycleView() {
        return this.recyclerView;
    }

    @Override
    public View getView() {
        return this.empty_view;
    }

    @Override
    public SwipeRefreshLayout getRefreshLayout() {
        return this.refreshLayout;
    }

    @Override
    public ProgressDialog getProgressDialog() {
        return this.progressDialog;
    }

    @Override
    public KoorDosenViewAdapter getKoorDosenAdapter() {
        return this.koorDosenViewAdapter;
    }

    @Override
    public ProdiMahasiswaViewAdapter getKoorMahasiswaAdapter() {
        return this.prodiMahasiswaViewAdapter;
    }

    @Override
    public ProdiInformasiViewAdapter getKoorInformasiAdapter() {
        return this.prodiInformasiViewAdapter;
    }

    @Override
    public ProdiPlottingViewAdapter getKoorPlottingAdapter() {
        return this.prodiPlottingViewAdapter;
    }

    @Override
    public ProdiSKTAViewAdapter getProdiSKTAAdapter() {
        return this.prodiSKTAViewAdapter;
    }

    @Override
    public ProdiPlotPembimbingViewAdapter getProdiPlotPembimbingAdapter() {
        return this.prodiPlotPembimbingViewAdapter;
    }
}
