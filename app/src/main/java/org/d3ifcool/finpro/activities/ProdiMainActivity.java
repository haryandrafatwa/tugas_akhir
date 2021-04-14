package org.d3ifcool.finpro.activities;

import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.interfaces.objects.ProdiView;
import org.d3ifcool.finpro.core.mediators.prodi.ToolbarMediator;
import org.d3ifcool.finpro.core.models.Koordinator;
import org.d3ifcool.finpro.core.presenters.ProdiPresenter;
import org.d3ifcool.finpro.core.mediators.prodi.NavigationProdiMediator;
import org.d3ifcool.finpro.core.mediators.interfaces.ProdiMediator;

public class ProdiMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ProdiView {

    private ProdiMediator prodiMediator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        prodiMediator = new ToolbarMediator(this);
        prodiMediator.Notify(R.id.toolbar);
        setSupportActionBar(prodiMediator.getToolbar());

        prodiMediator.Notify(R.id.drawer_layout);
        prodiMediator.message("ActionBarDrawerToggle");

        prodiMediator.message("SessionManager");
        ProdiPresenter prodiPresenter = new ProdiPresenter(this);
        prodiPresenter.initContext(this);

        prodiPresenter.getKoorByParameter(prodiMediator.getSessionManager().getSessionUsername());

        prodiMediator.Notify(R.id.nav_view);
        prodiMediator.getNavigationView().setNavigationItemSelectedListener(this);

        prodiMediator.getNavigationView().getMenu().getItem(0).setChecked(true);
        onNavigationItemSelected(prodiMediator.getNavigationView().getMenu().getItem(0));

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

        prodiMediator.getDrawer().closeDrawer(GravityCompat.START);
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
    public void onGetObjectKoor(Koordinator koordinator) {
        prodiMediator.getSessionManager().createSessionDataKoor(koordinator);
    }

    @Override
    public void isEmptyObjectKoor() {

    }

    @Override
    public void onFailed(String message) {

    }
}
