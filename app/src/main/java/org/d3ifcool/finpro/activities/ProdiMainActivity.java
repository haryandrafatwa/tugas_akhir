package org.d3ifcool.finpro.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.navigation.NavigationView;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.SessionManager;
import org.d3ifcool.finpro.core.interfaces.ProdiContract;
import org.d3ifcool.finpro.core.mediators.interfaces.prodi.ProdiMediator;
import org.d3ifcool.finpro.core.mediators.prodi.NavigationProdiMediator;
import org.d3ifcool.finpro.core.mediators.prodi.ProdiConcrete;
import org.d3ifcool.finpro.core.models.Koordinator;
import org.d3ifcool.finpro.core.presenters.prodi.ProdiPresenter;
import org.d3ifcool.finpro.databinding.ActivityAdminMainBinding;
import org.d3ifcool.finpro.prodi.activities.KoorPemberitahuanActivity;
import org.d3ifcool.finpro.prodi.activities.KoorProfilActivity;

import java.util.List;

public class ProdiMainActivity extends AppCompatActivity implements ProdiContract.ViewModel, NavigationView.OnNavigationItemSelectedListener {

    private ProdiPresenter mPresenter;
    private ActivityAdminMainBinding mBinding;
    private ProdiConcrete mediator;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_admin_main);
        mPresenter = new ProdiPresenter(this);
        mBinding.setPresenter(mPresenter);


//        sessionManager = new SessionManager(this);

        mediator = new ProdiConcrete(this);
        mediator.message("SessionManager","set");

        mediator.message("ProgressDialog","set");

        mPresenter.getProdiByNIP(mediator.getSessionManager().getSessionToken(),mediator.getSessionManager().getSessionUsername());

        setSupportActionBar(mBinding.includeLayout.toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,mBinding.drawerLayout,mBinding.includeLayout.toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        mBinding.drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        ((TextView)mBinding.navView.getHeaderView(0).findViewById(R.id.tv_nama)).setText(mediator.getSessionManager().getSessionKoorNama());
        if (mediator.getSessionManager().getSessionUsername().equalsIgnoreCase("admin_prodi")){
            ((TextView) mBinding.navView.getHeaderView(0).findViewById(R.id.tv_role)).setText(R.string.title_koor_prodi);
        }else{
            ((TextView) mBinding.navView.getHeaderView(0).findViewById(R.id.tv_role)).setText(R.string.title_koor_lak);
        }
        mBinding.navView.setNavigationItemSelectedListener(this);
        mBinding.navView.getMenu().getItem(0).setChecked(true);
        onNavigationItemSelected(mBinding.navView.getMenu().findItem(R.id.nav_menu_dosen));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_toolbar_koor, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        mediator.Notify(item.getItemId());
        mBinding.drawerLayout.closeDrawer(GravityCompat.START);
        item.setCheckable(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        mediator.Notify(item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            mBinding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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
    public void onGetObjectProdi(Koordinator prodi) {
        mediator.getSessionManager().createSessionDataKoor(prodi);
    }

    @Override
    public void isEmptyObjectProdi() {

    }

    @Override
    public void onGetListProdi(List<Koordinator> prodiList) {

    }

    @Override
    public void isEmptyListProdi() {

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFailed(String message) {

    }
}
