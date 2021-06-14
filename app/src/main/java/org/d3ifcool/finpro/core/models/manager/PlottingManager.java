package org.d3ifcool.finpro.core.models.manager;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.api.ApiClient;
import org.d3ifcool.finpro.core.api.ApiService;
import org.d3ifcool.finpro.core.helpers.ConnectionHelper;
import org.d3ifcool.finpro.core.helpers.SessionManager;
import org.d3ifcool.finpro.core.interfaces.PlottingContract;
import org.d3ifcool.finpro.core.interfaces.lists.PlottingListView;
import org.d3ifcool.finpro.core.interfaces.works.PlottingWorkView;
import org.d3ifcool.finpro.core.models.Plotting;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlottingManager {
    private PlottingContract.ViewModel viewModel;

    private ConnectionHelper connectionHelper = new ConnectionHelper();
    private Context context;
    private ApiService apiInterface;

    public void initContext(Context context){
        this.context = context;
        apiInterface = ApiClient.getApiClient().create(ApiService.class);
    }

    public PlottingManager(PlottingContract.ViewModel viewMain) {
        this.viewModel = viewMain;
    }

    public void createPlotting (String token, String nip_pembimbing_1, String nip_pembimbing_2) {
        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("ShowProgressDialog");
            Call<ResponseBody> call = apiInterface.createPlotting("Bearer "+token, nip_pembimbing_1, nip_pembimbing_2);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    viewModel.onMessage("HideProgressDialog");
                    if (response.isSuccessful()){
                        viewModel.onMessage("onSuccess");
                    }else{
                        try {
                            JSONObject jsonObject = new JSONObject(response.errorBody().string());
                            viewModel.onMessage(jsonObject.getString("message"));
                        } catch (IOException | JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onMessage(t.getMessage());
                    Log.e("FINPRO!", "onFailure: "+t.getMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }

    }

    public void updatePlotting (String token, int id, String nip_pembimbing_1, String nip_pembimbing_2) {
        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("ShowProgressDialog");
            Call<ResponseBody> call = apiInterface.updatePlotting("Bearer "+token, id, nip_pembimbing_1, nip_pembimbing_2);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    viewModel.onMessage("HideProgressDialog");
                    if (response.isSuccessful()){
                        viewModel.onMessage("onSuccess");
                    }else{
                        try {
                            JSONObject jsonObject = new JSONObject(response.errorBody().string());
                            viewModel.onMessage(jsonObject.getString("message"));
                        } catch (IOException | JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onMessage(t.getMessage());
                    Log.e("FINPRO!", "onFailure: "+t.getMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }

    }

    public void deletePlotting (String token, int id){
        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("ShowProgressDialog");
            Call<ResponseBody> call = apiInterface.deletePlotting("Bearer "+token,id);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    viewModel.onMessage("HideProgressDialog");
                    if (response.isSuccessful()){
                        viewModel.onMessage("onSuccess");
                    }else{
                        try {
                            JSONObject jsonObject = new JSONObject(response.errorBody().string());
                            viewModel.onMessage(jsonObject.getString("message"));
                        } catch (IOException | JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onMessage(t.getMessage());
                    Log.e("FINPRO!", "onFailure: "+t.getMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }
    }

    public void getPlotting (String token){
        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("HideProgressDialog");
            Call<List<Plotting>> call = apiInterface.getPlotting("Bearer "+token);
            call.enqueue(new Callback<List<Plotting>>() {
                @Override
                public void onResponse(Call<List<Plotting>> call, Response<List<Plotting>> response) {
                    viewModel.onMessage("HideProgressDialog");
                    if (response.body() != null && response.isSuccessful()) {
                        viewModel.onGetListPlotting(response.body());
                    } else {
                        viewModel.onMessage("EmptyList");
                    }
                }

                @Override
                public void onFailure(Call<List<Plotting>> call, Throwable t) {
                    viewModel.onMessage("HideProgressDialog");
                    call.clone().enqueue(this);
//                    viewResult.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }
    }

    public void uploadFormPlotting (String token, MultipartBody.Part part){
        viewModel.onMessage("ShowProgressDialog");
        if (connectionHelper.isConnected(context)){
            Call<ResponseBody> call = apiInterface.uploadFormPlotting("Bearer "+token,part);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.body()!=null){
                        if (response.isSuccessful()){
                            viewModel.onMessage("HideProgressDialog");
                            viewModel.onMessage("onSuccess");
                        }
                    }else{
                        try {
                            viewModel.onMessage(response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    call.clone().enqueue(this);
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }
    }

    public void checkFormPlot (String token){
        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("ShowProgressDialog");
            Call<ResponseBody> call = apiInterface.checkFormPlot("Bearer "+token);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.body() != null){
                        if (response.isSuccessful()){
                            viewModel.onMessage("HideProgressDialog");
                            try {
                                JSONObject jsonObject = new JSONObject(response.body().string());
                                viewModel.onMessage(jsonObject.getString("message"));
                            } catch (JSONException|IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    call.clone().enqueue(this);
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }
    }

    public void downloadFormPlot (String token){
        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("ShowProgressDialog");
            Call<ResponseBody> call = apiInterface.downloadFormPlot("Bearer "+token);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    viewModel.onMessage("HideProgressDialog");
                    String filename = response.headers().get("Content-Disposition").split("=")[1];
                    viewModel.onGetBody(response.body(),filename);
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    call.clone().enqueue(this);
//                    viewResult.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteFormPlot (String token){
        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("ShowProgressDialog");
            Call<ResponseBody> call = apiInterface.deleteFormPlot("Bearer "+token);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    viewModel.onMessage("HideProgressDialog");
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        viewModel.onMessage(jsonObject.getString("message"));
                        viewModel.onMessage("onSuccess");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    call.clone().enqueue(this);
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }
    }

}
