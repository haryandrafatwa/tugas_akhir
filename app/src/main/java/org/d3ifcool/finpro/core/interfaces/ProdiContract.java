package org.d3ifcool.finpro.core.interfaces;

import org.d3ifcool.finpro.core.models.Dosen;
import org.d3ifcool.finpro.core.models.Koordinator;

import java.util.List;

public interface ProdiContract {

    interface ViewModel{
        void onGetObjectProdi(Koordinator prodi);
        void onMessage(String messages);
    }

    interface Presenter{
        void updateProdi(String token, String username);
        void getProdiByNIP(String token, String dsn_nip);
    }

}
