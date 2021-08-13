package org.d3ifcool.finpro.mahasiswa.activities;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.ResponseBody;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.Message;
import org.d3ifcool.finpro.core.helpers.SessionManager;
import org.d3ifcool.finpro.core.interfaces.AuthContract;
import org.d3ifcool.finpro.core.interfaces.DosenContract;
import org.d3ifcool.finpro.core.interfaces.MahasiswaContract;
import org.d3ifcool.finpro.core.mediators.ConcreteMediator;
import org.d3ifcool.finpro.core.mediators.Mediator;
import org.d3ifcool.finpro.core.models.Dosen;
import org.d3ifcool.finpro.core.models.Mahasiswa;
import org.d3ifcool.finpro.core.models.Plotting;
import org.d3ifcool.finpro.databinding.ActivityDosenProfilBinding;
import org.d3ifcool.finpro.databinding.ActivityMahasiswaProfilBinding;
import org.d3ifcool.finpro.mahasiswa.activities.editor.MahasiswaProfilUbahActivity;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import static org.d3ifcool.finpro.core.api.ApiUrl.FinproUrl.URL_FOTO_MAHASISWA;

public class MahasiswaProfilActivity extends AppCompatActivity implements AuthContract.ViewModel  {

    private ActivityMahasiswaProfilBinding mBinding;
    private Message message = new Message();
    private Mediator mediator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_mahasiswa_profil);
        mBinding.setLifecycleOwner(this);
        mediator = new ConcreteMediator(this);
        mediator.setAuthPresenter(this);

        mediator.setTitleContextWithHomeAsUp("Profile");

        mediator.message(message.setComponent("ProgressDialog").setEvent("set"));
        mediator.message(message.setComponent("SessionManager").setEvent("set"));
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
        mediator.message(message.setComponent("Toolbar").setVisibility(item.getItemId()).setEvent("mahasiswa"));
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setStatus(boolean status) {

    }

    @Override
    public void onRetrieveData(ResponseBody body) throws IOException, JSONException {
        mBinding.setModel(new Gson().fromJson(new JSONObject(body.string()).getJSONObject("data").toString(), Mahasiswa.class));
        message.setMahasiswa(mBinding.getModel());
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
