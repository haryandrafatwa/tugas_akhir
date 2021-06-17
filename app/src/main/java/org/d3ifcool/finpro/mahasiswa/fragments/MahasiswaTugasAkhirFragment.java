package org.d3ifcool.finpro.mahasiswa.fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.d3ifcool.finpro.core.helpers.Message;
import org.d3ifcool.finpro.core.interfaces.MahasiswaContract;
import org.d3ifcool.finpro.core.mediators.prodi.ConcreteMediator;
import org.d3ifcool.finpro.core.models.Mahasiswa;
import org.d3ifcool.finpro.core.models.Plotting;
import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.databinding.FragmentMahasiswaTugasAkhirBinding;

import java.util.List;

public class MahasiswaTugasAkhirFragment extends Fragment implements MahasiswaContract.ViewModel {

    private Message message = new Message();
    private FragmentMahasiswaTugasAkhirBinding mBinding;
    private ConcreteMediator mediator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_mahasiswa_tugas_akhir, container, false);
        mBinding.setLifecycleOwner(this);
        mediator = new ConcreteMediator((AppCompatActivity) getActivity());
        mediator.setMahasiswaPresenter(this);
        mBinding.setPresenter(mediator.getMahasiswaPresenter());

        mediator.message(message.setComponent("ProgressDialog").setEvent("set"));
        mediator.message(message.setComponent("SessionManager").setEvent("set"));
        mediator.setRefreshLayout(mBinding.swipeRefresh);
        mBinding.setToken(mediator.getSessionToken());
        mBinding.setMhsNim(mediator.getSessionUsername());

        return mBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        mediator.message(message.setComponent("MahasiswaPresenter").setEvent("getMahasiswaBySession"));
    }

    @Override
    public void onGetObjectMahasiswa(Mahasiswa mahasiswa) {
        message.setMahasiswa(mahasiswa);
        mBinding.setMahasiswa(mahasiswa);
        mediator.message(message.setComponent("RefreshLayout").setEvent("setRefreshing").setEnabled(false));
        mediator.message(message.setComponent("MahasiswaPresenter").setEvent("getPembimbing"));
    }

    @Override
    public void onGetListMahasiswa(List<Mahasiswa> mahasiswaList) {

    }

    @Override
    public void onSuccessGetPlotting(Plotting plotting) {
        message.setPlotting(plotting);
        mBinding.setPlot(plotting);
    }

    @Override
    public void onMessage(String messages) {
        switch (messages){
            case "ShowProgressDialog":
                mediator.message(message.setComponent("ProgressDialog").setEvent("show"));
                break;
            case "HideProgressDialog":
                mediator.message(message.setComponent("ProgressDialog").setEvent("dismiss"));
                break;
            default:
                mediator.message(message.setComponent("Toasty").setEvent("Warning").setText(messages));
                break;
        }
    }
}
