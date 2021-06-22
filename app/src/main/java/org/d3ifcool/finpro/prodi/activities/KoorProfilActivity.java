package org.d3ifcool.finpro.prodi.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import okhttp3.ResponseBody;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;

import org.d3ifcool.finpro.core.helpers.Message;
import org.d3ifcool.finpro.core.interfaces.AuthContract;
import org.d3ifcool.finpro.core.interfaces.ProdiContract;
import org.d3ifcool.finpro.core.mediators.interfaces.prodi.Mediator;
import org.d3ifcool.finpro.core.mediators.prodi.ConcreteMediator;
import org.d3ifcool.finpro.core.models.Koordinator;
import org.d3ifcool.finpro.core.models.User;
import org.d3ifcool.finpro.databinding.ActivityKoorProfilBinding;
import org.d3ifcool.finpro.R;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class KoorProfilActivity extends AppCompatActivity implements ProdiContract.ViewModel, AuthContract.ViewModel {

    private ActivityKoorProfilBinding mBinding;
    private Message message = new Message();
    private Mediator mediator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_koor_profil);
        mBinding.setLifecycleOwner(this);
        mediator = new ConcreteMediator(this);
        mediator.setAuthPresenter(this);
        mediator.setProdiPresenter(this);

        setTitle(getString(R.string.title_profil));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0f);

        mediator.message(message.setComponent("ProgressDialog").setEvent("set"));
        mediator.message(message.setComponent("SessionManager").setEvent("set"));

        mediator.message(message.setComponent("AuthPresenter").setEvent("getCurrentUser"));

    }

    @Override
    protected void onResume() {
        super.onResume();
        mediator.message(message.setComponent("AuthPresenter").setEvent("getCurrentUser"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mediator.message(message.setComponent("Toolbar").setVisibility(item.getItemId()).setEvent("koordinator"));
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setStatus(boolean status) {

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
            default:
                mediator.message(message.setComponent("Toasty").setEvent("Warning").setText(messages));
                break;
        }
    }

    @Override
    public void onRetrieveData(ResponseBody body) throws IOException, JSONException {
        mBinding.setModel(new Gson().fromJson(new JSONObject(body.string()).getJSONObject("data").toString(),Koordinator.class));
        message.setKoordinator(mBinding.getModel());
    }
}
