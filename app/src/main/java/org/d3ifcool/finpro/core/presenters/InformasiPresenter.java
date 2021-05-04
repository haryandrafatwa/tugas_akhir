package org.d3ifcool.finpro.core.presenters;


import android.content.Intent;
import android.text.TextUtils;

import androidx.databinding.ObservableField;

import org.d3ifcool.finpro.App;
import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.interfaces.InformasiContract;
import org.d3ifcool.finpro.core.models.Informasi;
import org.d3ifcool.finpro.core.models.manager.InformasiManager;
import org.d3ifcool.finpro.prodi.activities.editor.update.KoorInformasiUbahActivity;

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
    public void getAllInformasi() {
        informasiManager.getInformasi();
    }

    @Override
    public void createInformasi() {
        if (isValidate()){
            informasiManager.createInformasi(judul.get(),deskripsi.get(),penerbit);
        }
    }

    @Override
    public void deleteInformasi(int id) {
        informasiManager.deleteInformasi(id);
    }

    @Override
    public void updateInformasi(int id, String judul, String text) {
        informasiManager.updateInformasi(id,judul,text);
    }

    public void floatClick(){
        viewModel.onMessage("FloatButton");
    }

    public Intent toolbarIntent(Informasi informasi){
        Intent intent = new Intent(App.self(), KoorInformasiUbahActivity.class);
        intent.putExtra("extra_informasi",informasi);
        return intent;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }
}