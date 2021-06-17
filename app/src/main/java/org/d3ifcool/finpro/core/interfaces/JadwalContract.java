package org.d3ifcool.finpro.core.interfaces;

import org.d3ifcool.finpro.core.models.Informasi;
import org.d3ifcool.finpro.core.models.JadwalKegiatan;

import java.util.List;

public interface JadwalContract {

    interface ViewModel{
        void onGetListJadwal(List<JadwalKegiatan> kegiatanList);
        void onMessage(String message);
    }

    interface Presenter{
        void getAllJadwal(String token);
        void createJadwal(String token);
        void deleteJadwal(String token, int id);
        void updateJadwal(String token, int id);
    }

}
