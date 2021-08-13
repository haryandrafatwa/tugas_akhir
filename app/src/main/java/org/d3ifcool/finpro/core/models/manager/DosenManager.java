package org.d3ifcool.finpro.core.models.manager;

import android.content.Context;
import android.widget.Toast;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.api.ApiClient;
import org.d3ifcool.finpro.core.api.ApiService;
import org.d3ifcool.finpro.core.helpers.ConnectionHelper;
import org.d3ifcool.finpro.core.helpers.SessionManager;
import org.d3ifcool.finpro.core.interfaces.DosenContract;
import org.d3ifcool.finpro.core.models.Dosen;
import org.d3ifcool.finpro.core.models.Mahasiswa;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DosenManager {

    private DosenContract.ViewModel viewModel;

    private ConnectionHelper connectionHelper = new ConnectionHelper();
    private Context context;

    public void initContext(Context context){
        this.context = context;
    }

    public DosenManager(DosenContract.ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void getAllDosen(String token){

        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("ShowProgressDialog");
            ApiService apiInterface = ApiClient.getApiClient().create(ApiService.class);
            Call<List<Dosen>> call = apiInterface.getAllDosen("Bearer "+token);
            call.enqueue(new Callback<List<Dosen>>() {
                @Override
                public void onResponse(Call<List<Dosen>> call, Response<List<Dosen>> response) {
                    viewModel.onMessage("HideProgressDialog");
                    if (response.body() != null && response.isSuccessful()) {
                        viewModel.onGetListDosen(response.body());
                    } else {
                        viewModel.onMessage("EmptyList");
                    }

                }

                @Override
                public void onFailure(Call<List<Dosen>> call, Throwable t) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onMessage(t.getMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

    public void getCurrentDosen(String token){

        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("ShowProgressDialog");
            ApiService apiInterface = ApiClient.getApiClient().create(ApiService.class);
            Call<Dosen> call = apiInterface.getDosen("Bearer "+token);
            call.enqueue(new Callback<Dosen>() {
                @Override
                public void onResponse(Call<Dosen> call, Response<Dosen> response) {
                    viewModel.onMessage("HideProgressDialog");
                    if (response.body() != null && response.isSuccessful()) {
                        viewModel.onGetObjectDosen(response.body());
                    } else {
                        viewModel.onMessage("EmptyObject");
                    }

                }

                @Override
                public void onFailure(Call<Dosen> call, Throwable t) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onMessage(t.getMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

    public void createDosen(String token, String nip, String nama, String kode){

        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("ShowProgressDialog");
            ApiService apiInterfaceDosen = ApiClient.getApiClient().create(ApiService.class);
            Call<Dosen> call = apiInterfaceDosen.createDosen("Bearer "+token,nip,nama,kode);
            call.enqueue(new Callback<Dosen>() {
                @Override
                public void onResponse(Call<Dosen> call, Response<Dosen> response) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onMessage("onSuccess");
                }

                @Override
                public void onFailure(Call<Dosen> call, Throwable t) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onMessage(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteDosen(String token, String nip){

        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("ShowProgressDialog");

            ApiService apiInterfaceDosen = ApiClient.getApiClient().create(ApiService.class);
            Call<Dosen> call = apiInterfaceDosen.deleteDosen("Bearer "+token,nip);
            call.enqueue(new Callback<Dosen>() {
                @Override
                public void onResponse(Call<Dosen> call, Response<Dosen> response) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onMessage("onSuccess");
                }

                @Override
                public void onFailure(Call<Dosen> call, Throwable t) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onMessage(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

    public void updateDosen(String token, String nip_dosen, String dsn_nama, String dsn_kode, String dsn_kontak, String dsn_email, int batas_bimbingan, int batas_reviewer) {

        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("ShowProgressDialog");
            ApiService apiInterfaceDosen = ApiClient.getApiClient().create(ApiService.class);
            Call<Dosen> call = apiInterfaceDosen.updateDosen("Bearer "+token, nip_dosen, dsn_nama, dsn_kode, dsn_kontak, dsn_email, batas_bimbingan, batas_reviewer);
            call.enqueue(new Callback<Dosen>() {
                @Override
                public void onResponse(Call<Dosen> call, Response<Dosen> response) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onMessage("onSuccess");
                }

                @Override
                public void onFailure(Call<Dosen> call, Throwable t) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onMessage(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

    public void getDosenByParameter(String token, String dsn_nip){
        if (connectionHelper.isConnected(context)){
            ApiService apiInterfaceDosen = ApiClient.getApiClient().create(ApiService.class);
            Call<Dosen> call = apiInterfaceDosen.getDosenByParameter("Bearer "+token,dsn_nip);
            call.enqueue(new Callback<Dosen>() {
                @Override
                public void onResponse(Call<Dosen> call, Response<Dosen> response) {
                    viewModel.onMessage("HideProgressDialog");
                    if (response.body() != null && response.isSuccessful()) {
                        viewModel.onGetObjectDosen(response.body());
                    } else {
                        viewModel.onMessage("EmptyObject");
                    }

                }

                @Override
                public void onFailure(Call<Dosen> call, Throwable t) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onMessage(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }
    }

    public void getMahasiswaSidang(String token){
        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("ShowProgressDialog");
            ApiService apiInterfaceDosen = ApiClient.getApiClient().create(ApiService.class);
            Call<List<Mahasiswa>> call = apiInterfaceDosen.getMahasiswaSidang("Bearer "+token);
            call.enqueue(new Callback<List<Mahasiswa>>() {
                @Override
                public void onResponse(Call<List<Mahasiswa>> call, Response<List<Mahasiswa>> response) {
                    if (response.body() != null && response.isSuccessful()) {
                        viewModel.onMessage("HideProgressDialog");
                        if (response.body().size() > 0){
                            viewModel.onGetListMahasiswa(response.body());
                        }else{
                            viewModel.onMessage("EmptyList");
                        }
                    } else {
                        call.clone().enqueue(this);
                    }

                }

                @Override
                public void onFailure(Call<List<Mahasiswa>> call, Throwable t) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onMessage(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }
    }

    public void getMahasiswaSidangByUsername(String token, String username){
        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("ShowProgressDialog");
            ApiService apiInterfaceDosen = ApiClient.getApiClient().create(ApiService.class);
            Call<Mahasiswa> call = apiInterfaceDosen.getMahasiswaSidangByUsername("Bearer "+token,username);
            call.enqueue(new Callback<Mahasiswa>() {
                @Override
                public void onResponse(Call<Mahasiswa> call, Response<Mahasiswa> response) {
                    if (response.body() != null && response.isSuccessful()) {
                        viewModel.onMessage("HideProgressDialog");
                        if (response.body() != null){
                            viewModel.onGetObjectMahasiswa(response.body());
                        }else{
                            viewModel.onMessage("EmptyObject");
                        }
                    } else {
                        call.clone().enqueue(this);
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
}
