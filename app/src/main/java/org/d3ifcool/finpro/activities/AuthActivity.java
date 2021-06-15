package org.d3ifcool.finpro.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.components.ProgressDialog;
import org.d3ifcool.finpro.core.components.SessionManager;
import org.d3ifcool.finpro.core.interfaces.AuthContract;
import org.d3ifcool.finpro.core.mediators.prodi.LoginConcrete;
import org.d3ifcool.finpro.core.mediators.interfaces.prodi.LoginMediator;
import org.d3ifcool.finpro.core.models.User;
import org.d3ifcool.finpro.core.presenters.AuthPresenter;
import org.d3ifcool.finpro.databinding.ActivityAuthBinding;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import es.dmoral.toasty.Toasty;
import okhttp3.ResponseBody;

public class AuthActivity extends AppCompatActivity implements AuthContract.ViewModel {

    private AuthPresenter mPresenter;
    private ActivityAuthBinding mBinding;
    private LoginMediator loginMediator;

    private boolean status;
    private static final String ROLE_DOSEN = "dosen";
    private static final String ROLE_MAHASISWA = "mahasiswa";
    private static final String ROLE_LAK = "LAK";
    private static final String ROLE_PRODI = "prodi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_auth);
        mPresenter = new AuthPresenter(this);
        mBinding.setPresenter(mPresenter);

        loginMediator = new LoginConcrete();
        loginMediator.registerComponent(new ProgressDialog(this));
        loginMediator.registerComponent(new SessionManager(this));
        loginMediator.setMessagePD("Mohon tunggu . . .");

        if(loginMediator.getSessionToken() != null ){
            mPresenter.checkUser(loginMediator.getSessionToken());
        }
    }

    public void checkUserLogin(String cekPengguna){
        if (cekPengguna != null) {
            if(status){
                if (cekPengguna.equalsIgnoreCase(ROLE_MAHASISWA)){
                    startActivity(MahasiswaMainActivity.class);
                } else if (cekPengguna.equalsIgnoreCase(ROLE_DOSEN)){
                    startActivity(DosenMainActivity.class);
                } else if (cekPengguna.equalsIgnoreCase(ROLE_PRODI)) {
                    startActivity(ProdiMainActivity.class);
                } else if(cekPengguna.equalsIgnoreCase(ROLE_LAK)){
                    startActivity(ProdiMainActivity.class);
                }
            }
        }

    }

    private void startActivity(Class aClass){
        Intent j = new Intent(AuthActivity.this, aClass);
        startActivity(j);
        finish();
    }

    @Override
    public void setStatus(boolean status) {
        this.status = status;
        checkUserLogin(loginMediator.getSessionPengguna());
    }

    @Override
    public void onMessage(String messages) {
        switch (messages){
            case "showProgress":
                loginMediator.showPD();
                break;
            case "dismissProgress":
                loginMediator.dismissPD();
                break;
            default:
                Toasty.warning(this,messages,Toasty.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    public void onRetrieveData(ResponseBody body) throws IOException, JSONException {
        this.status = true;
        JSONObject object = new JSONObject(body.string());
        User user = (User) object.get("data");
        /*User user = new User(object.getString("token"),object.getString("username"),object.getString("pengguna"),
                object.getBoolean("success"),object.getString("message"));*/
        loginMediator.createSession(user.getUsername(), user.getPengguna(),user.getToken());
        checkUserLogin(user.getPengguna());
    }
}
