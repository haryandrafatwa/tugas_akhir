package org.d3ifcool.finpro.core.models.manager;

import android.content.Context;
import android.widget.Toast;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.api.ApiClient;
import org.d3ifcool.finpro.core.api.ApiService;
import org.d3ifcool.finpro.core.helpers.ConnectionHelper;
import org.d3ifcool.finpro.core.interfaces.InformasiContract;
import org.d3ifcool.finpro.core.interfaces.JadwalContract;
import org.d3ifcool.finpro.core.models.Informasi;
import org.d3ifcool.finpro.core.models.JadwalKegiatan;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JadwalKegiatanManager {

    private JadwalContract.ViewModel viewModel;

    private ConnectionHelper connectionHelper = new ConnectionHelper();
    private Context context;

    public void initContext(Context context){
        this.context = context;
    }

    public JadwalKegiatanManager(JadwalContract.ViewModel view) {
        this.viewModel = view;
    }

    public void createJadwal (String token, String nama_kegiatan, String tgl_mulai, String tgl_akhir) {
        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("ShowProgressDialog");
            ApiService apiInterface = ApiClient.getApiClient().create(ApiService.class);
            Call<JadwalKegiatan> call = apiInterface.createJadwal("Bearer "+token, nama_kegiatan, tgl_mulai, tgl_akhir);
            call.enqueue(new Callback<JadwalKegiatan>() {
                @Override
                public void onResponse(Call<JadwalKegiatan> call, Response<JadwalKegiatan> response) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onMessage("onSuccess");
                }

                @Override
                public void onFailure(Call<JadwalKegiatan> call, Throwable t) {
                    call.clone().enqueue(this);
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }

    }

    public void updateJadwal(String token, int jadwal_id, String nama_kegiatan, String tgl_mulai, String tgl_akhir) {
        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("ShowProgressDialog");
            ApiService apiInterface = ApiClient.getApiClient().create(ApiService.class);
            Call<JadwalKegiatan> call = apiInterface.updateJadwal("Bearer "+token, jadwal_id, nama_kegiatan, tgl_mulai, tgl_akhir);
            call.enqueue(new Callback<JadwalKegiatan>() {
                @Override
                public void onResponse(Call<JadwalKegiatan> call, Response<JadwalKegiatan> response) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onMessage("onSuccess");
                }

                @Override
                public void onFailure(Call<JadwalKegiatan> call, Throwable t) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onMessage(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteJadwal(String token, int jadwal_id) {
        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("ShowProgressDialog");
            ApiService apiInterface = ApiClient.getApiClient().create(ApiService.class);
            Call<JadwalKegiatan> call = apiInterface.deleteJadwal("Bearer "+token, jadwal_id);
            call.enqueue(new Callback<JadwalKegiatan>() {
                @Override
                public void onResponse(Call<JadwalKegiatan> call, Response<JadwalKegiatan> response) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onMessage("onSuccess");
                }

                @Override
                public void onFailure(Call<JadwalKegiatan> call, Throwable t) {
                    viewModel.onMessage("ShowProgressDialog");
                    viewModel.onMessage(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }

    }

    public void getAllJadwal (String token){
        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("ShowProgressDialog");
            ApiService apiInterface = ApiClient.getApiClient().create(ApiService.class);
            Call<List<JadwalKegiatan>> call = apiInterface.getJadwal("Bearer "+token);
            call.enqueue(new Callback<List<JadwalKegiatan>>() {
                @Override
                public void onResponse(Call<List<JadwalKegiatan>> call, Response<List<JadwalKegiatan>> response) {
                    viewModel.onMessage("HideProgressDialog");
                    if (response.body() != null && response.isSuccessful()) {
                        viewModel.onGetListJadwal(response.body());
                    } else {
                        viewModel.onMessage("EmptyList");
                    }
                }

                @Override
                public void onFailure(Call<List<JadwalKegiatan>> call, Throwable t) {
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
