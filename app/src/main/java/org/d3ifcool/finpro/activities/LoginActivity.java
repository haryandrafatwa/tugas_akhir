package org.d3ifcool.finpro.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.SessionManager;
import org.d3ifcool.finpro.core.interfaces.objects.LoginView;
import org.d3ifcool.finpro.core.models.User;
import org.d3ifcool.finpro.core.presenters.AuthPresenter;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button btn_login;
    private String username, password;
    private boolean status;

    private ProgressDialog progressDialog;
    private AuthPresenter authPresenter;
    private SessionManager sessionManager;

    private static final String ROLE_DOSEN = "dosen";
    private static final String ROLE_MAHASISWA = "mahasiswa";
    private static final String ROLE_LAK = "LAK";
    private static final String ROLE_PRODI = "prodi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_login = findViewById(R.id.act_main_button_login);
        editTextUsername = findViewById(R.id.act_main_edittext_username);
        editTextPassword = findViewById(R.id.act_main_edittext_password);

        sessionManager = new SessionManager(this);
        authPresenter = new AuthPresenter(this);
        authPresenter.initContext(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(org.d3ifcool.finpro.R.string.text_progress_dialog));

        if(sessionManager.getSessionUsername() != null ){
            authPresenter.getUser(sessionManager.getSessionUsername());
            checkUserLogin(sessionManager.getSessionPengguna());
        }

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = editTextUsername.getText().toString();
                password = editTextPassword.getText().toString();

                if (username.isEmpty()){
                    editTextUsername.setError(getString(R.string.text_tidak_boleh_kosong));
                } else if (password.isEmpty()){
                    editTextPassword.setError(getString(R.string.text_tidak_boleh_kosong));
                } else {
                    authPresenter.getLogin(username, password);
                }
            }
        });
    }

    public void checkUserLogin(String cekPengguna){

        if (cekPengguna != null) {
            if(status){
                if (cekPengguna.equalsIgnoreCase(ROLE_MAHASISWA)){
                    Intent j = new Intent(LoginActivity.this, MahasiswaMainActivity.class);
                    startActivity(j);
                    finish();
                } else if (cekPengguna.equalsIgnoreCase(ROLE_DOSEN)){
                    Intent i = new Intent(LoginActivity.this, DosenMainActivity.class);
                    startActivity(i);
                    finish();
                } else if (cekPengguna.equalsIgnoreCase(ROLE_PRODI)) {
                    Intent k = new Intent(LoginActivity.this, ProdiMainActivity.class);
                    startActivity(k);
                    finish();
                }
            }
        }

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
    public void onRequestSuccess(User user) {
        this.status = true;
        sessionManager.createSession(user.getUsername(), user.getPengguna(),user.getToken());
        checkUserLogin(user.getPengguna());
    }

    @Override
    public void setStatus(boolean status) {
        this.status = status;
        checkUserLogin(sessionManager.getSessionPengguna());
    }

    @Override
    public void isEmptyObjectLogin() {
        String failedMessage = "Id Pengguna atau Password Salah";
        Toast.makeText(this, failedMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailed(String message) {
        String failedMessage = "Id Pengguna atau Password Salah";
        Log.e("TAG", "onResponse: "+message);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
