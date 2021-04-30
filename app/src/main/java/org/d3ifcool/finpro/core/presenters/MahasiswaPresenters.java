package org.d3ifcool.finpro.core.presenters;

import android.content.Context;
import android.widget.Toast;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.api.ApiClient;
import org.d3ifcool.finpro.core.api.ApiService;
import org.d3ifcool.finpro.core.helpers.ConnectionHelper;
import org.d3ifcool.finpro.core.helpers.SessionManager;
import org.d3ifcool.finpro.core.interfaces.lists.MahasiswaListView;
import org.d3ifcool.finpro.core.interfaces.objects.MahasiswaView;
import org.d3ifcool.finpro.core.interfaces.works.MahasiswaWorkView;
import org.d3ifcool.finpro.core.models.Mahasiswa;
import org.d3ifcool.finpro.core.models.Plotting;

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
 * Copyright (C) 3/2/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhamad Ikhsan Ramadhan
 * E-mail   : ikhsanramadhan28@gmail.com
 * Majors   : D3 Teknik Informatika 2016
 * Campus   : Telkom University
 * -----------------------------------------
 */
public class MahasiswaPresenters {
    private MahasiswaListView viewResult;
    private MahasiswaWorkView viewEditor;
    private MahasiswaView viewObject;

    private ConnectionHelper connectionHelper = new ConnectionHelper();
    private Context context;

    private SessionManager sessionManager;

    public void initContext(Context context){
        this.context = context;
        this.sessionManager = new SessionManager(context);
    }

    public MahasiswaPresenters(MahasiswaListView viewResult) {
        this.viewResult = viewResult;
    }

    public MahasiswaPresenters(MahasiswaListView viewResult, MahasiswaWorkView viewEditor) {
        this.viewResult = viewResult;
        this.viewEditor = viewEditor;
    }

    public MahasiswaPresenters(MahasiswaWorkView viewEditor) {
        this.viewEditor = viewEditor;
    }

    public MahasiswaPresenters(MahasiswaView viewObject) {
        this.viewObject = viewObject;
    }

    public MahasiswaPresenters(MahasiswaListView viewResult, MahasiswaView viewObject) {
        this.viewResult = viewResult;
        this.viewObject = viewObject;
    }

    public MahasiswaPresenters(MahasiswaWorkView viewEditor, MahasiswaView viewObject) {
        this.viewEditor = viewEditor;
        this.viewObject = viewObject;
    }

    public void getMahasiswa(){

        if (connectionHelper.isConnected(context)){
            viewResult.showProgress();
            ApiService apiInterfaceMahasiswa = ApiClient.getApiClient().create(ApiService.class);
            Call<List<Mahasiswa>> call = apiInterfaceMahasiswa.getMahasiswa("Bearer "+sessionManager.getSessionToken());
            call.enqueue(new Callback<List<Mahasiswa>>() {
                @Override
                public void onResponse(Call<List<Mahasiswa>> call, Response<List<Mahasiswa>> response) {
                    viewResult.hideProgress();
                    if (response.body() != null && response.isSuccessful()) {
                        viewResult.onGetListMahasiswa(response.body());
                    } else {
                        viewResult.isEmptyListMahasiswa();
                    }

                }

                @Override
                public void onFailure(Call<List<Mahasiswa>> call, Throwable t) {
                    viewResult.hideProgress();
                    viewResult.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

    public void createMahasiswa(String nim, String nama){

        if (connectionHelper.isConnected(context)){
            viewEditor.showProgress();
            ApiService apiInterfaceMahasiswa = ApiClient.getApiClient().create(ApiService.class);
            Call<Mahasiswa> call = apiInterfaceMahasiswa.createMahasiswa("Bearer "+sessionManager.getSessionToken(),nim, nama);
            call.enqueue(new Callback<Mahasiswa>() {
                @Override
                public void onResponse(Call<Mahasiswa> call, Response<Mahasiswa> response) {
                    viewEditor.hideProgress();
                    viewEditor.onSucces();
                }

                @Override
                public void onFailure(Call<Mahasiswa> call, Throwable t) {
                    viewEditor.hideProgress();
                    viewEditor.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }



    }

    public void updateMahasiswa(String nim, String nama, String angkatan, String kontak, String email, String mhs_foto, String judul){

        if (connectionHelper.isConnected(context)){
            viewEditor.showProgress();
            ApiService apiInterfaceMahasiswa = ApiClient.getApiClient().create(ApiService.class);
            Call<Mahasiswa> call = apiInterfaceMahasiswa.updateMahasiswa(nim, nama, angkatan, kontak, mhs_foto,email);
            call.enqueue(new Callback<Mahasiswa>() {
                @Override
                public void onResponse(Call<Mahasiswa> call, Response<Mahasiswa> response) {
                    viewEditor.hideProgress();
                    viewEditor.onSucces();
                }

                @Override
                public void onFailure(Call<Mahasiswa> call, Throwable t) {
                    viewEditor.hideProgress();
                    viewEditor.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }



    }

    public void updateMahasiswa(String nim, String nama, String angkatan, String kontak, String email, String judul){

        if (connectionHelper.isConnected(context)){
            viewEditor.showProgress();
            ApiService apiInterfaceMahasiswa = ApiClient.getApiClient().create(ApiService.class);
            Call<Mahasiswa> call = apiInterfaceMahasiswa.updateMahasiswa(nim, nama, angkatan, kontak, email, judul);
            call.enqueue(new Callback<Mahasiswa>() {
                @Override
                public void onResponse(Call<Mahasiswa> call, Response<Mahasiswa> response) {
                    viewEditor.hideProgress();
                    viewEditor.onSucces();
                }

                @Override
                public void onFailure(Call<Mahasiswa> call, Throwable t) {
                    viewEditor.hideProgress();
                    viewEditor.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }
    }

    public void addPembimbing(String mhs_nim, int id){

        if (connectionHelper.isConnected(context)){
            viewEditor.showProgress();
            ApiService apiInterfaceMahasiswa = ApiClient.getApiClient().create(ApiService.class);
            Call<Mahasiswa> call = apiInterfaceMahasiswa.addPembimbing("Bearer "+sessionManager.getSessionToken(),id,mhs_nim);
            call.enqueue(new Callback<Mahasiswa>() {
                @Override
                public void onResponse(Call<Mahasiswa> call, Response<Mahasiswa> response) {
                    if (response.body()!=null || response.isSuccessful()){
                        viewEditor.hideProgress();
                        viewEditor.onSucces();
                    }else{
                        viewEditor.hideProgress();
                        try {
                            viewEditor.onFailed(response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<Mahasiswa> call, Throwable t) {
                    viewEditor.hideProgress();
                    viewEditor.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }
    }

    public void updateSKTA(String mhs_nim, MultipartBody.Part part){

        if (connectionHelper.isConnected(context)){
            viewEditor.showProgress();
            ApiService apiInterfaceMahasiswa = ApiClient.getApiClient().create(ApiService.class);
            Call<ResponseBody> call = apiInterfaceMahasiswa.updateSKTA("Bearer "+sessionManager.getSessionToken(),mhs_nim, part);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.body()!=null || response.isSuccessful()){
                        viewEditor.hideProgress();
                        viewEditor.onSucces();
                    }else{
                        viewEditor.hideProgress();
                        try {
                            viewEditor.onFailed(response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    viewEditor.hideProgress();
                    viewEditor.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteMahasiswa(String nim){

        if (connectionHelper.isConnected(context)){
            viewEditor.showProgress();
            ApiService apiInterfaceMahasiswa = ApiClient.getApiClient().create(ApiService.class);
            Call<Mahasiswa> call = apiInterfaceMahasiswa.deleteMahasiswa(nim);
            call.enqueue(new Callback<Mahasiswa>() {
                @Override
                public void onResponse(Call<Mahasiswa> call, Response<Mahasiswa> response) {
                    viewEditor.hideProgress();
                    viewEditor.onSucces();
                }

                @Override
                public void onFailure(Call<Mahasiswa> call, Throwable t) {
                    viewEditor.hideProgress();
                    viewEditor.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

    public void getMahasiswaByParameter(String mhs_nim){

        if (connectionHelper.isConnected(context)){
            ApiService apiInterfaceMahasiswa = ApiClient.getApiClient().create(ApiService.class);
            Call<Mahasiswa> call = apiInterfaceMahasiswa.getMahasiswaByParameter(mhs_nim);
            call.enqueue(new Callback<Mahasiswa>() {
                @Override
                public void onResponse(Call<Mahasiswa> call, Response<Mahasiswa> response) {
                    viewObject.hideProgress();
                    if (response.body() != null && response.isSuccessful()) {
                        viewObject.onGetObjectMahasiswa(response.body());
                    } else {
                        viewObject.isEmptyObjectMahasiswa();
                    }

                }

                @Override
                public void onFailure(Call<Mahasiswa> call, Throwable t) {
                    viewObject.hideProgress();
                    viewObject.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }



    }

    public void getPembimbing(int plotId){
        if (connectionHelper.isConnected(context)){
            viewEditor.showProgress();
            ApiService apiInterfaceMahasiswa = ApiClient.getApiClient().create(ApiService.class);
            Call<Plotting> call = apiInterfaceMahasiswa.getPlottingByParamter("Bearer "+sessionManager.getSessionToken(),plotId);
            call.enqueue(new Callback<Plotting>() {
                @Override
                public void onResponse(Call<Plotting> call, Response<Plotting> response) {
                    viewEditor.hideProgress();
                    viewEditor.onSuccesGetPlotting(response.body());
                }

                @Override
                public void onFailure(Call<Plotting> call, Throwable t) {
                    viewEditor.hideProgress();
                    viewEditor.onFailed(t.getMessage());
                }
            });
        }
    }

    public void updateMahasiswaJudul(String nim, int judul_id){

        if (connectionHelper.isConnected(context)){
            viewEditor.showProgress();
            ApiService apiInterfaceMahasiswa = ApiClient.getApiClient().create(ApiService.class);
            Call<Mahasiswa> call = apiInterfaceMahasiswa.updateJudulMahasiswa(nim, judul_id);
            call.enqueue(new Callback<Mahasiswa>() {
                @Override
                public void onResponse(Call<Mahasiswa> call, Response<Mahasiswa> response) {
                    viewEditor.hideProgress();
                    viewEditor.onSucces();
                }

                @Override
                public void onFailure(Call<Mahasiswa> call, Throwable t) {
                    viewEditor.hideProgress();
                    viewEditor.onFailed(t.getLocalizedMessage());
                }
            });

        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

}
