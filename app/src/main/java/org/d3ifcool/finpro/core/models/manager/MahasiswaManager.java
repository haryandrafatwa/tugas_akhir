package org.d3ifcool.finpro.core.models.manager;

import android.content.Context;
import android.widget.Toast;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.api.ApiClient;
import org.d3ifcool.finpro.core.api.ApiService;
import org.d3ifcool.finpro.core.helpers.ConnectionHelper;
import org.d3ifcool.finpro.core.helpers.SessionManager;
import org.d3ifcool.finpro.core.interfaces.MahasiswaContract;
import org.d3ifcool.finpro.core.models.Mahasiswa;
import org.d3ifcool.finpro.core.models.Plotting;

import java.io.IOException;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MahasiswaManager {

    private MahasiswaContract.ViewModel viewModel;

    private ConnectionHelper connectionHelper = new ConnectionHelper();
    private Context context;
    private SessionManager sessionManager;

    public void initContext(Context context){
        this.context = context;
        sessionManager = new SessionManager(context);
    }

    public MahasiswaManager(MahasiswaContract.ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void getMahasiswa(){

        if (connectionHelper.isConnected(context)){
            viewModel.showProgress();
            ApiService apiInterfaceMahasiswa = ApiClient.getApiClient().create(ApiService.class);
            Call<List<Mahasiswa>> call = apiInterfaceMahasiswa.getMahasiswa("Bearer "+sessionManager.getSessionToken());
            call.enqueue(new Callback<List<Mahasiswa>>() {
                @Override
                public void onResponse(Call<List<Mahasiswa>> call, Response<List<Mahasiswa>> response) {
                    viewModel.hideProgress();
                    if (response.body() != null && response.isSuccessful()) {
                        viewModel.onGetListMahasiswa(response.body());
                    } else {
                        viewModel.isEmptyListMahasiswa();
                    }

                }

                @Override
                public void onFailure(Call<List<Mahasiswa>> call, Throwable t) {
                    viewModel.hideProgress();
                    viewModel.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

    public void createMahasiswa(String nim, String nama){

        if (connectionHelper.isConnected(context)){
            viewModel.showProgress();
            ApiService apiInterfaceMahasiswa = ApiClient.getApiClient().create(ApiService.class);
            Call<Mahasiswa> call = apiInterfaceMahasiswa.createMahasiswa("Bearer "+sessionManager.getSessionToken(),nim, nama);
            call.enqueue(new Callback<Mahasiswa>() {
                @Override
                public void onResponse(Call<Mahasiswa> call, Response<Mahasiswa> response) {
                    viewModel.hideProgress();
                    viewModel.onSuccess();
                }

                @Override
                public void onFailure(Call<Mahasiswa> call, Throwable t) {
                    viewModel.hideProgress();
                    viewModel.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }



    }

    public void updateMahasiswa(String nim, String nama, String angkatan, String kontak,String mhs_foto, String email){

        if (connectionHelper.isConnected(context)){
            viewModel.showProgress();
            ApiService apiInterfaceMahasiswa = ApiClient.getApiClient().create(ApiService.class);
            Call<Mahasiswa> call = apiInterfaceMahasiswa.updateMahasiswa(nim, nama, angkatan, kontak, mhs_foto,email);
            call.enqueue(new Callback<Mahasiswa>() {
                @Override
                public void onResponse(Call<Mahasiswa> call, Response<Mahasiswa> response) {
                    viewModel.hideProgress();
                    viewModel.onSuccess();
                }

                @Override
                public void onFailure(Call<Mahasiswa> call, Throwable t) {
                    viewModel.hideProgress();
                    viewModel.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }



    }

    public void updateMahasiswa(String nim, String nama, String angkatan, String kontak, String email){

        if (connectionHelper.isConnected(context)){
            viewModel.showProgress();
            ApiService apiInterfaceMahasiswa = ApiClient.getApiClient().create(ApiService.class);
            Call<Mahasiswa> call = apiInterfaceMahasiswa.updateMahasiswa(nim, nama, angkatan, kontak, email);
            call.enqueue(new Callback<Mahasiswa>() {
                @Override
                public void onResponse(Call<Mahasiswa> call, Response<Mahasiswa> response) {
                    viewModel.hideProgress();
                    viewModel.onSuccess();
                }

                @Override
                public void onFailure(Call<Mahasiswa> call, Throwable t) {
                    viewModel.hideProgress();
                    viewModel.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }
    }

    public void addPembimbing(String mhs_nim, int id){

        if (connectionHelper.isConnected(context)){
            viewModel.showProgress();
            ApiService apiInterfaceMahasiswa = ApiClient.getApiClient().create(ApiService.class);
            Call<Mahasiswa> call = apiInterfaceMahasiswa.addPembimbing("Bearer "+sessionManager.getSessionToken(),id,mhs_nim);
            call.enqueue(new Callback<Mahasiswa>() {
                @Override
                public void onResponse(Call<Mahasiswa> call, Response<Mahasiswa> response) {
                    if (response.body()!=null || response.isSuccessful()){
                        viewModel.hideProgress();
                        viewModel.onSuccess();
                    }else{
                        viewModel.hideProgress();
                        try {
                            viewModel.onFailed(response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<Mahasiswa> call, Throwable t) {
                    viewModel.hideProgress();
                    viewModel.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }
    }

    public void updateSKTA(String mhs_nim, MultipartBody.Part part){

        if (connectionHelper.isConnected(context)){
            viewModel.showProgress();
            ApiService apiInterfaceMahasiswa = ApiClient.getApiClient().create(ApiService.class);
            Call<ResponseBody> call = apiInterfaceMahasiswa.updateSKTA("Bearer "+sessionManager.getSessionToken(),mhs_nim, part);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.body()!=null || response.isSuccessful()){
                        viewModel.hideProgress();
                        viewModel.onSuccess();
                    }else{
                        viewModel.hideProgress();
                        try {
                            viewModel.onFailed(response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    viewModel.hideProgress();
                    viewModel.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteMahasiswa(String nim){

        if (connectionHelper.isConnected(context)){
            viewModel.showProgress();
            ApiService apiInterfaceMahasiswa = ApiClient.getApiClient().create(ApiService.class);
            Call<Mahasiswa> call = apiInterfaceMahasiswa.deleteMahasiswa(nim);
            call.enqueue(new Callback<Mahasiswa>() {
                @Override
                public void onResponse(Call<Mahasiswa> call, Response<Mahasiswa> response) {
                    viewModel.hideProgress();
                    viewModel.onSuccess();
                }

                @Override
                public void onFailure(Call<Mahasiswa> call, Throwable t) {
                    viewModel.hideProgress();
                    viewModel.onFailed(t.getLocalizedMessage());
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
                    viewModel.hideProgress();
                    if (response.body() != null && response.isSuccessful()) {
                        viewModel.onGetObjectMahasiswa(response.body());
                    } else {
                        viewModel.isEmptyObjectMahasiswa();
                    }

                }

                @Override
                public void onFailure(Call<Mahasiswa> call, Throwable t) {
                    viewModel.hideProgress();
                    viewModel.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }



    }

    public void getPembimbing(int plotId){
        if (connectionHelper.isConnected(context)){
            viewModel.showProgress();
            ApiService apiInterfaceMahasiswa = ApiClient.getApiClient().create(ApiService.class);
            Call<Plotting> call = apiInterfaceMahasiswa.getPlottingByParamter("Bearer "+sessionManager.getSessionToken(),plotId);
            call.enqueue(new Callback<Plotting>() {
                @Override
                public void onResponse(Call<Plotting> call, Response<Plotting> response) {
                    viewModel.hideProgress();
                    viewModel.onSuccessGetPlotting(response.body());
                }

                @Override
                public void onFailure(Call<Plotting> call, Throwable t) {
                    viewModel.hideProgress();
                    viewModel.onFailed(t.getMessage());
                }
            });
        }
    }

    public void updateMahasiswaJudul(String nim, int judul_id){

        if (connectionHelper.isConnected(context)){
            viewModel.showProgress();
            ApiService apiInterfaceMahasiswa = ApiClient.getApiClient().create(ApiService.class);
            Call<Mahasiswa> call = apiInterfaceMahasiswa.updateJudulMahasiswa(nim, judul_id);
            call.enqueue(new Callback<Mahasiswa>() {
                @Override
                public void onResponse(Call<Mahasiswa> call, Response<Mahasiswa> response) {
                    viewModel.hideProgress();
                    viewModel.onSuccess();
                }

                @Override
                public void onFailure(Call<Mahasiswa> call, Throwable t) {
                    viewModel.hideProgress();
                    viewModel.onFailed(t.getLocalizedMessage());
                }
            });

        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

}