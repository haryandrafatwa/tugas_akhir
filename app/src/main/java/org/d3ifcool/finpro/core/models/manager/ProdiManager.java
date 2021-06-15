package org.d3ifcool.finpro.core.models.manager;

import android.content.Context;
import android.widget.Toast;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.api.ApiClient;
import org.d3ifcool.finpro.core.api.ApiService;
import org.d3ifcool.finpro.core.helpers.ConnectionHelper;
import org.d3ifcool.finpro.core.interfaces.DosenContract;
import org.d3ifcool.finpro.core.interfaces.ProdiContract;
import org.d3ifcool.finpro.core.models.Koordinator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class ProdiManager {

    private ProdiContract.ViewModel viewModel;

    private ConnectionHelper connectionHelper = new ConnectionHelper();
    private Context context;

    public void initContext(Context context){
        this.context = context;
    }

    public ProdiManager(ProdiContract.ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void createProdi(String koor_nip , String koor_nama , String koor_kontak, String koor_foto, String koor_email){

        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("showProgress");
            ApiService interfaceAdmin = ApiClient.getApiClient().create(ApiService.class);
            Call<Koordinator>call = interfaceAdmin.createKoor(koor_nip,koor_nama,koor_kontak,koor_foto,koor_email);
            call.enqueue(new Callback<Koordinator>() {
                @Override
                public void onResponse(Call<Koordinator> call, Response<Koordinator> response) {
                    viewModel.onMessage("hideProgress");
                    viewModel.onMessage("onSuccess");
                }

                @Override
                public void onFailure(Call<Koordinator> call, Throwable t) {
                    viewModel.onMessage("hideProgress");
                    viewModel.onMessage(t.getMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

    public void updateProdi(String token, String username, String koor_nip, String koor_nama, String koor_kode, String koor_kontak, String koor_email){

        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("showProgress");
            ApiService interfaceAdmin = ApiClient.getApiClient().create(ApiService.class);
            Call<Koordinator>call = interfaceAdmin.updateKoor("Bearer "+token, username, koor_nip, koor_nama, koor_kode, koor_kontak,koor_email);
            call.enqueue(new Callback<Koordinator>() {
                @Override
                public void onResponse(Call<Koordinator> call, Response<Koordinator> response) {
                    viewModel.onMessage("dismissProgress");
                    viewModel.onMessage("onSuccess");
                }

                @Override
                public void onFailure(Call<Koordinator> call, Throwable t) {
                    viewModel.onMessage("dismissProgress");
                    viewModel.onMessage(t.getMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

    public void getProdi(){

        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("showProgress");
            ApiService interfaceAdmin = ApiClient.getApiClient().create(ApiService.class);
            Call<List<Koordinator>> call = interfaceAdmin.getKoor();
            call.enqueue(new Callback<List<Koordinator>>() {
                @Override
                public void onResponse(Call<List<Koordinator>> call, Response<List<Koordinator>> response) {
                    viewModel.onMessage("hideProgress");
                    if (response.body() != null && response.isSuccessful()) {
//                        viewModel.onGetListProdi(response.body());
                    } else {
                        viewModel.onMessage("EmptyList");
                    }

                }

                @Override
                public void onFailure(Call<List<Koordinator>> call, Throwable t) {
                    viewModel.onMessage("hideProgress");
                    viewModel.onMessage(t.getMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }



    }

    public void deleteProdi(String koor_nip){

        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("showProgress");
            ApiService apiInterfaceKoorPa = ApiClient.getApiClient().create(ApiService.class);
            Call<Koordinator>call = apiInterfaceKoorPa.deleteKoor(koor_nip);
            call.enqueue(new Callback<Koordinator>() {
                @Override
                public void onResponse(Call<Koordinator> call, Response<Koordinator> response) {
                    viewModel.onMessage("dismissProgress");
                    viewModel.onMessage("onSuccess");
                }

                @Override
                public void onFailure(Call<Koordinator> call, Throwable t) {
                    viewModel.onMessage("dismissProgress");
                    viewModel.onMessage(t.getMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

    public void getProdiByParameter(String token, String koor_nip){

        if (connectionHelper.isConnected(context)){
            ApiService apiInterfaceKoorPa = ApiClient.getApiClient().create(ApiService.class);
            Call<Koordinator> call = apiInterfaceKoorPa.getKoorByParameter("Bearer "+token,koor_nip);
            call.enqueue(new Callback<Koordinator>() {
                @Override
                public void onResponse(Call<Koordinator> call, Response<Koordinator> response) {
                    viewModel.onMessage("dismissProgress");
                    if (response.body() != null && response.isSuccessful()) {
                        viewModel.onGetObjectProdi(response.body());
                    } else {
                        viewModel.onMessage("EmptyObject");
                    }

                }

                @Override
                public void onFailure(Call<Koordinator> call, Throwable t) {
                    viewModel.onMessage("dismissProgress");
                    viewModel.onMessage(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

}

