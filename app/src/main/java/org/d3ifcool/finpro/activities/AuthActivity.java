package org.d3ifcool.finpro.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.components.ProgressDialog;
import org.d3ifcool.finpro.core.components.SessionManager;
import org.d3ifcool.finpro.core.interfaces.LoginContract;
import org.d3ifcool.finpro.core.mediators.prodi.LoginConcrete;
import org.d3ifcool.finpro.core.mediators.interfaces.prodi.LoginMediator;
import org.d3ifcool.finpro.core.models.User;
import org.d3ifcool.finpro.core.presenters.LoginPresenter;
import org.d3ifcool.finpro.databinding.ActivityAuthBinding;

import es.dmoral.toasty.Toasty;

public class AuthActivity extends AppCompatActivity implements LoginContract.ViewModel {

    private LoginPresenter mPresenter;
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
        mPresenter = new LoginPresenter(this);
        mBinding.setPresenter(mPresenter);

        loginMediator = new LoginConcrete();
        loginMediator.registerComponent(new ProgressDialog(this));
        loginMediator.registerComponent(new SessionManager(this));
        loginMediator.setMessagePD("Mohon tunggu . . .");

        if(loginMediator.getSessionUsername() != null ){
            mPresenter.checkUser(loginMediator.getSessionUsername());
        }
    }

    @Override
    public void login(User user) {
        this.status = true;
        loginMediator.createSession(user.getUsername(), user.getPengguna(),user.getToken());
        checkUserLogin(user.getPengguna());
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
    public void onFailed(String error) {
        Toasty.error(this,error,Toasty.LENGTH_LONG).show();
    }

    @Override
    public void showProgress() {
        loginMediator.showPD();
    }

    @Override
    public void hideProgress() {
        loginMediator.dismissPD();
    }

    @Override
    public void setStatus(boolean status) {
        this.status = status;
        checkUserLogin(loginMediator.getSessionPengguna());
    }
}
