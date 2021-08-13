package org.d3ifcool.finpro.dosen.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.Message;
import org.d3ifcool.finpro.core.interfaces.DosenContract;
import org.d3ifcool.finpro.core.mediators.Mediator;
import org.d3ifcool.finpro.core.mediators.ConcreteMediator;
import org.d3ifcool.finpro.core.models.Dosen;
import org.d3ifcool.finpro.core.models.Mahasiswa;
import org.d3ifcool.finpro.databinding.ActivityDosenSidangTugasAkhirBinding;
import org.d3ifcool.finpro.dosen.activities.detail.SidangDetailActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import es.dmoral.toasty.Toasty;

import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.EXTRA_MAHASISWA;

public class DosenSidangActivity extends AppCompatActivity implements DosenContract.ViewModel {

    private ActivityDosenSidangTugasAkhirBinding mBinding;
    private Message message = new Message();
    private Mediator mediator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_dosen_sidang_tugas_akhir);
        mBinding.setLifecycleOwner(this);
        mediator = new ConcreteMediator(this);
        mediator.setDosenPresenter(this);

        mediator.setTitleContextWithHomeAsUp("Detail Tugas Akhir");

        mediator.message(message.setComponent("ProgressDialog").setEvent("set"));
        mediator.message(message.setComponent("SessionManager").setEvent("set"));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mediator.message(message.setComponent("DosenPresenter").setEvent("getMahasiswaSidangByUsername").setText(getIntent().getStringExtra(EXTRA_MAHASISWA)));
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
    public void onGetObjectDosen(Dosen dosen) {

    }

    @Override
    public void onGetListDosen(List<Dosen> dosenList) {

    }

    @Override
    public void onGetObjectMahasiswa(Mahasiswa mahasiswa) {
        mBinding.setModel(mahasiswa);
        mediator.setTextView(mBinding.actDsnPaBimbinganTextviewStatusSidang);
        if (mahasiswa.getSidang_status().equalsIgnoreCase("terjadwalkan")){
            mediator.message(message.setComponent("TextView").setEvent("setText").setText(mahasiswa.setSidangStatus()));
            mediator.message(message.setComponent("TextView").setEvent("setTextColor").setColor("#ff2c3b41"));
        }else if(mahasiswa.getSidang_status().equalsIgnoreCase("revisi")){
            mediator.message(message.setComponent("TextView").setEvent("setText").setText("Lulus Bersyarat"));
            mediator.message(message.setComponent("TextView").setEvent("setTextColor").setColor("#FF888888"));
        }else if(mahasiswa.getSidang_status().equalsIgnoreCase("tidak lulus")){
            mediator.message(message.setComponent("TextView").setEvent("setText").setText("Tidak Lulus"));
            mediator.message(message.setComponent("TextView").setEvent("setTextColor").setColor("#FFFF0000"));
        }else{
            mediator.message(message.setComponent("TextView").setEvent("setText").setText("Lulus"));
            mediator.message(message.setComponent("TextView").setEvent("setTextColor").setColor("#FF4CAF50"));
        }
        mBinding.frgMhsPaCardviewSidang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date openDate = format.parse(mahasiswa.getSidang_tanggal());
                    Date now = new Date();
                    if(now.before(openDate)){
                        onMessage("Sidang belum berlangsung!");
                    }else{
                        mediator.selectIntent(message.setaClass(SidangDetailActivity.class).setExtraIntent(mahasiswa.getMhs_nim()));
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onGetListMahasiswa(List<Mahasiswa> mahasiswasList) {

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
            default:
                mediator.message(message.setComponent("Toasty").setEvent("Warning").setText(messages));
                break;
        }
    }
}
