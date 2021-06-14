package org.d3ifcool.finpro.core.presenters;


import android.content.Intent;
import android.text.TextUtils;

import androidx.databinding.ObservableField;

import org.d3ifcool.finpro.App;
import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.activities.detail.InformasiUbahActivity;
import org.d3ifcool.finpro.core.interfaces.InformasiContract;
import org.d3ifcool.finpro.core.interfaces.PlottingContract;
import org.d3ifcool.finpro.core.models.Informasi;
import org.d3ifcool.finpro.core.models.manager.InformasiManager;
import org.d3ifcool.finpro.core.models.manager.PlottingManager;

import okhttp3.MultipartBody;

public class PlottingPresenter implements PlottingContract.Presenter {

    private PlottingManager plottingManager;
    private PlottingContract.ViewModel viewModel;

    public PlottingPresenter(PlottingContract.ViewModel viewModel) {
        this.viewModel = viewModel;
        plottingManager = new PlottingManager(viewModel);
        plottingManager.initContext(App.self());
    }

    public void onRefresh(String token){
        getAllPlotting(token);
        checkFrom(token);
    }

    public void onDownload(String token){
        viewModel.onMessage("ShowProgressDialog");
        plottingManager.downloadFormPlot(token);
    }

    public void showProgress(){
        viewModel.onMessage("ShowProgressDialog");
    }

    public void dismissProgress(){
        viewModel.onMessage("HideProgressDialog");
    }

    public void onIntent(){
        viewModel.onMessage("onIntent");
    }

    public void onDelete(){
        viewModel.onMessage("DeleteFormPlot");
    }

    public void onUpload(){
        viewModel.onMessage("UploadFormPlot");
    }

    public void onCreate(){
        viewModel.onMessage("AddButton");
    }

    @Override
    public void getAllPlotting(String token) {
        plottingManager.getPlotting(token);
    }

    @Override
    public void createPlotting(String token, String dosen1, String dosen2) {
        plottingManager.createPlotting(token, dosen1, dosen2);
    }

    @Override
    public void deletePlotting(String token, int id) {
        plottingManager.deletePlotting(token,id);
    }

    @Override
    public void uploadForm(String token, MultipartBody.Part part) {
        plottingManager.uploadFormPlotting(token,part);
    }

    @Override
    public void deleteForm(String token) {
        plottingManager.deleteFormPlot(token);
    }

    @Override
    public void updatePlotting(String token, int id, String dosen1, String dosen2) {
        plottingManager.updatePlotting(token,id,dosen1,dosen2);
    }

    @Override
    public void checkFrom(String token) {
        plottingManager.checkFormPlot(token);
    }
}