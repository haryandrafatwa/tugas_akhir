package org.d3ifcool.finpro.core.interfaces;

import org.d3ifcool.finpro.core.models.Dosen;

import java.util.List;

public interface DosenContract {

    interface ViewModel{
        void onGetObjectDosen(Dosen dosen);
        void onGetListDosen(List<Dosen> dosenList);
        void onMessage(String message);
    }

    interface Presenter{
        void onCreate();
        void getCurrentDosen(String token);
        void getAllDosen(String token);
        void createDosen(String token);
        void deleteDosen(String token, String dsn_nip);
        void updateDosen(String token);
        void getDosenByNIP(String token, String dsn_nip);
    }

}
