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

import org.d3ifcool.finpro.core.interfaces.InformasiContract;
import org.d3ifcool.finpro.core.interfaces.lists.InformasiListView;
import org.d3ifcool.finpro.core.mediators.interfaces.prodi.ProdiFragmentMediator;
import org.d3ifcool.finpro.core.mediators.prodi.ProdiConcrete;
import org.d3ifcool.finpro.core.mediators.prodi.ProdiFragmentConcrete;
import org.d3ifcool.finpro.core.models.Informasi;
import org.d3ifcool.finpro.core.models.Plotting;
import org.d3ifcool.finpro.core.presenters.InformasiPresenter;
import org.d3ifcool.finpro.databinding.FragmentKoorInformasiBinding;
import org.d3ifcool.finpro.prodi.activities.editor.create.ProdiInformasiTambahActivity;
import org.d3ifcool.finpro.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProdiInformasiFragment extends Fragment implements InformasiContract.ViewModel {

    private ArrayList<Informasi> arrayList = new ArrayList<>();
    private InformasiPresenter informasiPresenter;

    private ProdiConcrete mediator;
    private FragmentKoorInformasiBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_koor_informasi,container,false);
        informasiPresenter = new InformasiPresenter(this);
        binding.setPresenter(informasiPresenter);

        mediator = new ProdiConcrete((AppCompatActivity) getActivity());
        mediator.message("ProgressDialog","set");
        mediator.message("InformasiViewAdapter","set");

        informasiPresenter.getAllInformasi();
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        informasiPresenter.getAllInformasi();
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
                mediator.selectIntent(ProdiInformasiTambahActivity.class);
                break;
            default:
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
