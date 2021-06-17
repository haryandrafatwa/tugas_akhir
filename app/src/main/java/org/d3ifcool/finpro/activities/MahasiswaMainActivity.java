package org.d3ifcool.finpro.activities;

import androidx.databinding.DataBindingUtil;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.Message;
import org.d3ifcool.finpro.core.interfaces.MahasiswaContract;
import org.d3ifcool.finpro.core.mediators.prodi.ConcreteMediator;
import org.d3ifcool.finpro.core.models.Plotting;
import org.d3ifcool.finpro.databinding.ActivityMahasiswaMainBinding;
import org.d3ifcool.finpro.core.helpers.SessionManager;
import org.d3ifcool.finpro.core.models.Mahasiswa;

import java.util.List;

public class MahasiswaMainActivity extends AppCompatActivity implements MahasiswaContract.ViewModel {

    private SessionManager sessionManager;
    private ActivityMahasiswaMainBinding mBinding;
    private Message message = new Message();
    private ConcreteMediator mediator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_mahasiswa_main);
        mBinding.setLifecycleOwner(this);
        mediator = new ConcreteMediator(this);
        mediator.setMahasiswaPresenter(this);

        mediator.message(message.setComponent("ProgressDialog").setEvent("set"));
        mediator.message(message.setComponent("SessionManager").setEvent("set"));

        mediator.message(message.setComponent("MahasiswaPresenter").setEvent("getMahasiswaBySession"));

        setTitle(R.string.title_informasi);
        getSupportActionBar().setElevation(0);

        mediator.setBottomNavigationView(mBinding.actMhsHomeBottomNavigation);
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
    public void onGetObjectMahasiswa(Mahasiswa mahasiswa) {
        mediator.message(message.setComponent("SessionManager").setEvent("createMahasiswa").setMahasiswa(mahasiswa));
    }

    @Override
    public void onGetListMahasiswa(List<Mahasiswa> mahasiswaList) {

    }

    @Override
    public void onSuccessGetPlotting(Plotting plotting) {

    }

    @Override
    public void onMessage(String messages) {
        switch (messages){
            case "ShowProgressDialog":
                mediator.message(message.setComponent("ProgressDialog").setEvent("show"));
                break;
            case "HideProgressDialog":
                mediator.message(message.setComponent("ProgressDialog").setEvent("dismiss"));
                break;
            default:
                mediator.message(message.setComponent("Toasty").setEvent("Warning").setText(messages));
                break;
        }
    }

}