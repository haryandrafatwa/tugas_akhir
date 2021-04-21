package org.d3ifcool.finpro.core.interfaces;

import org.d3ifcool.finpro.core.models.Dosen;
import org.d3ifcool.finpro.core.models.Koordinator;

import java.util.List;

public interface ProdiContract {

    interface ViewModel{
        void showProgress();
        void hideProgress();
        void onGetObjectProdi(Koordinator prodi);
        void isEmptyObjectProdi();
        void onGetListProdi(List<Koordinator> prodiList);
        void isEmptyListProdi();
        void onSuccess();
        void onFailed(String message);
    }

    interface Presenter{
        void getAllProdi();
        void createProdi();
        void deleteProdi();
        void updateProdi();
        void getProdiByNIP(String token, String dsn_nip);
    }

}
