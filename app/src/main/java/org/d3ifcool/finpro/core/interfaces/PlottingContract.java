package org.d3ifcool.finpro.core.interfaces;

import org.d3ifcool.finpro.core.models.Informasi;
import org.d3ifcool.finpro.core.models.Plotting;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;

public interface PlottingContract {

    interface ViewModel{
        void onGetListPlotting(List<Plotting> plottingList);
        void onMessage(String message);
        void onGetBody(ResponseBody body, String filename);
    }

    interface Presenter{
        void getAllPlotting(String token);
        void createPlotting(String token, String dosen1, String dosen2);
        void deletePlotting(String token, int id);
        void updatePlotting(String token, int id, String dosen1, String dosen2);

        void checkFrom(String token);
        void uploadForm(String token, MultipartBody.Part part);
        void deleteForm(String token);
    }

}
