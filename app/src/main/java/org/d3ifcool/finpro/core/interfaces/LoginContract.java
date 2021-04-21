package org.d3ifcool.finpro.core.interfaces;

import org.d3ifcool.finpro.core.models.User;

public interface LoginContract {

    interface ViewModel{
        void login(User user);
        void onFailed(String error);
        void showProgress();
        void hideProgress();
        void setStatus(boolean status);
    }

    interface Presenter{
        void doLogin();
        void checkUser(String username);
    }

}
