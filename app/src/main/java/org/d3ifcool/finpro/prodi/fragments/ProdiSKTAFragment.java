package org.d3ifcool.finpro.prodi.fragments;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.interfaces.lists.MahasiswaListView;
import org.d3ifcool.finpro.core.mediators.prodi.ConcreteMediator;
import org.d3ifcool.finpro.core.models.Mahasiswa;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProdiSKTAFragment extends Fragment implements MahasiswaListView {

    private ArrayList<Mahasiswa> arrayList = new ArrayList<>();
//    private MahasiswaPresenters mahasiswaPresenter;
    private ConcreteMediator mediator;

    public ProdiSKTAFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_koor_mahasiswa, container, false);

       /* mediator = new ProdiFragmentConcrete(view);
        mediator.message("RefreshLayout");
        mediator.message("RecycleView");
        mediator.message("EmptyView");
        mediator.message("ProgressDialog");
        mediator.message("ProdiSKTAAdapter");
        mediator.getFloatingButton().setVisibility(View.GONE);*/

//        mahasiswaPresenter = new MahasiswaPresenters(this);
//        mahasiswaPresenter.initContext(getContext());
//        mahasiswaPresenter.getMahasiswa();

        /*mediator.getRefreshLayout().setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mahasiswaPresenter.getMahasiswa();
            }
        });*/
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
//        mahasiswaPresenter.getMahasiswa();
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
    public void onGetListMahasiswa(List<Mahasiswa> mahasiswa) {

        arrayList.clear();
        for (int i = 0; i < mahasiswa.size(); i++) {
            if (mahasiswa.get(i).getSk_status() != 2){
                arrayList.add(mahasiswa.get(i));
                Log.e("TAG", "onGetListMahasiswa: "+mahasiswa.get(i).getSk_status() );
            }
        }

        /*mediator.getProdiSKTAAdapter().setmMahasiswa(arrayList);
        mediator.getRecycleView().setAdapter(mediator.getProdiSKTAAdapter());
        mediator.getRefreshLayout().setRefreshing(false);

        if (arrayList.size() == 0) {
            mediator.getView().setVisibility(View.VISIBLE);
        } else {
            mediator.getView().setVisibility(View.GONE);
        }*/

    }

    @Override
    public void isEmptyListMahasiswa() {
        //mediator.getView().setVisibility(View.VISIBLE);
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
