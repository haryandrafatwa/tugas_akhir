package org.d3ifcool.finpro.core.models.manager;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.api.ApiClient;
import org.d3ifcool.finpro.core.api.ApiService;
import org.d3ifcool.finpro.core.helpers.ConnectionHelper;
import org.d3ifcool.finpro.core.interfaces.LoginContract;
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

    private LoginContract.ViewModel view;

    private ConnectionHelper connectionHelper = new ConnectionHelper();
    private Context context;

    public void initContext(Context context){
        this.context = context;
    }

    public AuthManager(LoginContract.ViewModel view) {
        this.view = view;
    }

    public AuthManager() {}

    public void getLogin(String username, String password){
        view.showProgress();
        if (connectionHelper.isConnected(context)){
            ApiService apiInterface = ApiClient.getApiClient().create(ApiService.class);
            Call<User> call = apiInterface.setLogin(username, password);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    view.hideProgress();
                    if (response.isSuccessful() && response.body() != null){
                        boolean success = response.body().getSuccess();
                        if (success) {
                            Log.e("TAG", "onResponse: "+response.body());
                            view.hideProgress();
                            view.login(response.body());
                        } else {
                            view.hideProgress();
                            view.onFailed(response.body().getMessage());
                        }
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    view.hideProgress();
                    view.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toasty.error(context, context.getString(R.string.validate_no_connection), Toasty.LENGTH_SHORT).show();
        }

    }

    public void getUser(String username){
        view.showProgress();
        if (connectionHelper.isConnected(context)){
            ApiService apiInterface = ApiClient.getApiClient().create(ApiService.class);
            Call<ResponseBody> call = apiInterface.getUserBy(username);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful() && response.body() != null){
                        try {
                            String s = response.body().string();
                            JSONObject jsonObject = new JSONObject(s);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) {
                                view.hideProgress();
                                Log.e("TAG", "onResponse: "+response.body());
                                view.setStatus(true);
                            } else {
                                view.hideProgress();
                                view.onFailed("User tidak ditemukan!");
                            }
                        } catch (IOException | JSONException e) {
                            e.printStackTrace();
                        }
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
