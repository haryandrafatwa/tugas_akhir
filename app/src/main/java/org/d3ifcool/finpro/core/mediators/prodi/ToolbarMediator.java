package org.d3ifcool.finpro.core.mediators.prodi;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.activities.AuthActivity;
import org.d3ifcool.finpro.core.helpers.SessionManager;
import org.d3ifcool.finpro.core.mediators.interfaces.prodi.ProdiMediator;
import org.d3ifcool.finpro.core.models.manager.AuthManager;
import org.d3ifcool.finpro.prodi.activities.KoorPemberitahuanActivity;
import org.d3ifcool.finpro.prodi.activities.KoorProfilActivity;

public class ToolbarMediator implements ProdiMediator {

    private AppCompatActivity appCompatActivity;

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private SessionManager sessionManager;
    private NavigationView navigationView;
    private AuthManager authManager = new AuthManager();

    public ToolbarMediator(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    @Override
    public void Notify(int id) {
        switch (id){
            case R.id.toolbar:
                this.toolbar = this.appCompatActivity.findViewById(R.id.toolbar);
                break;
            case R.id.drawer_layout:
                this.drawer = this.appCompatActivity.findViewById(R.id.drawer_layout);
                break;
            case R.id.nav_view:
                navigationView = this.appCompatActivity.findViewById(R.id.nav_view);
                TextView tv_nama = navigationView.getHeaderView(0).findViewById(R.id.tv_nama);
                tv_nama.setText(sessionManager.getSessionKoorNama());
                TextView tv_role = navigationView.getHeaderView(0).findViewById(R.id.tv_role);
                if (sessionManager.getSessionUsername().equalsIgnoreCase("admin_prodi")){
                    tv_role.setText(R.string.title_koor_prodi);
                }else{
                    tv_role.setText(R.string.title_koor_lak);
                }
                break;
            case R.id.toolbar_menu_pemberitahuan:
                Intent intentPemberitahuan = new Intent(appCompatActivity, KoorPemberitahuanActivity.class);
                appCompatActivity.startActivity(intentPemberitahuan);
                break;

            case R.id.toolbar_menu_profil:
                Intent intentProfil = new Intent(appCompatActivity, KoorProfilActivity.class);
                appCompatActivity.startActivity(intentProfil);
                break;

            case R.id.toolbar_menu_keluar:
                new AlertDialog
                        .Builder(appCompatActivity)
                        .setTitle(R.string.dialog_keluar_title)
                        .setMessage(R.string.dialog_keluar_text)
                        .setPositiveButton("Keluar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intentKeluar = new Intent(appCompatActivity, AuthActivity.class);
                                appCompatActivity.startActivity(intentKeluar);
                                authManager.logout(sessionManager.getSessionToken());
                                sessionManager.removeSession();
                                appCompatActivity.finish();
                                authManager.initContext(appCompatActivity);
                            }
                        })

                        .setNegativeButton("Batal", null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

                break;
            default:
                break;
        }
    }

    @Override
    public void message(String event) {
        switch (event){
            case "ActionBarDrawerToggle":
                actionBarDrawerToggle = new ActionBarDrawerToggle(
                    this.appCompatActivity, this.drawer, this.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
                this.drawer.addDrawerListener(actionBarDrawerToggle);
                actionBarDrawerToggle.syncState();
                break;
            case "SessionManager":
                sessionManager = new SessionManager(this.appCompatActivity);
                break;
            default:
                break;
        }
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public DrawerLayout getDrawer() {
        return drawer;
    }

    public SessionManager getSessionManager() {
        return sessionManager;
    }

    public NavigationView getNavigationView() {
        return navigationView;
    }
}
