package org.d3ifcool.finpro.dosen.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.Message;
import org.d3ifcool.finpro.core.interfaces.DosenContract;
import org.d3ifcool.finpro.core.mediators.Mediator;
import org.d3ifcool.finpro.core.mediators.ConcreteMediator;
import org.d3ifcool.finpro.core.models.Dosen;
import org.d3ifcool.finpro.core.models.Mahasiswa;
import org.d3ifcool.finpro.databinding.FragmentDosenSidangBinding;

import java.util.ArrayList;
import java.util.List;

public class DosenMahasiswaSidangFragment extends Fragment implements DosenContract.ViewModel {

    private FragmentDosenSidangBinding mBinding;
    private Message message = new Message();
    private Mediator mediator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_dosen_sidang, container, false);
        mBinding.setLifecycleOwner(this);
        mediator = new ConcreteMediator((AppCompatActivity)getActivity());
        mediator.setDosenPresenter(this);
        mBinding.setPresenter(mediator.getDosenPresenter());

        mediator.message(message.setComponent("ProgressDialog").setEvent("set"));
        mediator.message(message.setComponent("SessionManager").setEvent("set"));
        mediator.message(message.setComponent("DosenMahasiswaBimbinganViewAdapter").setEvent("set"));
        mediator.setRefreshLayout(mBinding.frgDsnPaBimbinganSwiperefresh);
        mediator.setRecyclerView(mBinding.frgDsnPaBimbinganRecyclerview);
        mediator.setRelativeLayout(mBinding.includeLayout.viewEmptyview);
        mBinding.setToken(mediator.getSessionToken());

        return mBinding.getRoot();

    }

    @Override
    public void onResume() {
        super.onResume();
        mediator.message(message.setComponent("DosenPresenter").setEvent("getMahasiswaSidang"));
    }

    @Override
    public void onGetObjectDosen(Dosen dosen) {

    }

    @Override
    public void onGetListDosen(List<Dosen> dosenList) {

    }

    @Override
    public void onGetObjectMahasiswa(Mahasiswa mahasiswa) {

    }

    @Override
    public void onGetListMahasiswa(List<Mahasiswa> mahasiswasList) {
        ArrayList<Mahasiswa> arrayList = new ArrayList<>();
        arrayList.addAll(mahasiswasList);
        mediator.message(message.setComponent("DosenMahasiswaBimbinganViewAdapter").setEvent("addItem").setItem(arrayList).setText("penguji"));
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
            case "EmptyList":
                mediator.message(message.setComponent("RelativeLayout").setEvent("setVisibility").setVisibility(View.VISIBLE));
                break;
            default:
                mediator.message(message.setComponent("Toasty").setEvent("Warning").setText(messages));
                break;
        }
    }
}
