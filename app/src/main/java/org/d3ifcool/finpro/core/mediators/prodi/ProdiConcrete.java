package org.d3ifcool.finpro.core.mediators.prodi;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.activities.AuthActivity;
import org.d3ifcool.finpro.core.helpers.MethodHelper;
import org.d3ifcool.finpro.core.helpers.SessionManager;
import org.d3ifcool.finpro.core.mediators.interfaces.prodi.ProdiMediator;
import org.d3ifcool.finpro.core.models.manager.AuthManager;
import org.d3ifcool.finpro.prodi.activities.KoorPemberitahuanActivity;
import org.d3ifcool.finpro.prodi.activities.KoorProfilActivity;
import org.d3ifcool.finpro.prodi.fragments.ProdiDosenFragment;
import org.d3ifcool.finpro.prodi.fragments.ProdiInformasiFragment;
import org.d3ifcool.finpro.prodi.fragments.ProdiMahasiswaFragment;
import org.d3ifcool.finpro.prodi.fragments.ProdiPlottingFragment;
import org.d3ifcool.finpro.prodi.fragments.ProdiSKTAFragment;

public class ProdiConcrete implements ProdiMediator {

    private AppCompatActivity appCompatActivity;
    private AuthManager authManager;
    private MethodHelper methodHelper;

    private SessionManager sessionManager;
    private AlertDialog.Builder alertDialog;
    private ProgressDialog progressDialog;


    public ProdiConcrete(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
        authManager  = new AuthManager();
        methodHelper = new MethodHelper(appCompatActivity);
    }

    private static final String SET = "set";

    @Override
    public void Notify(int id) {
        switch (id){
            case R.id.toolbar_menu_pemberitahuan:
                selectIntent(KoorPemberitahuanActivity.class);
                break;

            case R.id.toolbar_menu_profil:
                selectIntent(KoorProfilActivity.class);
                break;

            case R.id.toolbar_menu_keluar:
                message("AlertDialog",SET);
                message("AlertDialog","logout");
                break;
            case R.id.nav_menu_informasi:
                this.appCompatActivity.setTitle(R.string.title_informasi);
                methodHelper.applyFragment(new ProdiInformasiFragment(),"ProdiInformasiFragment");
                break;
            case R.id.nav_menu_mahasiswa:
                this.appCompatActivity.setTitle(R.string.title_mahasiswa);
                methodHelper.applyFragment(new ProdiMahasiswaFragment(),"ProdiMahasiswaFragment");
                break;
            case R.id.nav_menu_dosen:
                this.appCompatActivity.setTitle(R.string.title_dosen);
                methodHelper.applyFragment(new ProdiDosenFragment(),"ProdiDosenFragment");
                break;
            case R.id.nav_menu_plotting:
                this.appCompatActivity.setTitle(R.string.menu_plotting_pembimbing);
                methodHelper.applyFragment(new ProdiPlottingFragment(),"ProdiPlottingFragment");
                break;
            case R.id.nav_menu_skta:
                this.appCompatActivity.setTitle(R.string.menu_sk_ta);
                methodHelper.applyFragment(new ProdiSKTAFragment(),"ProdiSKTAFragment");
                break;
            default:
                break;
        }
    }

    @Override
    public void message(String component, String event) {
        switch (component){
            case "ProgressDialog":
                switch (event){
                    case SET:
                        progressDialog = new ProgressDialog(appCompatActivity);
                        progressDialog.setMessage(appCompatActivity.getString(org.d3ifcool.finpro.R.string.text_progress_dialog));
                        break;
                }
                break;
            case "SessionManager":
                switch (event){
                    case SET:
                        sessionManager = new SessionManager(this.appCompatActivity);
                        break;
                }
                break;
            case "AlertDialog":
                switch (event){
                    case SET:
                        alertDialog = new AlertDialog.Builder(appCompatActivity).setIcon(android.R.drawable.ic_dialog_alert).setNegativeButton("Batal",null);
                        break;
                    case "logout":
                        alertDialog
                                .setTitle(R.string.dialog_keluar_title)
                                .setMessage(R.string.dialog_keluar_text)
                                .setPositiveButton("Keluar", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intentKeluar = new Intent(appCompatActivity, AuthActivity.class);
                                        appCompatActivity.startActivity(intentKeluar);
                                        authManager.logout(sessionManager.getSessionToken());
                                        sessionManager.removeSession();
                                        authManager.initContext(appCompatActivity);
                                        appCompatActivity.finish();
                                    }
                                })
                                .show();
                        break;
                }
                break;
            default:
                break;
        }
    }

    private void selectIntent(Class aClass){
        Intent intent = new Intent(appCompatActivity, aClass);
        appCompatActivity.startActivity(intent);
    }

    public ProgressDialog getProgressDialog(){
        return this.progressDialog;
    }

    public SessionManager getSessionManager(){
        return this.sessionManager;
    }
}
