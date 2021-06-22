package org.d3ifcool.finpro.core.presenters;

import android.content.Intent;
import android.text.TextUtils;

import androidx.databinding.ObservableField;

import org.d3ifcool.finpro.App;
import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.Constant;
import org.d3ifcool.finpro.core.interfaces.DosenContract;
import org.d3ifcool.finpro.core.models.Dosen;
import org.d3ifcool.finpro.core.models.manager.DosenManager;
import org.d3ifcool.finpro.prodi.activities.editor.ProdiDosenEditorActivity;

public class DosenPresenter implements DosenContract.Presenter {

    public ObservableField<String> nip;
    public ObservableField<String> nama;
    public ObservableField<String> kode;
    public ObservableField<Integer> kuota_bimbingan;
    public ObservableField<Integer> kuota_reviewer;
    public ObservableField<String> email;
    public ObservableField<String> kontak;

    private DosenContract.ViewModel viewModel;
    private DosenManager dosenManager;

    public DosenPresenter(DosenContract.ViewModel viewModel) {
        this.viewModel = viewModel;
        dosenManager = new DosenManager(viewModel);
        dosenManager.initContext(App.self());
        intFields();
    }

    private void intFields(){
        nama = new ObservableField<>();
        nip = new ObservableField<>();
        kode = new ObservableField<>();
        kuota_bimbingan = new ObservableField<>();
        kuota_reviewer = new ObservableField<>();
        email = new ObservableField<>();
        kontak = new ObservableField<>();
    }

    private boolean isValidate(){
        if (TextUtils.isEmpty(nama.get())){
            viewModel.onMessage("Nama Dosen "+App.self().getString(R.string.text_tidak_boleh_kosong));
            return false;
        }

        if (TextUtils.isEmpty(nip.get())){
            viewModel.onMessage("NIP Dosen "+App.self().getString(R.string.text_tidak_boleh_kosong));
            return false;
        }

        if (TextUtils.isEmpty(kode.get())){
            viewModel.onMessage("Kode Dosen "+App.self().getString(R.string.text_tidak_boleh_kosong));
            return false;
        }
        return true;
    }

    @Override
    public void onCreate() {
        if (isValidate()){
            viewModel.onMessage("AddButton");
        }
    }

    @Override
    public void getCurrentDosen(String token) {
        dosenManager.getCurrentDosen(token);
    }

    @Override
    public void getAllDosen(String token) {
        dosenManager.getAllDosen(token);
    }

    @Override
    public void createDosen(String token) {
        dosenManager.createDosen(token, nip.get(), nama.get(), kode.get());
    }

    @Override
    public void deleteDosen(String token, String dsn_nip) {
        dosenManager.deleteDosen(token, dsn_nip);
    }

    @Override
    public void updateDosen(String token) {
        if (isValidate()){
            dosenManager.updateDosen(token, nip.get(), nama.get(), kode.get(), kontak.get(), email.get(), kuota_bimbingan.get(), kuota_reviewer.get());
        }
    }

    @Override
    public void getDosenByNIP(String token, String dsn_nip) {
        dosenManager.getDosenByParameter(token,dsn_nip);
    }

    public Intent toolbarIntent(Dosen dosen){
        Intent intent = new Intent(App.self(), ProdiDosenEditorActivity.class);
        intent.putExtra(Constant.ObjectConstanta.EXTRA_DOSEN,dosen);
        return intent;
    }

    public void floatButton(){
        viewModel.onMessage("FloatButton");
    }
}
