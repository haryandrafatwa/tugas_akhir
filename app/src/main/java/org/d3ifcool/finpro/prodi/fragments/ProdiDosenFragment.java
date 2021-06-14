package org.d3ifcool.finpro.prodi.fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.d3ifcool.finpro.core.helpers.Message;
import org.d3ifcool.finpro.core.interfaces.DosenContract;
import org.d3ifcool.finpro.core.mediators.prodi.ConcreteMediator;
import org.d3ifcool.finpro.core.models.Dosen;
import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.databinding.FragmentProdiDosenBinding;
import org.d3ifcool.finpro.prodi.activities.editor.ProdiDosenEditorActivity;

import java.util.ArrayList;
import java.util.List;

public class ProdiDosenFragment extends Fragment implements DosenContract.ViewModel {

    private Message message = new Message();
    private ConcreteMediator mediator;
    private FragmentProdiDosenBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_prodi_dosen,container,false);
        mediator = new ConcreteMediator((AppCompatActivity) getActivity());
        mediator.setDosenPresenter(this);
        binding.setPresenter(mediator.getDosenPresenter());

        mediator.message(message.setComponent("ProgressDialog").setEvent("set"));
        mediator.message(message.setComponent("ProdiDosenAdapter").setEvent("set"));
        mediator.message(message.setComponent("SessionManager").setEvent("set"));

        mediator.setRecyclerView(binding.recyclerView);
        mediator.setRelativeLayout(binding.includeLayout.viewEmptyview);
        mediator.setRefreshLayout(binding.refresh);

        binding.setToken(mediator.getSessionToken());
        mediator.message(message.setComponent("DosenPresenter").setEvent("getAllData"));

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        mediator.message(message.setComponent("DosenPresenter").setEvent("getAllData"));
    }

    @Override
    public void onGetObjectDosen(Dosen dosen) {

    }

    @Override
    public void onGetListDosen(List<Dosen> dosen) {
        ArrayList<Dosen> arrayList = new ArrayList<>();
        arrayList.clear();
        arrayList.addAll(dosen);
        mediator.message(message.setComponent("ProdiDosenAdapter").setEvent("addItem").setItem(arrayList));
        mediator.message(message.setComponent("ProdiDosenAdapter").setEvent("setAdapter"));
        mediator.message(message.setComponent("RefreshLayout").setEvent("setRefreshing").setEnabled(false));
        if (arrayList.size() == 0) {
            mediator.message(message.setComponent("RelativeLayout").setEvent("setVisibility").setVisibility(View.VISIBLE));
        } else {
            mediator.message(message.setComponent("RelativeLayout").setEvent("setVisibility").setVisibility(View.GONE));
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
            case "EmptyList":
                mediator.message(message.setComponent("RelativeLayout").setEvent("setVisibility").setVisibility(View.VISIBLE));
                break;
            case "FloatButton":
                mediator.selectIntent(ProdiDosenEditorActivity.class);
                break;
            default:
                mediator.message(message.setComponent("Toasty").setEvent("Warning").setText(messages));
                break;
        }
    }
}
