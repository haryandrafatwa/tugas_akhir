package org.d3ifcool.finpro.core.presenters;

import android.text.TextUtils;

import androidx.databinding.ObservableField;

import org.d3ifcool.finpro.App;
import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.interfaces.AuthContract;
import org.d3ifcool.finpro.core.models.manager.AuthManager;

public class AuthPresenter implements AuthContract.Presenter {
    public ObservableField<String> username;
    public ObservableField<String> password;
    private AuthContract.ViewModel mViewModel;
    private AuthManager authManager;

    public AuthPresenter(AuthContract.ViewModel mViewModel) {
        this.mViewModel = mViewModel;
        intFields();
        authManager = new AuthManager(mViewModel);
        authManager.initContext(App.self());
    }

    private void intFields(){
        username = new ObservableField<>();
        password = new ObservableField<>();
    }

    private boolean isValidate(){
        if (TextUtils.isEmpty(username.get())){
            mViewModel.onMessage("Username "+App.self().getString(R.string.text_tidak_boleh_kosong));
            return false;
        }

        if (TextUtils.isEmpty(password.get())){
            mViewModel.onMessage("Password "+App.self().getString(R.string.text_tidak_boleh_kosong));
            return false;
        }
        return true;
    }

    @Override
    public void doLogin() {
        if (isValidate()){
            authManager.getLogin(username.get(),password.get());
        }
    }

    @Override
    public void checkUser(String token) {
        authManager.checkUser(token);
    }

    @Override
    public void getUser(String token) {
        authManager.getUser(token);
    }
}
