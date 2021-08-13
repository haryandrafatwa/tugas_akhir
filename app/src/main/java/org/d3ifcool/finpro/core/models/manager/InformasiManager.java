package org.d3ifcool.finpro.core.models.manager;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.api.ApiClient;
import org.d3ifcool.finpro.core.api.ApiService;
import org.d3ifcool.finpro.core.helpers.ConnectionHelper;
import org.d3ifcool.finpro.core.interfaces.InformasiContract;
import org.d3ifcool.finpro.core.models.Informasi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InformasiManager {
    
    private InformasiContract.ViewModel viewModel;

    private ConnectionHelper connectionHelper = new ConnectionHelper();
    private Context context;

    public void initContext(Context context){
        this.context = context;
    }

    public InformasiManager(InformasiContract.ViewModel view) {
        this.viewModel = view;
    }

    public void createInformasi (String token, String informasi_judul, String informasi_isi, String penerbit) {

        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("ShowProgressDialog");
            ApiService apiInterface = ApiClient.getApiClient().create(ApiService.class);
            Call<Informasi> call = apiInterface.createInformasi("Bearer "+token, informasi_judul, informasi_isi, penerbit);
            call.enqueue(new Callback<Informasi>() {
                @Override
                public void onResponse(Call<Informasi> call, Response<Informasi> response) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onMessage("onSuccess");
                }

                @Override
                public void onFailure(Call<Informasi> call, Throwable t) {
                    call.clone().enqueue(this);
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }

    }


    public void updateInformasi (String token, int informasi_id, String informasi_judul, String informasi_isi) {
        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("ShowProgressDialog");
            ApiService apiInterface = ApiClient.getApiClient().create(ApiService.class);
            Call<Informasi> call = apiInterface.updateInformasi("Bearer "+token, informasi_id, informasi_judul, informasi_isi);
            call.enqueue(new Callback<Informasi>() {
                @Override
                public void onResponse(Call<Informasi> call, Response<Informasi> response) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onMessage("onSuccess");
                }

                @Override
                public void onFailure(Call<Informasi> call, Throwable t) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onMessage(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteInformasi(String token, int informasi_id) {
        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("ShowProgressDialog");
            ApiService apiInterface = ApiClient.getApiClient().create(ApiService.class);
            Call<Informasi> call = apiInterface.deleteInformasi("Bearer "+token, informasi_id);
            call.enqueue(new Callback<Informasi>() {
                @Override
                public void onResponse(Call<Informasi> call, Response<Informasi> response) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onMessage("onSuccess");
                }

                @Override
                public void onFailure(Call<Informasi> call, Throwable t) {
                    viewModel.onMessage("ShowProgressDialog");
                    viewModel.onMessage(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }

    }

    public void getInformasi (String token){
        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("ShowProgressDialog");
            ApiService apiInterface = ApiClient.getApiClient().create(ApiService.class);
            Call<List<Informasi>> call = apiInterface.getInformasi("Bearer "+token);
            call.enqueue(new Callback<List<Informasi>>() {
                @Override
                public void onResponse(Call<List<Informasi>> call, Response<List<Informasi>> response) {
                    viewModel.onMessage("HideProgressDialog");
                    if (response.body() != null && response.isSuccessful()) {
                        viewModel.onGetListInformasi(response.body());
                    } else {
                        viewModel.onMessage("EmptyList");
                    }
                }

                @Override
                public void onFailure(Call<List<Informasi>> call, Throwable t) {
                    viewModel.onMessage("HideProgressDialog");
//                    viewModel.onMessage(t.getLocalizedMessage());
                    call.clone().enqueue(this);
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }
    }

}
