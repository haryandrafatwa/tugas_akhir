package org.d3ifcool.finpro.core.mediators.prodi;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.MethodHelper;
import org.d3ifcool.finpro.core.helpers.SessionManager;
import org.d3ifcool.finpro.core.mediators.interfaces.prodi.ProdiMediator;
import org.d3ifcool.finpro.prodi.fragments.ProdiDosenFragment;
import org.d3ifcool.finpro.prodi.fragments.ProdiInformasiFragment;
import org.d3ifcool.finpro.prodi.fragments.ProdiMahasiswaFragment;
import org.d3ifcool.finpro.prodi.fragments.ProdiPlottingFragment;
import org.d3ifcool.finpro.prodi.fragments.ProdiSKTAFragment;

public class NavigationProdiMediator implements ProdiMediator {

    private AppCompatActivity appCompatActivity;

    public NavigationProdiMediator(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    @Override
    public void Notify(@IdRes int id) {
        MethodHelper methodHelper = new MethodHelper(this.appCompatActivity);
        switch (id){

            default:
                break;
        }
    }

    @Override
    public void message(String component, String event) {

    }
}
