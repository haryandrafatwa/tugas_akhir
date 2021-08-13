package org.d3ifcool.finpro.prodi.fragments;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.d3ifcool.finpro.core.helpers.Message;
import org.d3ifcool.finpro.core.interfaces.JadwalContract;
import org.d3ifcool.finpro.core.interfaces.MahasiswaContract;
import org.d3ifcool.finpro.core.mediators.Mediator;
import org.d3ifcool.finpro.core.mediators.ConcreteMediator;
import org.d3ifcool.finpro.core.models.JadwalKegiatan;
import org.d3ifcool.finpro.core.models.Mahasiswa;
import org.d3ifcool.finpro.core.models.Plotting;
import org.d3ifcool.finpro.databinding.FragmentKoorMahasiswaBinding;
import org.d3ifcool.finpro.prodi.activities.editor.ProdiMahasiswaEditorActivity;
import org.d3ifcool.finpro.R;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProdiMahasiswaFragment extends Fragment implements MahasiswaContract.ViewModel, JadwalContract.ViewModel {

    private Message message = new Message();
    private Mediator mediator;
    private FragmentKoorMahasiswaBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_koor_mahasiswa,container,false);
        mediator = new ConcreteMediator((AppCompatActivity) getActivity());
        mediator.setMahasiswaPresenter(this);
        mediator.setJadwalPresenter(this);
        binding.setPresenter(mediator.getMahasiswaPresenter());

        mediator.message(message.setComponent("ProgressDialog").setEvent("set"));
        mediator.message(message.setComponent("SessionManager").setEvent("set"));
        mediator.message(message.setComponent("ProdiMahasiswaAdapter").setEvent("set"));
        mediator.setRecyclerView(binding.recyclerView);
        mediator.setRefreshLayout(binding.refresh);
        binding.setToken(mediator.getSessionToken());
        if (mediator.getSessionPengguna().equalsIgnoreCase("prodi")){
            binding.frgKoorDosenHomeFab.setVisibility(View.VISIBLE);
        }
        mediator.message(message.setComponent("MahasiswaPresenter").setEvent("getAllData"));
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onGetObjectMahasiswa(Mahasiswa mahasiswa) {

    }

    @Override
    public void onGetListMahasiswa(List<Mahasiswa> mahasiswa) {
        ArrayList<Mahasiswa> arrayList = new ArrayList<>();
        arrayList.addAll(mahasiswa);
        mediator.message(message.setComponent("ProdiMahasiswaAdapter").setEvent("addItem").setItem(arrayList));
        mediator.message(message.setComponent("ProdiMahasiswaAdapter").setEvent("setAdapter"));
        mediator.message(message.setComponent("RefreshLayout").setEvent("setRefreshing").setEnabled(false));
        mediator.setRelativeLayout(binding.includeLayout.viewEmptyview);
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
        mediator.setRelativeLayout(binding.layoutKonfirmasi.containerAskAllSidang);
        mediator.setButton(binding.layoutKonfirmasi.btnAcceptAllBimbingan);
        mediator.message(message.setComponent("JadwalPresenter").setEvent("showKegiatanList").setText("ProdiMahasiswaFragment"));
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
                mediator.setRelativeLayout(binding.includeLayout.viewEmptyview);
                mediator.message(message.setComponent("RelativeLayout").setEvent("setVisibility").setVisibility(View.VISIBLE));
                break;
            case "onSuccessAsk":
                mediator.message(message.setComponent("Toasty").setEvent("Success").setText("Berhasil melakukan aksi!"));
                break;
            case "FloatButton":
                mediator.selectIntent(new Message().setaClass(ProdiMahasiswaEditorActivity.class));
                break;
            default:
                mediator.message(message.setComponent("Toasty").setEvent("Warning").setText(messages));
                break;
        }
    }
}
