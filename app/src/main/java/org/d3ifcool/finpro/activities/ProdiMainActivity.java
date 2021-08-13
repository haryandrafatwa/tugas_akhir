package org.d3ifcool.finpro.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import com.google.android.material.navigation.NavigationView;
import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.Message;
import org.d3ifcool.finpro.core.interfaces.ProdiContract;
import org.d3ifcool.finpro.core.mediators.Mediator;
import org.d3ifcool.finpro.core.mediators.ConcreteMediator;
import org.d3ifcool.finpro.core.models.Koordinator;
import org.d3ifcool.finpro.databinding.ActivityAdminMainBinding;

import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.ROLE_PRODI;

public class ProdiMainActivity extends AppCompatActivity implements ProdiContract.ViewModel,
        NavigationView.OnNavigationItemSelectedListener {

    private Message message = new Message();
    private ActivityAdminMainBinding mBinding;
    private Mediator mediator;

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
        getMenuInflater().inflate(R.menu.menu_main_toolbar_koor, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        mediator.message(message.setComponent("Toolbar").setVisibility(item.getItemId()));
        mBinding.drawerLayout.closeDrawer(GravityCompat.START);
        item.setCheckable(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mediator.message(message.setComponent("Toolbar").setVisibility(item.getItemId()).setEvent(ROLE_PRODI));
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
    public void onGetObjectProdi(Koordinator prodi) {
        mediator.getSessionManager().createSessionDataKoor(prodi);
    }

    @Override
    public void onMessage(String messages) {
        switch (messages){
            case "showProgress":
                mediator.message(message.setComponent("ProgressDialog").setEvent("show"));
                break;
            case "dismissProgress":
                mediator.message(message.setComponent("ProgressDialog").setEvent("dismiss"));
                break;
            default:
                mediator.message(message.setComponent("Toasty").setEvent("Warning").setText(messages));
                break;
        }
    }
}
