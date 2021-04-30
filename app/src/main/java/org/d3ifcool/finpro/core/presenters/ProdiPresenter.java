package org.d3ifcool.finpro.core.presenters;

import org.d3ifcool.finpro.App;
import org.d3ifcool.finpro.core.interfaces.DosenContract;
import org.d3ifcool.finpro.core.interfaces.ProdiContract;
import org.d3ifcool.finpro.core.models.manager.AuthManager;
import org.d3ifcool.finpro.core.models.manager.DosenManager;
import org.d3ifcool.finpro.core.models.manager.ProdiManager;

public class ProdiPresenter implements ProdiContract.Presenter {

    private ProdiContract.ViewModel mViewModel;
    private ProdiManager prodiManager;
    private AuthManager authManager;

    public ProdiPresenter(ProdiContract.ViewModel mViewModel) {
        this.mViewModel = mViewModel;
        prodiManager = new ProdiManager(mViewModel);
        prodiManager.initContext(App.self());
        authManager = new AuthManager();
        authManager.initContext(App.self());
    }

    @Override
    public void getAllProdi() {

    }

    @Override
    public void createProdi() {

    }

    @Override
    public void deleteProdi() {

    }

    @Override
    public void updateProdi() {

    }

    @Override
    public void getProdiByNIP(String token, String dsn_nip) {
        prodiManager.getProdiByParameter(token, dsn_nip);
    }

    public void logout(String token){
        authManager.logout(token);
    }
}
