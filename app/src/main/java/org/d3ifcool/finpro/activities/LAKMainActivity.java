package org.d3ifcool.finpro.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.Message;
import org.d3ifcool.finpro.core.mediators.prodi.ConcreteMediator;

public class LAKMainActivity extends AppCompatActivity {

    private Message message = new Message();
    private ConcreteMediator mediator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_admin_main);
        mBinding.setLifecycleOwner(this);
        mediator = new ConcreteMediator(this);
        mediator.setProdiPresenter(this);

        mediator.message(message.setComponent("ProgressDialog").setEvent("set"));
        mediator.message(message.setComponent("SessionManager").setEvent("set"));

        mediator.message(message.setComponent("ProdiPresenter").setEvent("getProdiByNIP"));

        setSupportActionBar(mBinding.includeLayout.toolbar);

        mediator.setActionBarDrawerToggle(mBinding.drawerLayout,mBinding.includeLayout.toolbar);
        mBinding.drawerLayout.addDrawerListener(mediator.getActionBarDrawerToggle());
        mBinding.navView.setNavigationItemSelectedListener(this);
        mBinding.navView.getMenu().getItem(0).setChecked(true);
        onNavigationItemSelected(mBinding.navView.getMenu().getItem(0));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mediator.message(message.setComponent("Toolbar").setVisibility(item.getItemId()).setEvent("mahasiswa"));
        return super.onOptionsItemSelected(item);
    }

}
