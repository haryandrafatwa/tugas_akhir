package org.d3ifcool.finpro.core.interfaces;

import org.d3ifcool.finpro.core.models.Mahasiswa;
import org.d3ifcool.finpro.core.models.Plotting;

import java.util.List;

import okhttp3.MultipartBody;

public interface MahasiswaContract {

    interface ViewModel{
        void onGetObjectMahasiswa(Mahasiswa mahasiswa);
        void onGetListMahasiswa(List<Mahasiswa> mahasiswaList);
        void onSuccessGetPlotting(Plotting plotting);
        void onMessage(String message);
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
