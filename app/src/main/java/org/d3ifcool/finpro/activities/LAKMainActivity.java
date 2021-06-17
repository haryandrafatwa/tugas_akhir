package org.d3ifcool.finpro.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.Message;
import org.d3ifcool.finpro.core.interfaces.ProdiContract;
import org.d3ifcool.finpro.core.mediators.prodi.ConcreteMediator;
import org.d3ifcool.finpro.core.models.Koordinator;
import org.d3ifcool.finpro.databinding.ActivityLakMainBinding;

public class LAKMainActivity extends AppCompatActivity implements ProdiContract.ViewModel {

    private ActivityLakMainBinding mBinding;
    private Message message = new Message();
    private ConcreteMediator mediator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_lak_main);
        mBinding.setLifecycleOwner(this);
        mediator = new ConcreteMediator(this);
        mediator.setProdiPresenter(this);

        mediator.message(message.setComponent("ProgressDialog").setEvent("set"));
        mediator.message(message.setComponent("SessionManager").setEvent("set"));

        mediator.message(message.setComponent("ProdiPresenter").setEvent("getMahasiswaBySession"));

        setTitle(R.string.title_informasi);
        getSupportActionBar().setElevation(0);

        mediator.setBottomNavigationView(mBinding.actDsnHomeBottomNavigation);
        mediator.message(message.setComponent("BottomNavigationView").setEvent("setOnNavigationItemSelectedListener"));
        mediator.message(message.setComponent("BottomNavigationView").setEvent("setFirstOpen").setText("mahasiswa"));
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
