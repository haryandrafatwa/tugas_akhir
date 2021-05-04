package org.d3ifcool.finpro.prodi.fragments;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.d3ifcool.finpro.core.interfaces.DosenContract;
import org.d3ifcool.finpro.core.mediators.prodi.ProdiConcrete;
import org.d3ifcool.finpro.core.models.Dosen;
import org.d3ifcool.finpro.core.presenters.DosenPresenter;
import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.databinding.FragmentProdiDosenBinding;
import org.d3ifcool.finpro.prodi.activities.editor.create.ProdiDosenTambahActivity;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProdiDosenFragment extends Fragment implements DosenContract.ViewModel {

    private ArrayList<Dosen> arrayList = new ArrayList<>();

    private DosenPresenter dosenPresenter;
    private ProdiConcrete mediator;
    private FragmentProdiDosenBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_prodi_dosen,container,false);
        dosenPresenter = new DosenPresenter(this);
        binding.setPresenter(dosenPresenter);
        mediator = new ProdiConcrete((AppCompatActivity) getActivity());
        mediator.message("ProgressDialog","set");
        mediator.message("DosenViewAdapter","set");

        dosenPresenter.getAllDosen();

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        dosenPresenter.getAllDosen();
    }

    @Override
    public void onGetObjectDosen(Dosen dosen) {

    }

    @Override
    public void onGetListDosen(List<Dosen> dosen) {
        arrayList.clear();
        arrayList.addAll(dosen);
        mediator.getDosenViewAdapter().setDosens(arrayList);
        binding.recyclerView.setAdapter(mediator.getDosenViewAdapter());
        binding.refresh.setRefreshing(false);
        if (arrayList.size() == 0){
            binding.includeLayout.viewEmptyview.setVisibility(View.VISIBLE);
        }else{
            binding.includeLayout.viewEmptyview.setVisibility(View.GONE);
        }
    }

    @Override
    public void onMessage(String message) {
        switch (message){
            case "ShowProgressDialog":
                mediator.getProgressDialog().show();
                break;
            case "HideProgressDialog":
                mediator.getProgressDialog().dismiss();
                break;
            case "EmptyList":
                binding.includeLayout.viewEmptyview.setVisibility(View.VISIBLE);
                break;
            case "FloatButton":
                mediator.selectIntent(ProdiDosenTambahActivity.class);
                break;
            default:
                Toasty.error(getContext(), message, Toasty.LENGTH_SHORT).show();
                break;
        }
    }
}
