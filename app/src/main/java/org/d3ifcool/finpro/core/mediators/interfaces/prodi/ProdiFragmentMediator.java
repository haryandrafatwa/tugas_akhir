package org.d3ifcool.finpro.core.mediators.interfaces.prodi;

import android.app.ProgressDialog;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.d3ifcool.finpro.prodi.adapters.ProdiDosenViewAdapter;
import org.d3ifcool.finpro.prodi.adapters.ProdiInformasiViewAdapter;
import org.d3ifcool.finpro.prodi.adapters.ProdiMahasiswaViewAdapter;
import org.d3ifcool.finpro.prodi.adapters.ProdiPlotPembimbingViewAdapter;
import org.d3ifcool.finpro.prodi.adapters.ProdiPlottingViewAdapter;
import org.d3ifcool.finpro.prodi.adapters.ProdiSKTAViewAdapter;

public interface ProdiFragmentMediator {

    void Notify(@IdRes int id);
    void message(String event);
    void message(String event,Class target);
    TextView getTVStatus();
    TextView getTVDownload();
    TextView getTVHapus();
    FloatingActionButton getFloatingButton();
    FloatingActionButton getUploadFAB();
    RecyclerView getRecycleView();
    View getView();
    SwipeRefreshLayout getRefreshLayout();
    ProgressDialog getProgressDialog();
    ProdiDosenViewAdapter getKoorDosenAdapter();
    ProdiMahasiswaViewAdapter getKoorMahasiswaAdapter();
    ProdiInformasiViewAdapter getKoorInformasiAdapter();
    ProdiPlottingViewAdapter getKoorPlottingAdapter();
    ProdiSKTAViewAdapter getProdiSKTAAdapter();
    ProdiPlotPembimbingViewAdapter getProdiPlotPembimbingAdapter();
}
