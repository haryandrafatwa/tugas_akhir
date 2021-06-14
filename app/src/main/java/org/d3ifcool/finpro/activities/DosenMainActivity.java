package org.d3ifcool.finpro.activities;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.databinding.DataBindingUtil;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.d3ifcool.finpro.core.interfaces.DosenContract;
import org.d3ifcool.finpro.core.mediators.prodi.ConcreteMediator;
import org.d3ifcool.finpro.core.presenters.DosenPresenter;
import org.d3ifcool.finpro.databinding.ActivityDosenMainBinding;
import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.models.Dosen;

import java.util.List;

public class DosenMainActivity extends AppCompatActivity implements DosenContract.ViewModel, BottomNavigationView.OnNavigationItemSelectedListener {

    private ActivityDosenMainBinding mBinding;
    private ConcreteMediator mediator;
    private DosenPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_dosen_main);
        mPresenter = new DosenPresenter(this);

        setTitle(R.string.title_informasi);
        getSupportActionBar().setElevation(0);

        mediator = new ConcreteMediator(this);
        mediator.message("SessionManager","set");
        mPresenter.getCurrentDosen(mediator.getSessionManager().getSessionToken());

        mBinding.actDsnHomeBottomNavigation.setOnNavigationItemSelectedListener(this);
        mBinding.actDsnHomeBottomNavigation.getMenu().getItem(0).setChecked(true);
        onNavigationItemSelected(mBinding.actDsnHomeBottomNavigation.getMenu().getItem(0));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mediator.Notify(item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onGetObjectDosen(Dosen dosen) {
        mediator.getSessionManager().createSessionDataDosen(dosen);
    }

    @Override
    public void onGetListDosen(List<Dosen> dosenList) {

    }

    @Override
    public void onMessage(String message) {

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        item.setChecked(true);
        mediator.Notify(item.getItemId());
        return false;
    }
}
