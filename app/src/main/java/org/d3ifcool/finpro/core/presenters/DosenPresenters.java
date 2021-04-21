package org.d3ifcool.finpro.core.presenters;

import android.content.Context;
import android.widget.Toast;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.ConnectionHelper;
import org.d3ifcool.finpro.core.helpers.SessionManager;
import org.d3ifcool.finpro.core.interfaces.objects.DosenPembimbingView;
import org.d3ifcool.finpro.core.interfaces.objects.DosenReviewerView;
import org.d3ifcool.finpro.core.interfaces.objects.DosenView;
import org.d3ifcool.finpro.core.interfaces.works.DosenWorkView;
import org.d3ifcool.finpro.core.interfaces.lists.DosenListView;
import org.d3ifcool.finpro.core.models.Dosen;
import org.d3ifcool.finpro.core.api.ApiClient;
import org.d3ifcool.finpro.core.api.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ikhsan ramadhan
 * =========================================
 * Finpro
 * Copyright (C) 3/2/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhamad Ikhsan Ramadhan
 * E-mail   : ikhsanramadhan28@gmail.com
 * Majors   : D3 Teknik Informatika 2016
 * Campus   : Telkom University
 * -----------------------------------------
 */
public class DosenPresenters {

    private DosenListView viewResult;
    private DosenWorkView viewEditor;
    private DosenView viewObject;
    private DosenPembimbingView viewObjectPembimbing;
    private DosenReviewerView viewObjectReviewer;

    private ConnectionHelper connectionHelper = new ConnectionHelper();
    private Context context;
    private SessionManager sessionManager;

    public void initContext(Context context){
        this.context = context;
        sessionManager = new SessionManager(context);
    }

    public DosenPresenters(DosenListView viewResult) {
        this.viewResult = viewResult;
    }

    public DosenPresenters(DosenListView viewResult, DosenWorkView viewEditor) {
        this.viewResult = viewResult;
        this.viewEditor = viewEditor;
    }

    public DosenPresenters(DosenWorkView viewEditor) {
        this.viewEditor = viewEditor;
    }

    public DosenPresenters(DosenView viewObject) {
        this.viewObject = viewObject;
    }

    public DosenPresenters(DosenListView viewResult, DosenView viewObject) {
        this.viewResult = viewResult;
        this.viewObject = viewObject;
    }

    public DosenPresenters(DosenPembimbingView viewObjectPembimbing, DosenReviewerView viewObjectReviewer) {
        this.viewObjectPembimbing = viewObjectPembimbing;
        this.viewObjectReviewer = viewObjectReviewer;
    }

    public DosenPresenters(DosenReviewerView viewObjectReviewer) {
        this.viewObjectReviewer = viewObjectReviewer;
    }

    public DosenPresenters(DosenPembimbingView viewObjectPembimbing) {
        this.viewObjectPembimbing = viewObjectPembimbing;
    }

    public DosenPresenters(DosenListView viewResult, DosenReviewerView viewObjectReviewer) {
        this.viewResult = viewResult;
        this.viewObjectReviewer = viewObjectReviewer;
    }

    public void getDosen(){

        if (connectionHelper.isConnected(context)){
            viewResult.showProgress();
            ApiService apiInterface = ApiClient.getApiClient().create(ApiService.class);
            Call<List<Dosen>> call = apiInterface.getDosen("Bearer "+sessionManager.getSessionToken());
            call.enqueue(new Callback<List<Dosen>>() {
                @Override
                public void onResponse(Call<List<Dosen>> call, Response<List<Dosen>> response) {
                    viewResult.hideProgress();
                    if (response.body() != null && response.isSuccessful()) {
                        viewResult.onGetListDosen(response.body());
                    } else {
                        viewResult.isEmptyListDosen();
                    }

                }

                @Override
                public void onFailure(Call<List<Dosen>> call, Throwable t) {
                    viewResult.hideProgress();
                    viewResult.onFailed(t.getMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

    public void createDosen(String nip, String nama, String kode){

        if (connectionHelper.isConnected(context)){
            viewEditor.showProgress();

            ApiService apiInterfaceDosen = ApiClient.getApiClient().create(ApiService.class);
            Call<Dosen> call = apiInterfaceDosen.createDosen("Bearer "+sessionManager.getSessionToken(),nip,nama,kode);
            call.enqueue(new Callback<Dosen>() {
                @Override
                public void onResponse(Call<Dosen> call, Response<Dosen> response) {
                    viewEditor.hideProgress();
                    viewEditor.onSucces();
                }

                @Override
                public void onFailure(Call<Dosen> call, Throwable t) {
                    viewEditor.hideProgress();
                    viewEditor.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

    public void deleteDosen(String nip){

        if (connectionHelper.isConnected(context)){
            viewEditor.showProgress();

            ApiService apiInterfaceDosen = ApiClient.getApiClient().create(ApiService.class);
            Call<Dosen> call = apiInterfaceDosen.deleteDosen(nip);
            call.enqueue(new Callback<Dosen>() {
                @Override
                public void onResponse(Call<Dosen> call, Response<Dosen> response) {
                    viewEditor.hideProgress();
                    viewEditor.onSucces();
                }

                @Override
                public void onFailure(Call<Dosen> call, Throwable t) {
                    viewEditor.hideProgress();
                    viewEditor.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

    public void updateDosen(String nip_dosen, String dsn_nama, String dsn_kode, String dsn_kontak, String dsn_email, int batas_bimbingan, int batas_reviewer) {

        if (connectionHelper.isConnected(context)){
            viewEditor.showProgress();
            ApiService apiInterfaceDosen = ApiClient.getApiClient().create(ApiService.class);
            Call<Dosen> call = apiInterfaceDosen.updateDosen(nip_dosen, dsn_nama, dsn_kode, dsn_kontak, dsn_email, batas_bimbingan, batas_reviewer);
            call.enqueue(new Callback<Dosen>() {
                @Override
                public void onResponse(Call<Dosen> call, Response<Dosen> response) {
                    viewEditor.hideProgress();
                    viewEditor.onSucces();
                }

                @Override
                public void onFailure(Call<Dosen> call, Throwable t) {
                    viewEditor.hideProgress();
                    viewEditor.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

    public void updateDosenPure(String nip_dosen, String dsn_nama, String dsn_kode, String dsn_kontak,String dsn_email) {

        if (connectionHelper.isConnected(context)){
            viewEditor.showProgress();
            ApiService apiInterfaceDosen = ApiClient.getApiClient().create(ApiService.class);
            Call<Dosen> call = apiInterfaceDosen.updateDosenPure(nip_dosen, dsn_nama, dsn_kode, dsn_kontak, dsn_email);
            call.enqueue(new Callback<Dosen>() {
                @Override
                public void onResponse(Call<Dosen> call, Response<Dosen> response) {
                    viewEditor.hideProgress();
                    viewEditor.onSucces();
                }

                @Override
                public void onFailure(Call<Dosen> call, Throwable t) {
                    viewEditor.hideProgress();
                    viewEditor.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

    public void getDosenByParameter(String dsn_nip){

        if (connectionHelper.isConnected(context)){
            ApiService apiInterfaceDosen = ApiClient.getApiClient().create(ApiService.class);
            Call<Dosen> call = apiInterfaceDosen.getDosenByParameter(dsn_nip);
            call.enqueue(new Callback<Dosen>() {
                @Override
                public void onResponse(Call<Dosen> call, Response<Dosen> response) {
                    viewObject.hideProgress();
                    if (response.body() != null && response.isSuccessful()) {
                        viewObject.onGetObjectDosen(response.body());
                    } else {
                        viewObject.isEmptyObjectDosen();
                    }

                }

                @Override
                public void onFailure(Call<Dosen> call, Throwable t) {
                    viewObject.hideProgress();
                    viewObject.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

    public void getDosenPembimbing(String dsn_nip){

        if (connectionHelper.isConnected(context)){
            ApiService apiInterfaceDosen = ApiClient.getApiClient().create(ApiService.class);
            Call<Dosen> call = apiInterfaceDosen.getDosenByParameter(dsn_nip);
            call.enqueue(new Callback<Dosen>() {
                @Override
                public void onResponse(Call<Dosen> call, Response<Dosen> response) {
                    viewObjectPembimbing.hideProgress();
                    if (response.body() != null && response.isSuccessful()) {
                        viewObjectPembimbing.onGetObjectDosenPembimbing(response.body());
                    } else {
                        viewObjectPembimbing.isEmptyObjectDosenPembimbing();
                    }

                }

                @Override
                public void onFailure(Call<Dosen> call, Throwable t) {
                    viewObjectPembimbing.hideProgress();
                    viewObjectPembimbing.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

    public void getDosenReviewer(String dsn_nip){
        if (connectionHelper.isConnected(context)){
            ApiService apiInterfaceDosen = ApiClient.getApiClient().create(ApiService.class);
            Call<Dosen> call = apiInterfaceDosen.getDosenByParameter(dsn_nip);
            call.enqueue(new Callback<Dosen>() {
                @Override
                public void onResponse(Call<Dosen> call, Response<Dosen> response) {
                    viewObjectReviewer.hideProgress();
                    if (response.body() != null && response.isSuccessful()) {
                        viewObjectReviewer.onGetObjectDosenReviewer(response.body());
                    } else {
                        viewObjectReviewer.isEmptyObjectDosenReviewer();
                    }

                }

                @Override
                public void onFailure(Call<Dosen> call, Throwable t) {
                    viewObjectReviewer.hideProgress();
                    viewObjectReviewer.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }

    }
}
