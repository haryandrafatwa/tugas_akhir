package org.d3ifcool.finpro.core.mediators.interfaces.prodi;

import android.app.ProgressDialog;

import androidx.annotation.IdRes;

import org.d3ifcool.finpro.core.models.Mahasiswa;
import org.d3ifcool.finpro.core.models.Plotting;

public interface
ProdiDetailActivityMediator {

    void Notify(@IdRes int id);
    void message(String event);
    ProgressDialog getProgressDialog();
    Mahasiswa getMahasiswa();
    void setPlotting(Plotting plotting);
}
