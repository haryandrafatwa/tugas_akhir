package org.d3ifcool.finpro.core.presenters;

import android.content.Context;
import android.widget.Toast;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.ConnectionHelper;
import org.d3ifcool.finpro.core.interfaces.NotifikasiContract;
import org.d3ifcool.finpro.core.models.Notifikasi;
import org.d3ifcool.finpro.core.api.ApiClient;
import org.d3ifcool.finpro.core.api.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotifikasiPresenter {

    private NotifikasiContract.ViewModel viewModel;

    private ConnectionHelper connectionHelper = new ConnectionHelper();
    private Context context;

    public void initContext(Context context){
        this.context = context;
    }

    public NotifikasiPresenter(NotifikasiContract.ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void createNotifikasi(String token, String notifikasi_kategori, String notifikasi_deskripsi, String notifikasi_dari, String notifikasi_untuk){

        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("ShowProgressDialog");
            ApiService apiInterfaceNotifikasi = ApiClient.getApiClient().create(ApiService.class);
            Call<Notifikasi> call = apiInterfaceNotifikasi.createNotifikasi("Bearer "+token, notifikasi_kategori, notifikasi_deskripsi, notifikasi_dari, notifikasi_untuk);
            call.enqueue(new Callback<Notifikasi>() {
                @Override
                public void onResponse(Call<Notifikasi> call, Response<Notifikasi> response) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onMessage("onSuccessCreateNotif");
                }

                @Override
                public void onFailure(Call<Notifikasi> call, Throwable t) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onMessage(t.getMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

    public void getNotifikasi(){

        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("ShowProgressDialog");
            ApiService apiInterfaceNotifikasi = ApiClient.getApiClient().create(ApiService.class);
            Call<List<Notifikasi>> call = apiInterfaceNotifikasi.getNotifikasi();
            call.enqueue(new Callback<List<Notifikasi>>() {
                @Override
                public void onResponse(Call<List<Notifikasi>> call, Response<List<Notifikasi>> response) {
                    viewModel.onMessage("HideProgressDialog");
                    if (response.body() != null && response.isSuccessful()) {
                        viewModel.onGetListNotifikasi(response.body());
                    } else {
                        viewModel.onMessage("EmptyList");
                    }

                }

                @Override
                public void onFailure(Call<List<Notifikasi>> call, Throwable t) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onMessage(t.getMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

    public void searchNotifikasiBy(String parameter, String query){

        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("ShowProgressDialog");
            ApiService apiInterfaceNotifikasi = ApiClient.getApiClient().create(ApiService.class);
            Call<List<Notifikasi>> call = apiInterfaceNotifikasi.searchNotifikasiBy(parameter, query);
            call.enqueue(new Callback<List<Notifikasi>>() {
                @Override
                public void onResponse(Call<List<Notifikasi>> call, Response<List<Notifikasi>> response) {
                    viewModel.onMessage("HideProgressDialog");
                    if (response.body() != null && response.isSuccessful()) {
                        viewModel.onGetListNotifikasi(response.body());
                    } else {
                        viewModel.onMessage("EmptyList");
                    }

                }

                @Override
                public void onFailure(Call<List<Notifikasi>> call, Throwable t) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onMessage(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

    public void searchNotifikasiBy2(String parameter1, String query1, String parameter2, String query2){

        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("ShowProgressDialog");
            ApiService apiInterfaceNotifikasi = ApiClient.getApiClient().create(ApiService.class);
            Call<List<Notifikasi>> call = apiInterfaceNotifikasi.searchNotifikasiBy2(parameter1, query1, parameter2, query2);
            call.enqueue(new Callback<List<Notifikasi>>() {
                @Override
                public void onResponse(Call<List<Notifikasi>> call, Response<List<Notifikasi>> response) {
                    viewModel.onMessage("HideProgressDialog");
                    if (response.body() != null && response.isSuccessful()) {
                        viewModel.onGetListNotifikasi(response.body());
                    } else {
                        viewModel.onMessage("EmptyList");
                    }

                }

                @Override
                public void onFailure(Call<List<Notifikasi>> call, Throwable t) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onMessage(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

    public void sortNotifikasi(String query1, String query2){

        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("ShowProgressDialog");
            ApiService apiInterfaceNotifikasi = ApiClient.getApiClient().create(ApiService.class);
            Call<List<Notifikasi>> call = apiInterfaceNotifikasi.sortNotifikasi(query1, query2);
            call.enqueue(new Callback<List<Notifikasi>>() {
                @Override
                public void onResponse(Call<List<Notifikasi>> call, Response<List<Notifikasi>> response) {
                    viewModel.onMessage("HideProgressDialog");
                    if (response.body() != null && response.isSuccessful()) {
                        viewModel.onGetListNotifikasi(response.body());
                    } else {
                        viewModel.onMessage("EmptyList");
                    }

                }

                @Override
                public void onFailure(Call<List<Notifikasi>> call, Throwable t) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onMessage(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

    public void deleteNotifikasi(int notifikasi_id){

        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("ShowProgressDialog");
            ApiService apiInterfaceNotifikasi = ApiClient.getApiClient().create(ApiService.class);
            Call<Notifikasi> call = apiInterfaceNotifikasi.deleteNotifikasi(notifikasi_id);
            call.enqueue(new Callback<Notifikasi>() {
                @Override
                public void onResponse(Call<Notifikasi> call, Response<Notifikasi> response) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onMessage("onSuccessCreateNotif");
                }

                @Override
                public void onFailure(Call<Notifikasi> call, Throwable t) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onMessage(t.getMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }


    public void updateNotifikasi(int notifikasi_id, int notifikasi_baca){

        if (connectionHelper.isConnected(context)){
            ApiService apiInterfaceNotifikasi = ApiClient.getApiClient().create(ApiService.class);
            Call<Notifikasi> call = apiInterfaceNotifikasi.updateNotifikasi(notifikasi_id, notifikasi_baca);
            call.enqueue(new Callback<Notifikasi>() {
                @Override
                public void onResponse(Call<Notifikasi> call, Response<Notifikasi> response) {
                    viewModel.onMessage("onSuccessCreateNotif");
                }

                @Override
                public void onFailure(Call<Notifikasi> call, Throwable t) {
                    viewModel.onMessage(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

}
