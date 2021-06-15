package org.d3ifcool.finpro.core.models.manager;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.api.ApiClient;
import org.d3ifcool.finpro.core.api.ApiService;
import org.d3ifcool.finpro.core.helpers.ConnectionHelper;
import org.d3ifcool.finpro.core.interfaces.AuthContract;
import org.d3ifcool.finpro.core.models.User;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import es.dmoral.toasty.Toasty;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthManager {

    private AuthContract.ViewModel view;

    private ConnectionHelper connectionHelper = new ConnectionHelper();
    private Context context;

    public void initContext(Context context){
        this.context = context;
    }

    public AuthManager(AuthContract.ViewModel view) {
        this.view = view;
    }

    public AuthManager() {}

    public void getLogin(String username, String password){
        view.onMessage("showProgress");
        if (connectionHelper.isConnected(context)){
            ApiService apiInterface = ApiClient.getApiClient().create(ApiService.class);
            Call<ResponseBody> call = apiInterface.setLogin(username, password);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    view.onMessage("dismissProgress");
                    if (response.isSuccessful() && response.body() != null){
                        view.onMessage("dismissProgress");
                        try {
                            view.onRetrieveData(response.body());
                        } catch (JSONException|IOException e) {
                            e.printStackTrace();
                        }
                    }else{
                        try {
                            view.onMessage("dismissProgress");
                            view.onMessage(new JSONObject(response.body().string()).getString("message"));
                        } catch (JSONException|IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    view.onMessage("dismissProgress");
                    view.onMessage(t.getLocalizedMessage());
                }
            });
        } else {
            Toasty.error(context, context.getString(R.string.validate_no_connection), Toasty.LENGTH_SHORT).show();
        }

    }

    public void checkUser(String token){
        view.onMessage("showProgress");
        if (connectionHelper.isConnected(context)){
            ApiService apiInterface = ApiClient.getApiClient().create(ApiService.class);
            Call<ResponseBody> call = apiInterface.getCurrentUser("Bearer "+token);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful() && response.body() != null){
                        try {
                            String s = response.body().string();
                            JSONObject jsonObject = new JSONObject(s);
                            view.onMessage("dismissProgress");
                            view.setStatus(true);
                        } catch (IOException | JSONException e) {
                            e.printStackTrace();
                        }
                    }else{
                        view.onMessage("dismissProgress");
                        view.onMessage("User tidak ditemukan!");
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    call.clone().enqueue(this);
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }
    }

    public void getUser(String token){
        view.onMessage("showProgress");
        if (connectionHelper.isConnected(context)){
            ApiService apiInterface = ApiClient.getApiClient().create(ApiService.class);
            Call<ResponseBody> call = apiInterface.getCurrentUser("Bearer "+token);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful() && response.body() != null){
                        try {
                            view.onMessage("dismissProgress");
                            view.onRetrieveData(response.body());
                        } catch (IOException | JSONException e) {
                            e.printStackTrace();
                        }
                    }else{
                        view.onMessage("dismissProgress");
                        view.onMessage("User tidak ditemukan!");
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    call.clone().enqueue(this);
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }
    }

    public void logout(String token){
        ApiService apiInterface = ApiClient.getApiClient().create(ApiService.class);
        Call<ResponseBody> call = apiInterface.logout("Bearer "+token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful() && response.body() != null){
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        boolean success = jsonObject.getBoolean("success");
                        if (success) {
                            Log.e("TAG", "onResponse: "+jsonObject.getString("message"));
                        } else {
                            Log.e("TAG", "onResponse: "+jsonObject.getString("message"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("TAG", "onResponse: "+t.getMessage());
            }
        });

    }

}
