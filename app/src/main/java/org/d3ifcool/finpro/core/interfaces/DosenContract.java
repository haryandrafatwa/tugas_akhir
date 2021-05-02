package org.d3ifcool.finpro.core.interfaces;

import org.d3ifcool.finpro.core.models.Dosen;

import java.util.List;

public interface DosenContract {

    interface ViewModel{
        void showProgress();
        void hideProgress();
        void onGetObjectDosen(Dosen dosen);
        void isEmptyObjectDosen();
        void onGetListDosen(List<Dosen> dosenList);
        void isEmptyListDosen();
        void onSuccess();
        void onFailed(String message);
        void onClickFloatButton();
    }

    interface Presenter{
        void getAllDosen();
        void createDosen();
        void deleteDosen(String dsn_nip);
        void updateDosen();
        void getDosenByNIP(String dsn_nip);
    }

}
