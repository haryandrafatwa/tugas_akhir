package org.d3ifcool.finpro.core.presenters;


import android.content.Intent;
import android.text.TextUtils;

import androidx.databinding.ObservableField;

import org.d3ifcool.finpro.App;
import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.activities.detail.InformasiUbahActivity;
import org.d3ifcool.finpro.core.interfaces.InformasiContract;
import org.d3ifcool.finpro.core.interfaces.JadwalContract;
import org.d3ifcool.finpro.core.models.Informasi;
import org.d3ifcool.finpro.core.models.manager.InformasiManager;
import org.d3ifcool.finpro.core.models.manager.JadwalKegiatanManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JadwalPresenter implements JadwalContract.Presenter {

    public ObservableField<String> nama;
    public ObservableField<String> tgl_mulai;
    public ObservableField<String> tgl_akhir;

    private JadwalKegiatanManager jadwalKegiatanManager;
    private JadwalContract.ViewModel viewModel;

    public JadwalPresenter(JadwalContract.ViewModel viewModel) {
        this.viewModel = viewModel;
        jadwalKegiatanManager = new JadwalKegiatanManager(viewModel);
        jadwalKegiatanManager.initContext(App.self());
        intFields();
    }

    private void intFields(){
        nama = new ObservableField<>();
        tgl_mulai = new ObservableField<>();
        tgl_akhir = new ObservableField<>();
    }

    private boolean isValidate(){
        if (TextUtils.isEmpty(nama.get())){
            viewModel.onMessage("Nama Kegiatan "+App.self().getString(R.string.text_tidak_boleh_kosong));
            return false;
        }

        if (TextUtils.isEmpty(tgl_mulai.get())){
            viewModel.onMessage("Tanggal Mulai "+App.self().getString(R.string.text_tidak_boleh_kosong));
            return false;
        }

        if (TextUtils.isEmpty(tgl_akhir.get())){
            viewModel.onMessage("Tanggal Berakhir "+App.self().getString(R.string.text_tidak_boleh_kosong));
            return false;
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy");
        try {
            Date dAkhir = simpleDateFormat.parse(tgl_akhir.get());
            Date dAwal = simpleDateFormat.parse(tgl_mulai.get());
            if (dAkhir.before(dAwal)){
                viewModel.onMessage("Tanggal Berakhir Tidak Boleh Sebelum Tanggal Mulai");
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void onCreate(){
        viewModel.onMessage("onCreate");
    }

    public void buttonCreate(){
        viewModel.onMessage("buttonCreate");
    }

    @Override
    public void getAllJadwal(String token) {
        jadwalKegiatanManager.getAllJadwal(token);
    }

    @Override
    public void createJadwal(String token) {
        if (isValidate()){
            jadwalKegiatanManager.createJadwal(token,nama.get(),tgl_mulai.get(), tgl_akhir.get());
        }
    }

    @Override
    public void deleteJadwal(String token, int id) {
        jadwalKegiatanManager.deleteJadwal(token,id);
    }

    @Override
    public void updateJadwal(String token, int id) {
        jadwalKegiatanManager.updateJadwal(token, id,nama.get(), tgl_mulai.get(),tgl_akhir.get());
    }

    @Override
    public void getJadwalByParameter(String token, String like) {
        jadwalKegiatanManager.getJadwalByParameter(token,like);
    }

}