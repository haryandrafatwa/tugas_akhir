package org.d3ifcool.finpro.core.presenters;

import org.d3ifcool.finpro.App;
import org.d3ifcool.finpro.core.interfaces.DosenContract;
import org.d3ifcool.finpro.core.models.manager.DosenManager;

public class DosenPresenter implements DosenContract.Presenter {

    private DosenContract.ViewModel viewModel;
    private DosenManager dosenManager;

    public DosenPresenter(DosenContract.ViewModel viewModel) {
        this.viewModel = viewModel;
        dosenManager = new DosenManager(viewModel);
        dosenManager.initContext(App.self());
    }

    @Override
    public void getAllDosen() {
        dosenManager.getDosen();
    }

    @Override
    public void createDosen() {

    }

    @Override
    public void deleteDosen() {

    }

    @Override
    public void updateDosen() {

    }

    @Override
    public void getDosenByNIP(String dsn_nip) {

    }
}
