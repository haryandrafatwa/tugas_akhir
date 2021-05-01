package org.d3ifcool.finpro.prodi.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.interfaces.DosenContract;
import org.d3ifcool.finpro.core.mediators.interfaces.prodi.ProdiFragmentMediator;
import org.d3ifcool.finpro.core.mediators.prodi.ProdiFragmentConcrete;
import org.d3ifcool.finpro.core.models.Dosen;
import org.d3ifcool.finpro.core.models.manager.DosenManager;
import org.d3ifcool.finpro.prodi.activities.editor.create.ProdiDosenTambahActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProdiDosenFragments extends Fragment implements DosenContract.ViewModel {

    private ArrayList<Dosen> arrayList = new ArrayList<>();
    private DosenManager dosenManager;

    private ProdiFragmentMediator mediator;


    public ProdiDosenFragments() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_prodi_dosen, container, false);

        dosenManager = new DosenManager(this);
        dosenManager.initContext(getContext());

        mediator = new ProdiFragmentConcrete(view);
        mediator.message("RefreshLayout");
        mediator.message("RecycleView");
        mediator.message("EmptyView");
        mediator.message("ProgressDialog");
        mediator.message("ProdiDosenAdapter");
        mediator.message("FloatingAButton", ProdiDosenTambahActivity.class);

        dosenManager.getDosen();

        mediator.getRefreshLayout().setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                dosenManager.getDosen();
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        dosenManager.getDosen();
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

        mediator.getKoorDosenAdapter().setDosens(arrayList);
        mediator.getRecycleView().setAdapter(mediator.getKoorDosenAdapter());
        mediator.getRefreshLayout().setRefreshing(false);

        if (arrayList.size() == 0) {
            mediator.getView().setVisibility(View.VISIBLE);
        } else {
            mediator.getView().setVisibility(View.GONE);
        }
    }

    @Override
    public void isEmptyListDosen() {
        mediator.getView().setVisibility(View.VISIBLE);
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickFloatButton() {

    }
}
