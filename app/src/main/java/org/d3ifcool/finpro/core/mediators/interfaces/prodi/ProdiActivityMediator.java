package org.d3ifcool.finpro.core.mediators.interfaces.prodi;

import android.app.ProgressDialog;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.IdRes;

import org.d3ifcool.finpro.core.helpers.SessionManager;

public interface ProdiActivityMediator {

    void Notify(@IdRes int id);
    void message(String event);
    SessionManager getSessionManager();
    ProgressDialog getProgressDialog();
    Button getButton();
    EditText getETJudul();
    EditText getETDeskripsi();
    EditText getETNIP();
    EditText getETNama();
    EditText getETKode();
    Spinner getSPDosen1();
    Spinner getSPDosen2();
}
