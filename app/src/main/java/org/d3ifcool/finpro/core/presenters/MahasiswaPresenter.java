package org.d3ifcool.finpro.core.presenters;

import android.text.TextUtils;

import androidx.databinding.ObservableField;

import org.d3ifcool.finpro.App;
import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.interfaces.MahasiswaContract;
import org.d3ifcool.finpro.core.models.Mahasiswa;
import org.d3ifcool.finpro.core.models.manager.MahasiswaManager;

import okhttp3.MultipartBody;

public class MahasiswaPresenter implements MahasiswaContract.Presenter {


    public ObservableField<String> nama;
    public ObservableField<String> nim;
    public ObservableField<String> judul;
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
        angkatan = new ObservableField<>();
        telp = new ObservableField<>();
        email = new ObservableField<>();
    }

    private boolean isValidate(){
        if (TextUtils.isEmpty(nama.get())){
            viewModel.onFailed(App.self().getString(R.string.text_tidak_boleh_kosong));
            return false;
        }

        if (TextUtils.isEmpty(nim.get())){
            viewModel.onFailed(App.self().getString(R.string.text_tidak_boleh_kosong));
            return false;
        }

        if (TextUtils.isEmpty(judul.get())){
            viewModel.onFailed(App.self().getString(R.string.text_tidak_boleh_kosong));
            return false;
        }

        if (TextUtils.isEmpty(angkatan.get())){
            viewModel.onFailed(App.self().getString(R.string.text_tidak_boleh_kosong));
            return false;
        }

        if (TextUtils.isEmpty(telp.get())){
            viewModel.onFailed(App.self().getString(R.string.text_tidak_boleh_kosong));
            return false;
        }

        if (TextUtils.isEmpty(email.get())){
            viewModel.onFailed(App.self().getString(R.string.text_tidak_boleh_kosong));
            return false;
        }
        return true;
    }

    @Override
    public void getAllMahasiswa() {
        mahasiswaManager.getMahasiswa();
    }

    @Override
    public void createMahasiswa() {
        if (isValidate()){
            mahasiswaManager.createMahasiswa(nim.get(),nama.get());
        }
    }

    @Override
    public void deleteMahasiswa(String nim) {
        mahasiswaManager.deleteMahasiswa(nim);
    }

    @Override
    public void updateMahasiswa() {
        if (isValidate()) {
            mahasiswaManager.updateMahasiswa(nim.get(),nama.get(), angkatan.get(), telp.get(), email.get(), judul.get()
            );
        }
    }

    @Override
    public void getMahasiswaByNIM(String mhs_nim) {
        mahasiswaManager.getMahasiswaByParameter(mhs_nim);
    }

    @Override
    public void getPembimbing(int plot_id) {
        mahasiswaManager.getPembimbing(plot_id);
    }

    @Override
    public void updateSKTA(String mhs_nim, MultipartBody.Part part) {
        mahasiswaManager.updateSKTA(mhs_nim,part);
    }

    @Override
    public void btnSKUpdate() {
        viewModel.btnSKUpdate();
    }
}
