package org.d3ifcool.finpro.core.presenters;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.api.ApiClient;
import org.d3ifcool.finpro.core.api.ApiService;
import org.d3ifcool.finpro.core.helpers.ConnectionHelper;
import org.d3ifcool.finpro.core.helpers.SessionManager;
import org.d3ifcool.finpro.core.interfaces.lists.InformasiListView;
import org.d3ifcool.finpro.core.interfaces.lists.PlottingListView;
import org.d3ifcool.finpro.core.interfaces.works.InformasiWorkView;
import org.d3ifcool.finpro.core.interfaces.works.PlottingWorkView;
import org.d3ifcool.finpro.core.models.Informasi;
import org.d3ifcool.finpro.core.models.Plotting;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ikhsan ramadhan
 * =========================================
 * Finpro
 * Copyright (C) 3/1/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhamad Ikhsan Ramadhan
 * E-mail   : ikhsanramadhan28@gmail.com
 * Majors   : D3 Teknik Informatika 2016
 * Campus   : Telkom University
 * -----------------------------------------
 */
public class PlottingPresenter {
    private PlottingWorkView viewEditor;
    private PlottingListView viewResult;

    private ConnectionHelper connectionHelper = new ConnectionHelper();
    private Context context;
    private ApiService apiInterface;

    private SessionManager sessionManager;

    public void initContext(Context context){
        this.context = context;
        this.sessionManager = new SessionManager(context);
        apiInterface = ApiClient.getApiClient().create(ApiService.class);
    }

    public PlottingPresenter(PlottingWorkView view) {
        this.viewEditor = view;
    }

    public PlottingPresenter(PlottingListView viewMain) {
        this.viewResult = viewMain;
    }

    public void createPlotting (String nip_pembimbing_1, String nip_pembimbing_2) {

        if (connectionHelper.isConnected(context)){
            viewEditor.showProgress();
            Call<ResponseBody> call = apiInterface.createPlotting("Bearer "+sessionManager.getSessionToken(), nip_pembimbing_1, nip_pembimbing_2);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    viewEditor.hideProgress();
                    if (response.isSuccessful()){
                        viewEditor.onSucces();
                    }else{
                        try {
                            JSONObject jsonObject = new JSONObject(response.errorBody().string());
                            viewEditor.onFailed(jsonObject.getString("message"));
                        } catch (IOException | JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    viewEditor.hideProgress();
                    viewEditor.onFailed(t.getMessage());
                    Log.e("FINPRO!", "onFailure: "+t.getMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }

    }


    public void updatePlotting (int informasi_id, String informasi_judul, String informasi_isi) {
        if (connectionHelper.isConnected(context)){
            viewEditor.showProgress();
            Call<Informasi> call = apiInterface.updateInformasi(informasi_id, informasi_judul, informasi_isi);
            call.enqueue(new Callback<Informasi>() {
                @Override
                public void onResponse(Call<Informasi> call, Response<Informasi> response) {
                    viewEditor.hideProgress();
                    viewEditor.onSucces();
                }

                @Override
                public void onFailure(Call<Informasi> call, Throwable t) {
                    viewEditor.showProgress();
                    viewEditor.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }
    }

    public void deletePlotting(int informasi_id) {
        if (connectionHelper.isConnected(context)){
            if (viewResult == null){
                viewEditor.hideProgress();
            }else{
                viewResult.hideProgress();
            }
            Call<Informasi> call = apiInterface.deleteInformasi(informasi_id);
            call.enqueue(new Callback<Informasi>() {
                @Override
                public void onResponse(Call<Informasi> call, Response<Informasi> response) {
                    viewEditor.hideProgress();
                    viewEditor.onSucces();
                }

                @Override
                public void onFailure(Call<Informasi> call, Throwable t) {
                    viewEditor.showProgress();
                    viewEditor.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }

    }

    public void getPlotting (){
        if (connectionHelper.isConnected(context)){
            viewResult.hideProgress();
            Call<List<Plotting>> call = apiInterface.getPlotting("Bearer "+sessionManager.getSessionToken());
            call.enqueue(new Callback<List<Plotting>>() {
                @Override
                public void onResponse(Call<List<Plotting>> call, Response<List<Plotting>> response) {
                    viewResult.hideProgress();
                    if (response.body() != null && response.isSuccessful()) {
                        viewResult.onGetListPlotting(response.body());
                    } else {
                        viewResult.isEmptyListPlotting();
                    }
                }

                @Override
                public void onFailure(Call<List<Plotting>> call, Throwable t) {
                    viewResult.hideProgress();
                    call.clone().enqueue(this);
//                    viewResult.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }
    }

    public void uploadFormPlotting (MultipartBody.Part part){
        if (connectionHelper.isConnected(context)){
            Call<ResponseBody> call = apiInterface.uploadFormPlotting("Bearer "+sessionManager.getSessionToken(),part);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    viewResult.hideProgress();
                    viewResult.onFailed("Upload Berhasil");
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    call.clone().enqueue(this);
//                    viewResult.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }
    }

    public void checkFormPlot (){
        if (connectionHelper.isConnected(context)){
            viewResult.showProgress();
            Call<ResponseBody> call = apiInterface.checkFormPlot("Bearer "+sessionManager.getSessionToken());
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    viewResult.hideProgress();
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        viewResult.onFailed(jsonObject.getString("message"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    call.clone().enqueue(this);
//                    viewResult.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }
    }

    public void downloadFormPlot (){
        if (connectionHelper.isConnected(context)){
            viewResult.showProgress();
            Call<ResponseBody> call = apiInterface.downloadFormPlot("Bearer "+sessionManager.getSessionToken());
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    viewResult.hideProgress();
                    String filename = response.headers().get("Content-Disposition").split("=")[1];
                    viewResult.onGetBody(response.body(),filename);
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    call.clone().enqueue(this);
//                    viewResult.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteFormPlot (){
        if (connectionHelper.isConnected(context)){
            viewResult.showProgress();
            Call<ResponseBody> call = apiInterface.deleteFormPlot("Bearer "+sessionManager.getSessionToken());
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    viewResult.hideProgress();
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        viewResult.onFailed(jsonObject.getString("message"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    call.clone().enqueue(this);
//                    viewResult.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }
    }

}
