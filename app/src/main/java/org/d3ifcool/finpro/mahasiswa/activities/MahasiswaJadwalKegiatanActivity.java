package org.d3ifcool.finpro.mahasiswa.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.d3ifcool.finpro.core.helpers.Message;
import org.d3ifcool.finpro.core.interfaces.JadwalContract;
import org.d3ifcool.finpro.core.interfaces.lists.KegiatanListView;
import org.d3ifcool.finpro.core.mediators.interfaces.prodi.Mediator;
import org.d3ifcool.finpro.core.mediators.prodi.ConcreteMediator;
import org.d3ifcool.finpro.core.models.JadwalKegiatan;
import org.d3ifcool.finpro.core.models.Kegiatan;
import org.d3ifcool.finpro.core.presenters.KegiatanPresenter;
import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.databinding.ActivityMahasiswaJadwalKegiatanBinding;
import org.d3ifcool.finpro.lak.LAKJadwalEditorActivity;
import org.d3ifcool.finpro.mahasiswa.adapters.recyclerview.MahasiswaJadwalKegiatanViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MahasiswaJadwalKegiatanActivity extends AppCompatActivity implements JadwalContract.ViewModel {

    private ActivityMahasiswaJadwalKegiatanBinding mBinding;
    private Message message = new Message();
    private Mediator mediator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_mahasiswa_jadwal_kegiatan);
        mBinding.setLifecycleOwner(this);
        mediator = new ConcreteMediator(this);
        mediator.setJadwalPresenter(this);

        mediator.setTitleContextWithHomeAsUp("Jadwal Kegiatan");

        mediator.message(message.setComponent("ProgressDialog").setEvent("set"));
        mediator.message(message.setComponent("SessionManager").setEvent("set"));

        mediator.setTextView(mBinding.tvBulan);
        mediator.setCompactCalendarView(mBinding.compactcalendarView);
        mediator.setRecyclerView(mBinding.recyclerView);

    }

    @Override
    public void onResume() {
        super.onResume();
        mediator.message(message.setComponent("JadwalPresenter").setEvent("getAllData"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mediator.message(message.setComponent("Toolbar").setVisibility(item.getItemId()));
        return super.onOptionsItemSelected(item);
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
