package org.d3ifcool.finpro.activities;

import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.interfaces.ProdiContract;
import org.d3ifcool.finpro.core.interfaces.objects.ProdiView;
import org.d3ifcool.finpro.core.mediators.prodi.ProdiConcrete;
import org.d3ifcool.finpro.core.models.Koordinator;
import org.d3ifcool.finpro.core.presenters.ProdiPresenters;
import org.d3ifcool.finpro.core.mediators.prodi.NavigationProdiMediator;
import org.d3ifcool.finpro.core.mediators.interfaces.prodi.ProdiMediator;
import org.d3ifcool.finpro.core.presenters.prodi.ProdiPresenter;

import java.util.List;

public class ProdiMainActivitys extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ProdiContract.ViewModel {

    private ProdiMediator prodiMediator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        prodiMediator = new ProdiConcrete(this);
        prodiMediator.Notify(R.id.toolbar);
//        setSupportActionBar(prodiMediator.getToolbar());

        prodiMediator.Notify(R.id.drawer_layout);
//        prodiMediator.message("ActionBarDrawerToggle");

//        prodiMediator.message("SessionManager");
        ProdiPresenter prodiPresenter = new ProdiPresenter(this);

//        prodiPresenters.getKoorByParameter(prodiMediator.getSessionManager().getSessionToken(),
//                prodiMediator.getSessionManager().getSessionUsername());

        prodiMediator.Notify(R.id.nav_view);
//        prodiMediator.getNavigationView().setNavigationItemSelectedListener(this);

//        prodiMediator.getNavigationView().getMenu().getItem(0).setChecked(true);
//        onNavigationItemSelected(prodiMediator.getNavigationView().getMenu().getItem(0));

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_toolbar_koor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        prodiMediator.Notify(item.getItemId());

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        ProdiMediator mdt = new NavigationProdiMediator(this);
        mdt.Notify(item.getItemId());

//        prodiMediator.getDrawer().closeDrawer(GravityCompat.START);
        item.setCheckable(true);

        return true;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onGetObjectProdi(Koordinator prodi) {

    }

    @Override
    public void isEmptyObjectProdi() {

    }

    @Override
    public void onGetListProdi(List<Koordinator> prodiList) {

    }

    @Override
    public void isEmptyListProdi() {

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFailed(String message) {

    }
}
