package org.d3ifcool.finpro.core.presenters;

import android.content.Intent;
import android.text.TextUtils;

import androidx.databinding.ObservableField;
import androidx.lifecycle.Observer;

import org.d3ifcool.finpro.App;
import org.d3ifcool.finpro.core.helpers.Constant;
import org.d3ifcool.finpro.core.interfaces.DosenContract;
import org.d3ifcool.finpro.core.interfaces.ProdiContract;
import org.d3ifcool.finpro.core.models.Koordinator;
import org.d3ifcool.finpro.core.models.Mahasiswa;
import org.d3ifcool.finpro.core.models.manager.AuthManager;
import org.d3ifcool.finpro.core.models.manager.DosenManager;
import org.d3ifcool.finpro.core.models.manager.ProdiManager;
import org.d3ifcool.finpro.prodi.activities.editor.ProdiMahasiswaEditorActivity;
import org.d3ifcool.finpro.prodi.activities.editor.ProdiProfilUbahActivity;

import java.lang.reflect.Field;

public class ProdiPresenter implements ProdiContract.Presenter {

    public ObservableField<String> nip;
    public ObservableField<String> nama;
    public ObservableField<String> kode;
    public ObservableField<String> email;
    public ObservableField<String> kontak;

    private ProdiContract.ViewModel mViewModel;
    private ProdiManager prodiManager;
    private AuthManager authManager;

    public ProdiPresenter(ProdiContract.ViewModel mViewModel) {
        this.mViewModel = mViewModel;
        prodiManager = new ProdiManager(mViewModel);
        prodiManager.initContext(App.self());
        authManager = new AuthManager();
        authManager.initContext(App.self());
        initFields();
    }

    private void initFields(){
        nip = new ObservableField<>();
        nama = new ObservableField<>();
        kode = new ObservableField<>();
        email = new ObservableField<>();
        kontak = new ObservableField<>();
    }

    private boolean isValidate(){
        if (TextUtils.isEmpty(nama.get())){
            mViewModel.onMessage("Nama Tidak Boleh Kosong!");
            return false;
        }
        if (TextUtils.isEmpty(kode.get())){
            mViewModel.onMessage("Kode Tidak Boleh Kosong!");
            return false;
        }
        if (TextUtils.isEmpty(nip.get())){
            mViewModel.onMessage("NIP Tidak Boleh Kosong!");
            return false;
        }
        return true;
    }

    @Override
    public void updateProdi(String token, String username) {
        if (isValidate()){
            prodiManager.updateProdi(token, username, nip.get(),nama.get(), kode.get(), kontak.get(), email.get());
        }
    }

    @Override
    public void getProdiByNIP(String token, String dsn_nip) {
        prodiManager.getProdiByParameter(token, dsn_nip);
    }

    public Intent toolbarIntent(Koordinator koordinator){
        Intent intent = new Intent(App.self(), ProdiProfilUbahActivity.class);
        intent.putExtra(Constant.ObjectConstanta.EXTRA_KOORDINATOR,koordinator);
        return intent;
    }
}
