package org.d3ifcool.finpro.core.interfaces.lists;

import org.d3ifcool.finpro.core.models.Plotting;

import java.util.List;

import okhttp3.ResponseBody;

public interface PlottingListView {

    void showProgress();

    void hideProgress();

    void onGetListPlotting(List<Plotting> plotting);

    void onGetBody(ResponseBody body, String filename);

    void isEmptyListPlotting();

    void onFailed(String message);

}
