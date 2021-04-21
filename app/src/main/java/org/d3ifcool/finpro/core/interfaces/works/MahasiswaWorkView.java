package org.d3ifcool.finpro.core.interfaces.works;

import org.d3ifcool.finpro.core.models.Plotting;

public interface MahasiswaWorkView {

    void showProgress();

    void hideProgress();

    void onSucces();

    void onSuccesGetPlotting(Plotting plotting);

    void onFailed(String message);

}
