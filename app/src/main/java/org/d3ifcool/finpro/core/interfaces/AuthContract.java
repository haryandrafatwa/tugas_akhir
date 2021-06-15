package org.d3ifcool.finpro.core.interfaces;

import org.d3ifcool.finpro.core.models.User;
import org.json.JSONException;

import java.io.IOException;

import okhttp3.ResponseBody;

public interface AuthContract {

    interface ViewModel{
        void setStatus(boolean status);
        void onMessage(String messages);
        void onRetrieveData(ResponseBody body) throws IOException, JSONException;
    }

    interface Presenter{
        void doLogin();
        void checkUser(String token);
        void getUser(String token);
    }

}
