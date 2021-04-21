package org.d3ifcool.finpro.core.mediators.prodi;

import android.app.ProgressDialog;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.SessionManager;
import org.d3ifcool.finpro.core.mediators.interfaces.prodi.ProdiActivityMediator;

public class ProdiActivityConcrete implements ProdiActivityMediator {

    private AppCompatActivity app;
    private ProgressDialog progressDialog;
    private SessionManager sessionManager;
    private EditText et_judul, et_deskripsi, et_nip, et_nama, et_kode;
    private Spinner sp_dosen_1, sp_dosen_2;
    private Button btn_simpan;

    public ProdiActivityConcrete(AppCompatActivity app) {
        this.app = app;
    }

    @Override
    public void Notify(int id) {
        switch (id){
            case R.id.act_koor_edittext_judul:
                et_judul = app.findViewById(R.id.act_koor_edittext_judul);
                break;
            case R.id.act_koor_edittext_deskripsi:
                et_deskripsi = app.findViewById(R.id.act_koor_edittext_deskripsi);
                break;
            case R.id.act_koor_info_button_simpan:
                btn_simpan = app.findViewById(R.id.act_koor_info_button_simpan);
                break;
            case R.id.act_koor__dosen_edittext_nipdosen:
                et_nip = app.findViewById(R.id.act_koor__dosen_edittext_nipdosen);
                break;
            case R.id.act_koor_edittext_namadosen:
                et_nama = app.findViewById(R.id.act_koor_edittext_namadosen);
                break;
            case R.id.act_koor_edittext_kodedosen:
                et_kode = app.findViewById(R.id.act_koor_edittext_kodedosen);
                break;
            case R.id.act_koor__dosen_spinner_dosen_1:
                sp_dosen_1 = app.findViewById(R.id.act_koor__dosen_spinner_dosen_1);
                break;
            case R.id.act_koor__dosen_spinner_dosen_2:
                sp_dosen_2 = app.findViewById(R.id.act_koor__dosen_spinner_dosen_2);
                break;
            default:
                break;
        }
    }

    @Override
    public void message(String event) {
        switch (event){
            case "SessionManager":
                sessionManager = new SessionManager(app);
                break;
            case "ProgressDialog":
                progressDialog = new ProgressDialog(app);
                progressDialog.setMessage(app.getString(R.string.text_progress_dialog));
                break;
            default:
                break;
        }
    }

    @Override
    public SessionManager getSessionManager() {
        return this.sessionManager;
    }

    @Override
    public ProgressDialog getProgressDialog() {
        return this.progressDialog;
    }

    @Override
    public Button getButton() {
        return this.btn_simpan;
    }

    @Override
    public EditText getETJudul() {
        return this.et_judul;
    }

    @Override
    public EditText getETDeskripsi() {
        return this.et_deskripsi;
    }

    @Override
    public EditText getETNIP() {
        return this.et_nip;
    }

    @Override
    public EditText getETNama() {
        return this.et_nama;
    }

    @Override
    public EditText getETKode() {
        return this.et_kode;
    }

    @Override
    public Spinner getSPDosen1() {
        return this.sp_dosen_1;
    }

    @Override
    public Spinner getSPDosen2() {
        return this.sp_dosen_2;
    }
}
