package org.d3ifcool.finpro.prodi.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.Message;
import org.d3ifcool.finpro.core.interfaces.JadwalContract;
import org.d3ifcool.finpro.core.interfaces.MahasiswaContract;
import org.d3ifcool.finpro.core.mediators.interfaces.prodi.Mediator;
import org.d3ifcool.finpro.core.mediators.prodi.ConcreteMediator;
import org.d3ifcool.finpro.core.models.JadwalKegiatan;
import org.d3ifcool.finpro.core.models.Mahasiswa;
import org.d3ifcool.finpro.core.models.Plotting;
import org.d3ifcool.finpro.databinding.FragmentKoorMahasiswaBinding;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;

import static android.app.Activity.RESULT_OK;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.PICK_EXCEL_REQUEST;

public class ProdiSidangFragment extends Fragment implements MahasiswaContract.ViewModel, JadwalContract.ViewModel {

    private Message message = new Message();
    private FragmentKoorMahasiswaBinding mBinding;
    private Mediator mediator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_koor_mahasiswa,container,false);
        mBinding.setLifecycleOwner(this);
        mediator = new ConcreteMediator((AppCompatActivity) getActivity());
        mediator.setMahasiswaPresenter(this);
        mediator.setJadwalPresenter(this);
        mBinding.setPresenter(mediator.getMahasiswaPresenter());

        mediator.message(message.setComponent("ProgressDialog").setEvent("set"));
        mediator.message(message.setComponent("SessionManager").setEvent("set"));
        mediator.message(message.setComponent("FileHelper").setEvent("set"));
        mediator.message(message.setComponent("ProdiSidangViewAdapter").setEvent("set"));
        mediator.setRecyclerView(mBinding.recyclerView);
        mediator.setRelativeLayout(mBinding.includeLayout.viewEmptyview);
        mediator.setRefreshLayout(mBinding.refresh);
        mBinding.setToken(mediator.getSessionToken());
        mediator.setFloatingActionButton(mBinding.frgKoorDosenHomeFab);
        mediator.message(message.setComponent("FloatButton").setEvent("setVisibility").setVisibility(View.VISIBLE));
        mediator.message(message.setComponent("FloatButton").setEvent("setBackground").setText("ic_upload"));

        mediator.message(message.setComponent("MahasiswaPresenter").setEvent("getAllData"));

        return mBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        mediator.message(message.setComponent("MahasiswaPresenter").setEvent("getAllData"));
    }

    private void intentPickFile(){
        if (mediator.getPermissionFile()){;
            startActivityForResult(Intent.createChooser(mediator.findFileIntent("XLS"), "Pilih file"), PICK_EXCEL_REQUEST);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_EXCEL_REQUEST && resultCode == RESULT_OK) {
            mediator.message(message.setComponent("MahasiswaPresenter").setEvent("uploadFormSidang").setUri(data.getData()));
        }
    }

    @Override
    public void onGetObjectMahasiswa(Mahasiswa mahasiswa) {

    }

    @Override
    public void onGetListMahasiswa(List<Mahasiswa> mahasiswa) {
        ArrayList<Mahasiswa> arrayList = new ArrayList<>();
        arrayList.clear();
        for (int i = 0; i < mahasiswa.size(); i++) {
            if (mahasiswa.get(i).getSidang_status()!=null){
                if (mahasiswa.get(i).getSidang_status().equalsIgnoreCase("terjadwalkan")){
                    arrayList.add(mahasiswa.get(i));
                }
            }
        }
        mediator.message(message.setComponent("ProdiSidangViewAdapter").setEvent("addItem").setItem(arrayList));
        mediator.message(message.setComponent("ProdiSidangViewAdapter").setEvent("setAdapter"));
        mediator.message(message.setComponent("RefreshLayout").setEvent("setRefreshing").setEnabled(false));
        if (arrayList.size() == 0) {
            mediator.message(message.setComponent("RelativeLayout").setEvent("setVisibility").setVisibility(View.VISIBLE));
        } else {
            mediator.message(message.setComponent("RelativeLayout").setEvent("setVisibility").setVisibility(View.GONE));
        }
        mediator.message(message.setComponent("JadwalPresenter").setEvent("getJadwalByLike").setText("Pendaftaran% Sidang %Terjadwal% 1"));
    }

    @Override
    public void onSuccessGetPlotting(Plotting plotting) {

    }

    @Override
    public void onGetBody(ResponseBody body, String filename) {

    }

    @Override
    public void onGetListJadwal(List<JadwalKegiatan> kegiatanList) {
        message.setKegiatanList(kegiatanList);
        mediator.message(message.setComponent("FloatButton").setEvent("setOnClick").setText(this.getClass().getSimpleName()));
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
            case "onUploadForm":
                intentPickFile();
                break;
            case "onSuccess":
                onResume();
                break;
            default:
                mediator.message(message.setComponent("Toasty").setEvent("Warning").setText(messages));
                break;
        }
    }
}
