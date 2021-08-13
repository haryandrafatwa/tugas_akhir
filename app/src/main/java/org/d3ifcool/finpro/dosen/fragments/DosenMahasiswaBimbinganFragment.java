package org.d3ifcool.finpro.dosen.fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.Message;
import org.d3ifcool.finpro.core.interfaces.BimbinganContract;
import org.d3ifcool.finpro.core.mediators.Mediator;
import org.d3ifcool.finpro.core.mediators.ConcreteMediator;
import org.d3ifcool.finpro.core.models.Bimbingan;
import org.d3ifcool.finpro.core.models.Dosen;
import org.d3ifcool.finpro.core.models.Mahasiswa;
import org.d3ifcool.finpro.databinding.FragmentDosenBimbinganBinding;

import java.util.ArrayList;
import java.util.List;

public class DosenMahasiswaBimbinganFragment extends Fragment implements BimbinganContract.ViewModel {

    private FragmentDosenBimbinganBinding mBinding;
    private Message message = new Message();
    private Mediator mediator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_dosen_bimbingan, container, false);
        mBinding.setLifecycleOwner(this);
        mediator = new ConcreteMediator((AppCompatActivity)getActivity());
        mediator.setBimbinganPresenter(this);
        mBinding.setPresenter(mediator.getBimbinganPresenter());

        mediator.message(message.setComponent("ProgressDialog").setEvent("set"));
        mediator.message(message.setComponent("SessionManager").setEvent("set"));
        mediator.message(message.setComponent("DosenMahasiswaBimbinganViewAdapter").setEvent("set"));
        mediator.setRefreshLayout(mBinding.frgDsnPaBimbinganSwiperefresh);
        mediator.setRecyclerView(mBinding.frgDsnPaBimbinganRecyclerview);
        mediator.setRelativeLayout(mBinding.includeLayout.viewEmptyview);
        mBinding.setToken(mediator.getSessionToken());

        mediator.message(message.setComponent("BimbinganPresenter").setEvent("getAllData"));
        return mBinding.getRoot();

    }

    @Override
    public void onResume() {
        super.onResume();
        mediator.message(message.setComponent("BimbinganPresenter").setEvent("getAllData"));
    }


    @Override
    public void onGetListBimbingan(List<Bimbingan> bimbinganList, Dosen dosen, Mahasiswa mahasiswa) {
    }

    @Override
    public void onGetListMahasiswa(List<Mahasiswa> mahasiswaList) {
        ArrayList<Mahasiswa> arrayList = new ArrayList<>();
        arrayList.addAll(mahasiswaList);
        mediator.message(message.setComponent("DosenMahasiswaBimbinganViewAdapter").setEvent("addItem").setItem(arrayList).setText("pembimbing"));
        mediator.message(message.setComponent("DosenMahasiswaBimbinganViewAdapter").setEvent("setAdapter"));
        mediator.message(message.setComponent("RefreshLayout").setEvent("setRefreshing").setEnabled(false));

        if (arrayList.size() == 0) {
            mediator.message(message.setComponent("RelativeLayout").setEvent("setVisibility").setVisibility(View.VISIBLE));
        } else {
            mediator.message(message.setComponent("RelativeLayout").setEvent("setVisibility").setVisibility(View.GONE));
        }
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
