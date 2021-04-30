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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentProdiDosenBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_prodi_dosen,container,false);

        dosenPresenter = new DosenPresenter(this);
        mediator = new ProdiConcrete((AppCompatActivity) getActivity());
        mediator.message("ProgressDialog","set");
        mediator.message("DosenViewAdapter","set");
        mediator.setFloatingActionButton(binding.floatingActionButton, ProdiDosenTambahActivity.class);

        mediator.setRecyclerView(binding.recyclerView);

        mediator.setRefreshLayout(binding.refresh);
        mediator.getRefreshLayout().setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                dosenPresenter.getAllDosen();
            }
        });

        mediator.setRelativeLayout(binding.includeLayout.viewEmptyview);

        dosenPresenter.getAllDosen();

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        dosenPresenter.getAllDosen();
    }

    @Override
    public void showProgress() {
        mediator.getProgressDialog().show();
    }

    @Override
    public void hideProgress() {
        mediator.getProgressDialog().dismiss();
    }

    @Override
    public void onGetObjectDosen(Dosen dosen) {

    }

    @Override
    public void isEmptyObjectDosen() {

    }

    @Override
    public void onGetListDosen(List<Dosen> dosen) {
        arrayList.clear();
        arrayList.addAll(dosen);
        mediator.getDosenViewAdapter().setDosens(arrayList);
        mediator.getRecyclerView().setAdapter(mediator.getDosenViewAdapter());
        mediator.getRefreshLayout().setRefreshing(false);
        if (arrayList.size() == 0){
            mediator.getRelativeLayout().setVisibility(View.VISIBLE);
        }else{
            mediator.getRelativeLayout().setVisibility(View.GONE);
        }
    }

    @Override
    public void isEmptyListDosen() {
        mediator.getRelativeLayout().setVisibility(View.VISIBLE);
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFailed(String message) {
        Toasty.error(getContext(), message, Toasty.LENGTH_SHORT).show();
    }
}
