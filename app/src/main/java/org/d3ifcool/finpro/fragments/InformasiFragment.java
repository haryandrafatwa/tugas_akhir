package org.d3ifcool.finpro.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.activities.detail.InformasiTambahActivity;
import org.d3ifcool.finpro.core.helpers.Message;
import org.d3ifcool.finpro.core.interfaces.InformasiContract;
import org.d3ifcool.finpro.core.mediators.Mediator;
import org.d3ifcool.finpro.core.mediators.ConcreteMediator;
import org.d3ifcool.finpro.core.models.Informasi;
import org.d3ifcool.finpro.core.presenters.InformasiPresenter;
import org.d3ifcool.finpro.databinding.FragmentKoorInformasiBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class InformasiFragment extends Fragment implements InformasiContract.ViewModel {

    private ArrayList<Informasi> arrayList = new ArrayList<>();
    private InformasiPresenter informasiPresenter;

    private Mediator mediator;
    private FragmentKoorInformasiBinding binding;
    private Message message = new Message();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_koor_informasi,container,false);
        informasiPresenter = new InformasiPresenter(this);
        binding.setPresenter(informasiPresenter);

        mediator = new ConcreteMediator((AppCompatActivity) getActivity());
        mediator.message(message.setComponent("ProgressDialog").setEvent("set"));
        mediator.message(message.setComponent("SessionManager").setEvent("set"));
        mediator.message(message.setComponent("InformasiViewAdapter").setEvent("set"));
        binding.setToken(mediator.getSessionManager().getSessionToken());
        if (mediator.getSessionManager().getSessionPengguna().equalsIgnoreCase("mahasiswa")){
            binding.frgKoorDosenHomeFab.setVisibility(View.GONE);
        }
        informasiPresenter.getAllInformasi(mediator.getSessionManager().getSessionToken());
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        informasiPresenter.getAllInformasi(mediator.getSessionManager().getSessionToken());
    }

    @Override
    public void onGetObjectInformasi(Informasi informasi) {

    }

    @Override
    public void onGetListInformasi(List<Informasi> informasi) {

        arrayList.clear();
        arrayList.addAll(informasi);

        mediator.getInformasiViewAdapter().addItem(arrayList);
        binding.refresh.setRefreshing(false);
        binding.recyclerView.setAdapter(mediator.getInformasiViewAdapter());

        if (arrayList.size() == 0) {
            binding.includeLayout.viewEmptyview.setVisibility(View.VISIBLE);
        } else {
            binding.includeLayout.viewEmptyview.setVisibility(View.GONE);
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
                binding.includeLayout.viewEmptyview.setVisibility(View.VISIBLE);
                break;
            case "FloatButton":
                mediator.selectIntent(new Message().setaClass(InformasiTambahActivity.class));
                break;
            default:
                mediator.message(message.setComponent("Toasty").setEvent("Warning").setText(messages));
                break;
        }
    }
}
