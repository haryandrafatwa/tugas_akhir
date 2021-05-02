package org.d3ifcool.finpro.core.presenters;

import android.content.Intent;
import android.view.MenuItem;

import org.d3ifcool.finpro.App;
import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.interfaces.DosenContract;
import org.d3ifcool.finpro.core.models.Dosen;
import org.d3ifcool.finpro.core.models.manager.DosenManager;
import org.d3ifcool.finpro.prodi.activities.editor.update.KoorDosenUbahActivity;

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
    public void deleteDosen(String dsn_nip) {
        dosenManager.deleteDosen(dsn_nip);
    }

    @Override
    public void updateDosen() {

    }

    @Override
    public void getDosenByNIP(String dsn_nip) {

    }

    public Intent toolbarIntent(Dosen dosen){
        Intent intent = new Intent(App.self(), KoorDosenUbahActivity.class);
        intent.putExtra("extra_dosen",dosen);
        return intent;
    }

    public void floatButton(){
        viewModel.onClickFloatButton();
    }
}
