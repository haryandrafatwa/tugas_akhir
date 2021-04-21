package org.d3ifcool.finpro.core.presenters;

import android.text.TextUtils;

import androidx.databinding.ObservableField;

import org.d3ifcool.finpro.App;
import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.SessionManager;
import org.d3ifcool.finpro.core.interfaces.LoginContract;
import org.d3ifcool.finpro.core.models.User;
import org.d3ifcool.finpro.core.models.manager.AuthManager;

import java.util.List;

public class LoginPresenter implements LoginContract.Presenter {
    public ObservableField<String> username;
    public ObservableField<String> password;
    private LoginContract.ViewModel mViewModel;
    private AuthManager authManager;

    public LoginPresenter(LoginContract.ViewModel mViewModel) {
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
            mViewModel.onFailed(App.self().getString(R.string.text_tidak_boleh_kosong));
            return false;
        }

        if (TextUtils.isEmpty(password.get())){
            mViewModel.onFailed(App.self().getString(R.string.text_tidak_boleh_kosong));
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
    public void checkUser(String username) {
        authManager.getUser(username);
    }
}
