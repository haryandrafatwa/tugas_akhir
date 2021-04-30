package org.d3ifcool.finpro.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import com.google.android.material.navigation.NavigationView;
import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.interfaces.ProdiContract;
import org.d3ifcool.finpro.core.mediators.prodi.ProdiConcrete;
import org.d3ifcool.finpro.core.models.Koordinator;
import org.d3ifcool.finpro.core.presenters.prodi.ProdiPresenter;
import org.d3ifcool.finpro.databinding.ActivityAdminMainBinding;

import java.util.List;

public class ProdiMainActivity extends AppCompatActivity implements ProdiContract.ViewModel, NavigationView.OnNavigationItemSelectedListener {

    private ActivityAdminMainBinding mBinding;
    private ProdiConcrete mediator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_admin_main);
        ProdiPresenter mPresenter = new ProdiPresenter(this);

        mediator = new ProdiConcrete(this);
        mediator.message("SessionManager","set");
        mediator.message("ProgressDialog","set");

        mPresenter.getProdiByNIP(mediator.getSessionManager().getSessionToken(),mediator.getSessionManager().getSessionUsername());

        setSupportActionBar(mBinding.includeLayout.toolbar);

        mediator.setActionBarDrawerToggle(mBinding.drawerLayout,mBinding.includeLayout.toolbar);
        mBinding.drawerLayout.addDrawerListener(mediator.getActionBarDrawerToggle());
        mBinding.navView.setNavigationItemSelectedListener(this);
        mBinding.navView.getMenu().getItem(0).setChecked(true);
        onNavigationItemSelected(mBinding.navView.getMenu().getItem(0));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_toolbar_koor, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        mediator.Notify(item.getItemId());
        mBinding.drawerLayout.closeDrawer(GravityCompat.START);
        item.setCheckable(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
        mediator.message("ProgressDialog","show");
    }

    @Override
    public void hideProgress() {
        mediator.message("ProgressDialog","dismiss");
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
