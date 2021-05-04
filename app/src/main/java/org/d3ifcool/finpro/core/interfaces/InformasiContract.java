package org.d3ifcool.finpro.core.interfaces;

import org.d3ifcool.finpro.core.models.Informasi;
import org.d3ifcool.finpro.core.models.Plotting;

import java.util.List;

import okhttp3.MultipartBody;

public interface InformasiContract {

    interface ViewModel{
        void onGetObjectInformasi(Informasi informasi);
        void onGetListInformasi(List<Informasi> informasiList);
        void onMessage(String message);
    }

    interface Presenter{
        void getAllInformasi();
        void createInformasi();
        void deleteInformasi(int id);
        void updateInformasi(int id, String judul, String text);
    }

}
