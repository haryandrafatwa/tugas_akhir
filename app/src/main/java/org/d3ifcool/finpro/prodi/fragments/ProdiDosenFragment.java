package org.d3ifcool.finpro.prodi.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.d3ifcool.finpro.core.interfaces.lists.DosenListView;
import org.d3ifcool.finpro.core.mediators.interfaces.prodi.ProdiFragmentMediator;
import org.d3ifcool.finpro.core.mediators.prodi.ProdiFragmentConcrete;
import org.d3ifcool.finpro.core.models.Dosen;
import org.d3ifcool.finpro.core.presenters.DosenPresenters;
import org.d3ifcool.finpro.prodi.activities.editor.create.ProdiDosenTambahActivity;
import org.d3ifcool.finpro.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProdiDosenFragment extends Fragment implements DosenListView {

    private ArrayList<Dosen> arrayList = new ArrayList<>();
    private DosenPresenters dosenPresenters;

    private ProdiFragmentMediator mediator;


    public ProdiDosenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_koor_dosen, container, false);

        dosenPresenters = new DosenPresenters(this);
        dosenPresenters.initContext(getContext());

        mediator = new ProdiFragmentConcrete(view);
        mediator.message("RefreshLayout");
        mediator.message("RecycleView");
        mediator.message("EmptyView");
        mediator.message("ProgressDialog");
        mediator.message("ProdiDosenAdapter");
        mediator.message("FloatingAButton", ProdiDosenTambahActivity.class);

        dosenPresenters.getDosen();

        mediator.getRefreshLayout().setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                dosenPresenters.getDosen();
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        dosenPresenters.getDosen();
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
    public void onFailed(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
