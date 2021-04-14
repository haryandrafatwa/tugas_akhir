package org.d3ifcool.finpro.core.mediators.interfaces;

import androidx.annotation.IdRes;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.SessionManager;

public interface ProdiMediator {

    void Notify(@IdRes int id);
    void message(String event);
    Toolbar getToolbar();
    DrawerLayout getDrawer();
    NavigationView getNavigationView();
    SessionManager getSessionManager();

}
