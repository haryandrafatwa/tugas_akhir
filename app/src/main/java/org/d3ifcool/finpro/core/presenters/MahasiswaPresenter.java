package org.d3ifcool.finpro.core.presenters;

import android.content.Intent;
import android.text.TextUtils;

import androidx.databinding.ObservableField;

import org.d3ifcool.finpro.App;
import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.Constant;
import org.d3ifcool.finpro.core.interfaces.MahasiswaContract;
import org.d3ifcool.finpro.core.models.Mahasiswa;
import org.d3ifcool.finpro.core.models.manager.MahasiswaManager;
import org.d3ifcool.finpro.prodi.activities.editor.ProdiMahasiswaEditorActivity;

import okhttp3.MultipartBody;

public class MahasiswaPresenter implements MahasiswaContract.Presenter {

    public ObservableField<String> nama;
    public ObservableField<String> nim;
    public ObservableField<String> judul;
    public ObservableField<String> judulInggris;
    public ObservableField<String> angkatan;
    public ObservableField<String> telp;
    public ObservableField<String> email;
    private MahasiswaContract.ViewModel viewModel;
    private MahasiswaManager mahasiswaManager;

    public MahasiswaPresenter(MahasiswaContract.ViewModel viewModel) {
        this.viewModel = viewModel;
        mahasiswaManager = new MahasiswaManager(viewModel);
        mahasiswaManager.initContext(App.self());
        intFields();
    }

    private void intFields(){
        nama = new ObservableField<>();
        nim = new ObservableField<>();
        judul = new ObservableField<>();
        judulInggris = new ObservableField<>();
        angkatan = new ObservableField<>();
        telp = new ObservableField<>();
        email = new ObservableField<>();
    }

    private boolean isValidateUpdate(){
        if (TextUtils.isEmpty(nama.get())){
            viewModel.onMessage("Nama "+App.self().getString(R.string.text_tidak_boleh_kosong));
            return false;
        }

        if (TextUtils.isEmpty(nim.get())){
            viewModel.onMessage("NIM "+App.self().getString(R.string.text_tidak_boleh_kosong));
            return false;
        }

        if (TextUtils.isEmpty(angkatan.get())){
            viewModel.onMessage("Angkatan "+App.self().getString(R.string.text_tidak_boleh_kosong));
            return false;
        }

        if (TextUtils.isEmpty(judul.get())){
            viewModel.onMessage("Judul "+App.self().getString(R.string.text_tidak_boleh_kosong));
            return false;
        }

        if (TextUtils.isEmpty(judulInggris.get())){
            viewModel.onMessage("Judul Inggris "+App.self().getString(R.string.text_tidak_boleh_kosong));
            return false;
        }
        return true;
    }

    private boolean isValidateCreate(){
        if (TextUtils.isEmpty(nama.get())){
            viewModel.onMessage("Nama "+App.self().getString(R.string.text_tidak_boleh_kosong));
            return false;
        }

        if (TextUtils.isEmpty(nim.get())){
            viewModel.onMessage("NIM "+App.self().getString(R.string.text_tidak_boleh_kosong));
            return false;
        }

        if (TextUtils.isEmpty(angkatan.get())){
            viewModel.onMessage("Angkatan "+App.self().getString(R.string.text_tidak_boleh_kosong));
            return false;
        }
        return true;
    }

    @Override
    public void onCreate() {
        if (isValidateCreate()){
            viewModel.onMessage("AddButton");
        }
    }

    @Override
    public void getAllMahasiswa(String token) {
        mahasiswaManager.getMahasiswa(token);
    }

    @Override
    public void createMahasiswa(String token) {
        if (isValidateCreate()){
            mahasiswaManager.createMahasiswa(token,nim.get(),nama.get());
        }
    }

    @Override
    public void deleteMahasiswa(String token, String nim) {
        mahasiswaManager.deleteMahasiswa(token,nim);
    }

    @Override
    public void updateMahasiswa(String token) {
        if (isValidateUpdate()) {
            mahasiswaManager.updateMahasiswa(token, nim.get(),nama.get(), angkatan.get(), telp.get(), email.get(), judul.get(), judulInggris.get());
        }
    }

    @Override
    public void getMahasiswaByNIM(String token, String mhs_nim) {
        mahasiswaManager.getMahasiswaByParameter(token,mhs_nim);
    }

    @Override
    public void getPembimbing(String token, int plot_id) {
        mahasiswaManager.getPembimbing(token,plot_id);
    }

    @Override
    public void updateSKTA(String token, String mhs_nim, MultipartBody.Part part) {
        mahasiswaManager.updateSKTA(token, mhs_nim,part);
    }

    public void btnSKUpdate() {
        viewModel.onMessage("btnSkUpdate");
    }

    public void floatButton(){
        viewModel.onMessage("FloatButton");
    }

    public Intent toolbarIntent(Mahasiswa mahasiswa){
        Intent intent = new Intent(App.self(), ProdiMahasiswaEditorActivity.class);
        intent.putExtra(Constant.ObjectConstanta.EXTRA_MAHASISWA,mahasiswa);
        return intent;
    }
}
