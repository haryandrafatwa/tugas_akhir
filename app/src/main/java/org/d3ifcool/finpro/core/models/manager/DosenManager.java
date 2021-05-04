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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DosenManager {

    private DosenContract.ViewModel viewModel;

    private ConnectionHelper connectionHelper = new ConnectionHelper();
    private Context context;
    private SessionManager sessionManager;

    public void initContext(Context context){
        this.context = context;
        sessionManager = new SessionManager(context);
    }

    public DosenManager(DosenContract.ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void getDosen(){

        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("ShowProgressDialog");
            ApiService apiInterface = ApiClient.getApiClient().create(ApiService.class);
            Call<List<Dosen>> call = apiInterface.getDosen("Bearer "+sessionManager.getSessionToken());
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

    public void createDosen(String nip, String nama, String kode){

        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("ShowProgressDialog");
            ApiService apiInterfaceDosen = ApiClient.getApiClient().create(ApiService.class);
            Call<Dosen> call = apiInterfaceDosen.createDosen("Bearer "+sessionManager.getSessionToken(),nip,nama,kode);
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

    public void deleteDosen(String nip){

        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("ShowProgressDialog");

            ApiService apiInterfaceDosen = ApiClient.getApiClient().create(ApiService.class);
            Call<Dosen> call = apiInterfaceDosen.deleteDosen(nip);
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

    public void updateDosen(String nip_dosen, String dsn_nama, String dsn_kode, String dsn_kontak, String dsn_email, int batas_bimbingan, int batas_reviewer) {

        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("ShowProgressDialog");
            ApiService apiInterfaceDosen = ApiClient.getApiClient().create(ApiService.class);
            Call<Dosen> call = apiInterfaceDosen.updateDosen(nip_dosen, dsn_nama, dsn_kode, dsn_kontak, dsn_email, batas_bimbingan, batas_reviewer);
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

    public void getDosenByParameter(String dsn_nip){

        if (connectionHelper.isConnected(context)){
            ApiService apiInterfaceDosen = ApiClient.getApiClient().create(ApiService.class);
            Call<Dosen> call = apiInterfaceDosen.getDosenByParameter(dsn_nip);
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
}
