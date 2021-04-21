package org.d3ifcool.finpro.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.SessionManager;
import org.d3ifcool.finpro.core.interfaces.LoginContract;
import org.d3ifcool.finpro.core.models.User;
import org.d3ifcool.finpro.core.presenters.LoginPresenter;
import org.d3ifcool.finpro.databinding.ActivityAuthBinding;

import es.dmoral.toasty.Toasty;

public class AuthActivity extends AppCompatActivity implements LoginContract.ViewModel {

    private LoginPresenter mPresenter;
    private ActivityAuthBinding mBinding;
    private SessionManager sessionManager;
    private ProgressDialog progressDialog;

    private boolean status;
    private static final String ROLE_DOSEN = "dosen";
    private static final String ROLE_MAHASISWA = "mahasiswa";
    private static final String ROLE_LAK = "LAK";
    private static final String ROLE_PRODI = "prodi";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_auth);
        mPresenter = new LoginPresenter(this);
        mBinding.setPresenter(mPresenter);

        sessionManager = new SessionManager(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(org.d3ifcool.finpro.R.string.text_progress_dialog));

        if(sessionManager.getSessionUsername() != null ){
            mPresenter.checkUser(sessionManager.getSessionUsername());
        }
    }

    @Override
    public void login(User user) {
        this.status = true;
        sessionManager.createSession(user.getUsername(), user.getPengguna(),user.getToken());
        checkUserLogin(user.getPengguna());
    }

    public void checkUserLogin(String cekPengguna){

        if (cekPengguna != null) {
            if(status){
                if (cekPengguna.equalsIgnoreCase(ROLE_MAHASISWA)){
                    Intent j = new Intent(AuthActivity.this, MahasiswaMainActivity.class);
                    startActivity(j);
                    finish();
                } else if (cekPengguna.equalsIgnoreCase(ROLE_DOSEN)){
                    Intent i = new Intent(AuthActivity.this, DosenMainActivity.class);
                    startActivity(i);
                    finish();
                } else if (cekPengguna.equalsIgnoreCase(ROLE_PRODI)) {
                    Intent k = new Intent(AuthActivity.this, ProdiMainActivity.class);
                    startActivity(k);
                    finish();
                }
            }
        }

    }

    @Override
    public void onFailed(String error) {
        Toasty.error(this,error,Toasty.LENGTH_LONG).show();
    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void setStatus(boolean status) {
        this.status = status;
        checkUserLogin(sessionManager.getSessionPengguna());
    }
}
