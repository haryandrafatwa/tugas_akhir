package org.d3ifcool.finpro.core.presenters;


import android.content.Intent;
import android.text.TextUtils;

import androidx.databinding.ObservableField;

import org.d3ifcool.finpro.App;
import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.activities.detail.InformasiUbahActivity;
import org.d3ifcool.finpro.core.interfaces.InformasiContract;
import org.d3ifcool.finpro.core.models.Informasi;
import org.d3ifcool.finpro.core.models.manager.InformasiManager;

public class InformasiPresenter implements InformasiContract.Presenter {

    public ObservableField<String> judul;
    public ObservableField<String> deskripsi;
    private String penerbit;

    private InformasiManager informasiManager;
    private InformasiContract.ViewModel viewModel;

    public InformasiPresenter(InformasiContract.ViewModel viewModel) {
        this.viewModel = viewModel;
        informasiManager = new InformasiManager(viewModel);
        informasiManager.initContext(App.self());
        intFields();
    }

    private void intFields(){
        judul = new ObservableField<>();
        deskripsi = new ObservableField<>();
    }

    private boolean isValidate(){
        if (TextUtils.isEmpty(judul.get())){
            viewModel.onMessage("Judul "+App.self().getString(R.string.text_tidak_boleh_kosong));
            return false;
        }

        if (TextUtils.isEmpty(deskripsi.get())){
            viewModel.onMessage("Informasi "+App.self().getString(R.string.text_tidak_boleh_kosong));
            return false;
        }
        return true;
    }

    @Override
    public void getAllInformasi(String token) {
        informasiManager.getInformasi(token);
    }

    @Override
    public void createInformasi(String token) {
        if (isValidate()){
            informasiManager.createInformasi(token,judul.get(),deskripsi.get(),penerbit);
        }
    }

    @Override
    public void deleteInformasi(String token, int id) {
        informasiManager.deleteInformasi(token,id);
    }

    @Override
    public void updateInformasi(String token, int id) {
        informasiManager.updateInformasi(token, id,judul.get(), deskripsi.get());
    }

    public void buttonChange(){
        if (isValidate()){
            viewModel.onMessage("AlertUbah");
        }
    }

    public void floatClick(){
        viewModel.onMessage("FloatButton");
    }

    public Intent toolbarIntent(Informasi informasi){
        Intent intent = new Intent(App.self(), InformasiUbahActivity.class);
        intent.putExtra("extra_informasi",informasi);
        return intent;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    public void setJudul(String judul) {
        this.judul.set(judul);
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi.set(deskripsi);
    }
}