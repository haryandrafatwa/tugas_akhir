package org.d3ifcool.finpro.prodi.fragments;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.d3ifcool.finpro.core.interfaces.MahasiswaContract;
import org.d3ifcool.finpro.core.interfaces.lists.MahasiswaListView;
import org.d3ifcool.finpro.core.mediators.interfaces.prodi.ProdiFragmentMediator;
import org.d3ifcool.finpro.core.mediators.prodi.ProdiConcrete;
import org.d3ifcool.finpro.core.mediators.prodi.ProdiFragmentConcrete;
import org.d3ifcool.finpro.core.models.Mahasiswa;
import org.d3ifcool.finpro.core.models.Plotting;
import org.d3ifcool.finpro.core.presenters.DosenPresenter;
import org.d3ifcool.finpro.core.presenters.MahasiswaPresenter;
import org.d3ifcool.finpro.core.presenters.MahasiswaPresenters;
import org.d3ifcool.finpro.databinding.FragmentKoorMahasiswaBinding;
import org.d3ifcool.finpro.prodi.activities.editor.create.ProdiMahasiswaTambahActivity;
import org.d3ifcool.finpro.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProdiMahasiswaFragment extends Fragment implements MahasiswaContract.ViewModel {

    private ArrayList<Mahasiswa> arrayList = new ArrayList<>();
    private MahasiswaPresenter mahasiswaPresenter;
    private ProdiConcrete mediator;
    private FragmentKoorMahasiswaBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_koor_mahasiswa,container,false);
        mahasiswaPresenter = new MahasiswaPresenter(this);
        binding.setPresenter(mahasiswaPresenter);

        mediator = new ProdiConcrete((AppCompatActivity) getActivity());
        mediator.message("ProgressDialog","set");
        mediator.message("MahasiswaViewAdapter","set");

        mahasiswaPresenter.getAllMahasiswa();
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        mahasiswaPresenter.getAllMahasiswa();
    }

    @Override
    public void onGetObjectMahasiswa(Mahasiswa mahasiswa) {

    }

    @Override
    public void onGetListMahasiswa(List<Mahasiswa> mahasiswa) {

        arrayList.clear();
        arrayList.addAll(mahasiswa);

        mediator.getMahasiswaViewAdapter().setmMahasiswa(arrayList);
        binding.recyclerView.setAdapter(mediator.getMahasiswaViewAdapter());
        binding.refresh.setRefreshing(false);
        if (arrayList.size() == 0){
            binding.includeLayout.viewEmptyview.setVisibility(View.VISIBLE);
        }else{
            binding.includeLayout.viewEmptyview.setVisibility(View.GONE);
        }

    }

    @Override
    public void onSuccessGetPlotting(Plotting plotting) {

    }

    @Override
    public void onMessage(String message) {
        switch (message){
            case "ShowProgressDialog":
                mediator.getProgressDialog().show();
                break;
            case "HideProgressDialog":
                mediator.getProgressDialog().dismiss();
            case "EmptyList":
                binding.includeLayout.viewEmptyview.setVisibility(View.VISIBLE);
                break;
            case "FloatButton":
                mediator.selectIntent(ProdiMahasiswaTambahActivity.class);
                break;
            default:
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
