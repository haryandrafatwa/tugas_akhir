package org.d3ifcool.finpro.prodi.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.d3ifcool.finpro.core.interfaces.lists.InformasiListView;
import org.d3ifcool.finpro.core.mediators.interfaces.prodi.ProdiFragmentMediator;
import org.d3ifcool.finpro.core.mediators.prodi.ProdiFragmentConcrete;
import org.d3ifcool.finpro.core.models.Informasi;
import org.d3ifcool.finpro.core.presenters.InformasiPresenter;
import org.d3ifcool.finpro.prodi.activities.editor.create.ProdiInformasiTambahActivity;
import org.d3ifcool.finpro.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProdiInformasiFragment extends Fragment implements InformasiListView {

    private ArrayList<Informasi> arrayList = new ArrayList<>();
    private InformasiPresenter informasiPresenter;

    private ProdiFragmentMediator mediator;

    public ProdiInformasiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_koor_informasi, container, false);

        mediator = new ProdiFragmentConcrete(rootView);
        mediator.message("RefreshLayout");
        mediator.message("RecycleView");
        mediator.message("EmptyView");
        mediator.message("ProgressDialog");
        mediator.message("ProdiInformasiAdapter");
        mediator.message("FloatingAButton", ProdiInformasiTambahActivity.class);

        informasiPresenter = new InformasiPresenter(this);
        informasiPresenter.initContext(getContext());
        informasiPresenter.getInformasi();

        mediator.getRefreshLayout().setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                informasiPresenter.getInformasi();
            }
        });
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        informasiPresenter.getInformasi();
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
    public void onGetListInformasi(List<Informasi> informasi) {

        arrayList.clear();
        arrayList.addAll(informasi);

        mediator.getKoorInformasiAdapter().addItem(arrayList);
        mediator.getRefreshLayout().setRefreshing(false);
        mediator.getRecycleView().setAdapter(mediator.getKoorInformasiAdapter());

        if (arrayList.size() == 0) {
            mediator.getView().setVisibility(View.VISIBLE);
        } else {
            mediator.getView().setVisibility(View.GONE);
        }

    }

    @Override
    public void isEmptyListInformasi() {
        mediator.getView().setVisibility(View.VISIBLE);
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
