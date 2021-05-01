package org.d3ifcool.finpro.core.mediators.prodi;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.d3ifcool.finpro.App;
import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.activities.AuthActivity;
import org.d3ifcool.finpro.core.helpers.MethodHelper;
import org.d3ifcool.finpro.core.helpers.SessionManager;
import org.d3ifcool.finpro.core.mediators.interfaces.prodi.ProdiMediator;
import org.d3ifcool.finpro.core.models.manager.AuthManager;
import org.d3ifcool.finpro.core.models.manager.MahasiswaManager;
import org.d3ifcool.finpro.prodi.activities.KoorPemberitahuanActivity;
import org.d3ifcool.finpro.prodi.activities.KoorProfilActivity;
import org.d3ifcool.finpro.prodi.activities.editor.create.ProdiDosenTambahActivity;
import org.d3ifcool.finpro.prodi.adapters.ProdiDosenViewAdapter;
import org.d3ifcool.finpro.prodi.fragments.ProdiDosenFragment;
import org.d3ifcool.finpro.prodi.fragments.ProdiInformasiFragment;
import org.d3ifcool.finpro.prodi.fragments.ProdiMahasiswaFragment;
import org.d3ifcool.finpro.prodi.fragments.ProdiPlottingFragment;
import org.d3ifcool.finpro.prodi.fragments.ProdiSKTAFragment;

public class ProdiConcrete implements ProdiMediator {

    private AppCompatActivity activity;
    private AuthManager authManager;
    private MahasiswaManager mahasiswaManager;
    private MethodHelper methodHelper;

    private ProdiDosenViewAdapter dosenViewAdapter;

    private SessionManager sessionManager;
    private AlertDialog.Builder alertDialog;
    private ProgressDialog progressDialog;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private RelativeLayout relativeLayout;


    public ProdiConcrete(AppCompatActivity activity) {
        this.activity = activity;
        authManager  = new AuthManager();
        methodHelper = new MethodHelper(activity);
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

            case R.id.toolbar_menu_hapus:
                message("AlertDialog",SET);
                message("AlertDialog","hapus");
                break;

            case R.id.nav_menu_informasi:
                this.activity.setTitle(R.string.title_informasi);
                methodHelper.applyFragment(new ProdiInformasiFragment(),"ProdiInformasiFragment");
                break;
            case R.id.nav_menu_mahasiswa:
                this.activity.setTitle(R.string.title_mahasiswa);
                methodHelper.applyFragment(new ProdiMahasiswaFragment(),"ProdiMahasiswaFragment");
                break;
            case R.id.nav_menu_dosen:
                this.activity.setTitle(R.string.title_dosen);
                methodHelper.applyFragment(new ProdiDosenFragment(),"ProdiDosenFragment");
                break;
            case R.id.nav_menu_plotting:
                this.activity.setTitle(R.string.menu_plotting_pembimbing);
                methodHelper.applyFragment(new ProdiPlottingFragment(),"ProdiPlottingFragment");
                break;
            case R.id.nav_menu_skta:
                this.activity.setTitle(R.string.menu_sk_ta);
                methodHelper.applyFragment(new ProdiSKTAFragment(),"ProdiSKTAFragment");
                break;
            default:
                break;
        }
    }

    @Override
    public void message(String component, String event) {
        switch (component){
            case "DosenViewAdapter":
                switch (event){
                    case SET:
                        dosenViewAdapter = new ProdiDosenViewAdapter(activity);
                        break;
                }
                break;
            case "ProgressDialog":
                switch (event){
                    case SET:
                        progressDialog = new ProgressDialog(activity);
                        progressDialog.setMessage(activity.getString(org.d3ifcool.finpro.R.string.text_progress_dialog));
                        break;
                    case "show":
                        progressDialog.show();
                        break;
                    case "dismiss":
                        progressDialog.dismiss();
                        break;
                }
                break;
            case "SessionManager":
                switch (event){
                    case SET:
                        sessionManager = new SessionManager(this.activity);
                        break;
                }
                break;
            case "AlertDialog":
                switch (event){
                    case SET:
                        alertDialog = new AlertDialog.Builder(activity).setIcon(android.R.drawable.ic_dialog_alert).setNegativeButton("Batal",null);
                        break;
                    case "logout":
                        alertDialog
                                .setTitle(R.string.dialog_keluar_title)
                                .setMessage(R.string.dialog_keluar_text)
                                .setPositiveButton("Keluar", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intentKeluar = new Intent(activity, AuthActivity.class);
                                        activity.startActivity(intentKeluar);
                                        authManager.logout(sessionManager.getSessionToken());
                                        sessionManager.removeSession();
                                        authManager.initContext(activity);
                                        activity.finish();
                                    }
                                })
                                .show();
                        break;
                    case "hapus":
                        alertDialog
                                .setTitle(R.string.dialog_hapus_title)
                                .setMessage(R.string.dialog_hapus_text)
                                .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                });
                        break;
                }
                break;
            default:
                break;
        }
    }

    public void selectIntent(Class aClass){
        Intent intent = new Intent(activity, aClass);
        activity.startActivity(intent);
    }
    public void setActionBarDrawerToggle(DrawerLayout drawerLayout, Toolbar toolbar) {
        actionBarDrawerToggle = new ActionBarDrawerToggle(activity,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        actionBarDrawerToggle.syncState();
    }

    public ActionBarDrawerToggle getActionBarDrawerToggle() {
        return actionBarDrawerToggle;
    }

    public ProgressDialog getProgressDialog(){
        return this.progressDialog;
    }

    public SessionManager getSessionManager(){
        return this.sessionManager;
    }

    public ProdiDosenViewAdapter getDosenViewAdapter() {
        return dosenViewAdapter;
    }
}
