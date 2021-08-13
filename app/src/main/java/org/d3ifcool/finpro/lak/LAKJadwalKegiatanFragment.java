package org.d3ifcool.finpro.lak;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.Message;
import org.d3ifcool.finpro.core.interfaces.JadwalContract;
import org.d3ifcool.finpro.core.mediators.Mediator;
import org.d3ifcool.finpro.core.mediators.ConcreteMediator;
import org.d3ifcool.finpro.core.models.JadwalKegiatan;
import org.d3ifcool.finpro.databinding.FragmentLakJadwalKegiatanBinding;
import java.util.List;

public class LAKJadwalKegiatanFragment extends Fragment implements JadwalContract.ViewModel {

    private FragmentLakJadwalKegiatanBinding mBinding;
    private Message message = new Message();
    private Mediator mediator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_lak_jadwal_kegiatan,container,false);
        mBinding.setLifecycleOwner(this);
        mediator = new ConcreteMediator((AppCompatActivity) getActivity());
        mediator.setJadwalPresenter(this);
        mBinding.setPresenter(mediator.getJadwalPresenter());

        mediator.message(message.setComponent("ProgressDialog").setEvent("set"));
        mediator.message(message.setComponent("SessionManager").setEvent("set"));

        mediator.setTextView(mBinding.tvBulan);
        mediator.setCompactCalendarView(mBinding.compactcalendarView);
        mediator.setRecyclerView(mBinding.recyclerView);

        return mBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        mediator.message(message.setComponent("JadwalPresenter").setEvent("getAllData"));
    }

    @Override
    public void onGetListJadwal(List<JadwalKegiatan> kegiatanList) {
        message.setKegiatanList(kegiatanList);
        mediator.message(message.setComponent("CompactCalendar").setEvent("setListener"));
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
            case "onCreate":
                mediator.selectIntent(message.setaClass(LAKJadwalEditorActivity.class));
                break;
            default:
                mediator.message(message.setComponent("Toasty").setEvent("Warning").setText(messages));
                break;
        }
    }
}
