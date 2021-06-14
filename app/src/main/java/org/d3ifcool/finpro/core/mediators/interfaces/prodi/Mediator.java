package org.d3ifcool.finpro.core.mediators.interfaces.prodi;

import android.net.Uri;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.navigation.NavigationView;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.Message;
import org.d3ifcool.finpro.core.helpers.SessionManager;
import org.d3ifcool.finpro.core.interfaces.MahasiswaContract;
import org.d3ifcool.finpro.core.interfaces.PlottingContract;

import java.util.ArrayList;

public interface Mediator {

    void Notify(@IdRes int id);
    void message(String component, String event);
    void message(String component, String event, Uri uri);
    void message(Message message);

    void setTextView(TextView textView);
    void setRecyclerView(RecyclerView recyclerView);
    void setRefreshLayout(SwipeRefreshLayout refreshLayout);
    void setRelativeLayout(RelativeLayout relativeLayout);

    void setPlottingPresenter(PlottingContract.ViewModel viewModel);
    void setMahasiswaPresenter(MahasiswaContract.ViewModel viewModel);

    String getSessionToken();
    boolean getPermissionFile();

}
