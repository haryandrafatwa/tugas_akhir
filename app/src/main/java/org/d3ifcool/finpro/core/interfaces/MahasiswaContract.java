package org.d3ifcool.finpro.core.interfaces;

import org.d3ifcool.finpro.core.models.Mahasiswa;
import org.d3ifcool.finpro.core.models.Plotting;

import java.util.List;

import okhttp3.MultipartBody;

public interface MahasiswaContract {

    interface ViewModel{
        void showProgress();
        void hideProgress();
        void onGetObjectMahasiswa(Mahasiswa mahasiswa);
        void isEmptyObjectMahasiswa();
        void onGetListMahasiswa(List<Mahasiswa> mahasiswaList);
        void isEmptyListMahasiswa();
        void onSuccessGetPlotting(Plotting plotting);
        void onSuccess();
        void onFailed(String message);
    }

    interface Presenter{
        void getAllMahasiswa();
        void createMahasiswa();
        void deleteMahasiswa(String nim);
        void updateMahasiswa();
        void getMahasiswaByNIM(String mhs_nim);
        void getPembimbing(int plot_id);
        void updateSKTA(String mhs_nim, MultipartBody.Part part);
    }

}
