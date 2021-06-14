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

    public void initContext(Context context){
        this.context = context;
    }

    public MahasiswaManager(MahasiswaContract.ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void getMahasiswa(String token){

        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("ShowProgressDialog");
            ApiService apiInterfaceMahasiswa = ApiClient.getApiClient().create(ApiService.class);
            Call<List<Mahasiswa>> call = apiInterfaceMahasiswa.getMahasiswa("Bearer "+token);
            call.enqueue(new Callback<List<Mahasiswa>>() {
                @Override
                public void onResponse(Call<List<Mahasiswa>> call, Response<List<Mahasiswa>> response) {
                    viewModel.onMessage("HideProgressDialog");
                    if (response.body() != null && response.isSuccessful()) {
                        viewModel.onGetListMahasiswa(response.body());
                    } else {
                        viewModel.onMessage("EmptyList");
                    }

                }

                @Override
                public void onFailure(Call<List<Mahasiswa>> call, Throwable t) {
                    call.clone().enqueue(this);
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

    public void createMahasiswa(String token, String nim, String nama){

        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("ShowProgressDialog");
            ApiService apiInterfaceMahasiswa = ApiClient.getApiClient().create(ApiService.class);
            Call<Mahasiswa> call = apiInterfaceMahasiswa.createMahasiswa("Bearer "+token,nim, nama);
            call.enqueue(new Callback<Mahasiswa>() {
                @Override
                public void onResponse(Call<Mahasiswa> call, Response<Mahasiswa> response) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onMessage("onSuccess");
                }

                @Override
                public void onFailure(Call<Mahasiswa> call, Throwable t) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onMessage(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }



    }

    public void updateMahasiswa(String token, String nim, String nama, String angkatan, String kontak, String email, String judul, String judul_inggris){

        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("ShowProgressDialog");
            ApiService apiInterfaceMahasiswa = ApiClient.getApiClient().create(ApiService.class);
            Call<Mahasiswa> call = apiInterfaceMahasiswa.updateMahasiswa("Bearer "+token,nim, nama, angkatan, kontak, email, judul, judul_inggris);
            call.enqueue(new Callback<Mahasiswa>() {
                @Override
                public void onResponse(Call<Mahasiswa> call, Response<Mahasiswa> response) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onMessage("onSuccess");
                }

                @Override
                public void onFailure(Call<Mahasiswa> call, Throwable t) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onMessage(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }



    }

    public void addPembimbing(String token, String mhs_nim, int id){

        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("ShowProgressDialog");
            ApiService apiInterfaceMahasiswa = ApiClient.getApiClient().create(ApiService.class);
            Call<Mahasiswa> call = apiInterfaceMahasiswa.addPembimbing("Bearer "+token,id,mhs_nim);
            call.enqueue(new Callback<Mahasiswa>() {
                @Override
                public void onResponse(Call<Mahasiswa> call, Response<Mahasiswa> response) {
                    if (response.body()!=null || response.isSuccessful()){
                        viewModel.onMessage("HideProgressDialog");
                        viewModel.onMessage("onSuccess");
                    }else{
                        viewModel.onMessage("HideProgressDialog");
                        try {
                            viewModel.onMessage(response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<Mahasiswa> call, Throwable t) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onMessage(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }
    }

    public void updateSKTA(String token, String mhs_nim, MultipartBody.Part part){

        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("ShowProgressDialog");
            ApiService apiInterfaceMahasiswa = ApiClient.getApiClient().create(ApiService.class);
            Call<ResponseBody> call = apiInterfaceMahasiswa.updateSKTA("Bearer "+token,mhs_nim, part);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.body()!=null || response.isSuccessful()){
                        viewModel.onMessage("HideProgressDialog");
                        viewModel.onMessage("onSuccess");
                    }else{
                        viewModel.onMessage("HideProgressDialog");
                        try {
                            viewModel.onMessage(response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onMessage(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteMahasiswa(String token, String nim){

        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("ShowProgressDialog");
            ApiService apiInterfaceMahasiswa = ApiClient.getApiClient().create(ApiService.class);
            Call<Mahasiswa> call = apiInterfaceMahasiswa.deleteMahasiswa("Bearer "+token, nim);
            call.enqueue(new Callback<Mahasiswa>() {
                @Override
                public void onResponse(Call<Mahasiswa> call, Response<Mahasiswa> response) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onMessage("onSuccess");
                }

                @Override
                public void onFailure(Call<Mahasiswa> call, Throwable t) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onMessage(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

    public void getMahasiswaByParameter(String token, String mhs_nim){

        if (connectionHelper.isConnected(context)){
            ApiService apiInterfaceMahasiswa = ApiClient.getApiClient().create(ApiService.class);
            Call<Mahasiswa> call = apiInterfaceMahasiswa.getMahasiswaByParameter("Bearer "+token,mhs_nim);
            call.enqueue(new Callback<Mahasiswa>() {
                @Override
                public void onResponse(Call<Mahasiswa> call, Response<Mahasiswa> response) {
                    viewModel.onMessage("HideProgressDialog");
                    if (response.body() != null && response.isSuccessful()) {
                        viewModel.onGetObjectMahasiswa(response.body());
                    } else {
                        viewModel.onMessage("EmptyObject");
                    }

                }

                @Override
                public void onFailure(Call<Mahasiswa> call, Throwable t) {
                    call.clone().enqueue(this);
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }



    }

    public void getPembimbing(String token, int plotId){
        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("ShowProgressDialog");
            ApiService apiInterfaceMahasiswa = ApiClient.getApiClient().create(ApiService.class);
            Call<Plotting> call = apiInterfaceMahasiswa.getPlottingByParamter("Bearer "+token,plotId);
            call.enqueue(new Callback<Plotting>() {
                @Override
                public void onResponse(Call<Plotting> call, Response<Plotting> response) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onSuccessGetPlotting(response.body());
                }

                @Override
                public void onFailure(Call<Plotting> call, Throwable t) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onMessage(t.getMessage());
                }
            });
        }
    }

    public void updateMahasiswaJudul(String nim, int judul_id){

        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("ShowProgressDialog");
            ApiService apiInterfaceMahasiswa = ApiClient.getApiClient().create(ApiService.class);
            Call<Mahasiswa> call = apiInterfaceMahasiswa.updateJudulMahasiswa(nim, judul_id);
            call.enqueue(new Callback<Mahasiswa>() {
                @Override
                public void onResponse(Call<Mahasiswa> call, Response<Mahasiswa> response) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onMessage("onSuccess");
                }

                @Override
                public void onFailure(Call<Mahasiswa> call, Throwable t) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onMessage(t.getLocalizedMessage());
                }
            });

        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

}
