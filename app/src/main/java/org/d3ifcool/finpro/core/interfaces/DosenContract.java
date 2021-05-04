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
        void getAllDosen();
        void createDosen();
        void deleteDosen(String dsn_nip);
        void updateDosen();
        void getDosenByNIP(String dsn_nip);
    }

}
