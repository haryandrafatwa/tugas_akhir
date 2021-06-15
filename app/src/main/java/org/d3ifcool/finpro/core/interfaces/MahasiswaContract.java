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
        void onCreate();
        void onDelete();
        void getAllMahasiswa(String token);
        void createMahasiswa(String token);
        void deleteMahasiswa(String token,String nim);
        void updateMahasiswa(String token);
        void getMahasiswaByNIM(String token,String mhs_nim);
        void getPembimbing(String token,int plot_id);
        void addPembimbing(String token, String mhs_nim, int plot_id);
        void deletePembimbing(String token, String mhs_nim);
        void updateSKTA(String token,String mhs_nim);
    }

}
