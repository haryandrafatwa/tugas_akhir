package org.d3ifcool.finpro.core.presenters;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.ConnectionHelper;
import org.d3ifcool.finpro.core.interfaces.objects.LoginView;
import org.d3ifcool.finpro.core.models.User;
import org.d3ifcool.finpro.core.api.ApiClient;
import org.d3ifcool.finpro.core.api.ApiService;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * Finpro
 * Copyright (C) 03/03/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Line     : bullbee117
 * Phone    : 081357108568
 * Majors   : D3 Teknik Informatika 2016
 * Campus   : Telkom University
 * -----------------------------------------
 * id.amirisback.frogobox
 */
public class AuthPresenter {

    private LoginView view;

    private ConnectionHelper connectionHelper = new ConnectionHelper();
    private Context context;

    public void initContext(Context context){
        this.context = context;
    }

    public AuthPresenter(LoginView view) {
        this.view = view;
    }

    public AuthPresenter() {}

    public void getLogin(String username, String password){

        if (connectionHelper.isConnected(context)){
            view.showProgress();
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
                            view.onRequestSuccess(response.body());
                        } else {
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
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }

    }

    public void getUser(String username){

        if (connectionHelper.isConnected(context)){
            view.showProgress();
            ApiService apiInterface = ApiClient.getApiClient().create(ApiService.class);
            Call<ResponseBody> call = apiInterface.getUserBy(username);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    view.hideProgress();
                    if (response.isSuccessful() && response.body() != null){
                        try {
                            String s = response.body().string();
                            JSONObject jsonObject = new JSONObject(s);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) {
                                Log.e("TAG", "onResponse: "+response.body());
                                view.setStatus(true);
                            } else {
                                view.onFailed("User tidak ditemukan!");
                            }
                        } catch (IOException | JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    view.hideProgress();
                    view.onFailed(t.getLocalizedMessage());
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
