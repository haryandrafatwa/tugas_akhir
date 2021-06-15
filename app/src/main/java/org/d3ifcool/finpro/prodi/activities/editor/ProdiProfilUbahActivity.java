package org.d3ifcool.finpro.prodi.activities.editor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.d3ifcool.finpro.core.helpers.Constant;
import org.d3ifcool.finpro.core.helpers.Message;
import org.d3ifcool.finpro.core.interfaces.ProdiContract;
import org.d3ifcool.finpro.core.mediators.prodi.ConcreteMediator;
import org.d3ifcool.finpro.core.models.Koordinator;
import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.databinding.ActivityKoorProfilUbahBinding;

public class ProdiProfilUbahActivity extends AppCompatActivity implements ProdiContract.ViewModel {

    private ActivityKoorProfilUbahBinding mbinding;
    private Message message = new Message();
    private ConcreteMediator mediator;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mbinding = DataBindingUtil.setContentView(this, R.layout.activity_koor_profil_ubah);
        mbinding.setLifecycleOwner(this);
        mediator = new ConcreteMediator(this);
        mediator.setProdiPresenter(this);
        mbinding.setPresenter(mediator.getProdiPresenter());

        message.setKoordinator(getIntent().getParcelableExtra(Constant.ObjectConstanta.EXTRA_KOORDINATOR));

        setTitle(getString(R.string.title_profil_ubah));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0f);

        mediator.message(message.setComponent("ProgressDialog").setEvent("set"));
        mediator.message(message.setComponent("SessionManager").setEvent("set"));

        mediator.message(message.setComponent("ProdiPresenter").setEvent("showData"));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_accept, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mediator.message(message.setComponent("Toolbar").setVisibility(item.getItemId()).setEvent("koordinator"));
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onGetObjectProdi(Koordinator prodi) {

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
            case "onSuccess":
                mediator.message(message.setComponent("SessionManager").setEvent("updateKoor"));
                mediator.message(message.setComponent("Toasty").setEvent("Success").setText("Upload Berhasil!"));
                finish();
                break;
            default:
                mediator.message(message.setComponent("Toasty").setEvent("Warning").setText(messages));
                break;
        }
    }
}
