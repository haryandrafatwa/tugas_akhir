package org.d3ifcool.finpro.core.mediators.prodi;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.MethodHelper;
import org.d3ifcool.finpro.core.helpers.SessionManager;
import org.d3ifcool.finpro.core.mediators.interfaces.ProdiMediator;
import org.d3ifcool.finpro.prodi.fragments.KoorDosenFragment;
import org.d3ifcool.finpro.prodi.fragments.KoorInformasiFragment;
import org.d3ifcool.finpro.prodi.fragments.KoorMahasiswaFragment;
import org.d3ifcool.finpro.prodi.fragments.KoorTentangKamiFragment;

public class NavigationProdiMediator implements ProdiMediator {

    private AppCompatActivity appCompatActivity;

    public NavigationProdiMediator(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    @Override
    public void Notify(@IdRes int id) {
        MethodHelper methodHelper = new MethodHelper(this.appCompatActivity);
        switch (id){
            case R.id.nav_menu_informasi:
                this.appCompatActivity.setTitle(R.string.title_informasi);
                methodHelper.applyFragment(new KoorInformasiFragment());
                break;
            case R.id.nav_menu_mahasiswa:
                this.appCompatActivity.setTitle(R.string.title_mahasiswa);
                methodHelper.applyFragment(new KoorMahasiswaFragment());
                break;
            case R.id.nav_menu_dosen:
                this.appCompatActivity.setTitle(R.string.title_dosen);
                methodHelper.applyFragment(new KoorDosenFragment());
                break;
            case R.id.nav_menu_tentang_kami:
                this.appCompatActivity.setTitle(R.string.title_tentang_kami);
                methodHelper.applyFragment(new KoorTentangKamiFragment());
                break;
            default:
                break;
        }
    }

    @Override
    public void message(String event) {

    }

    @Override
    public Toolbar getToolbar() {
        return null;
    }

    @Override
    public DrawerLayout getDrawer() {
        return null;
    }

    @Override
    public NavigationView getNavigationView() {
        return null;
    }

    @Override
    public SessionManager getSessionManager() {
        return null;
    }
}
