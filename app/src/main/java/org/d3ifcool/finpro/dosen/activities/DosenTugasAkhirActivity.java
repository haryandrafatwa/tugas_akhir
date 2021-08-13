package org.d3ifcool.finpro.dosen.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.d3ifcool.finpro.core.helpers.Message;
import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.interfaces.BimbinganContract;
import org.d3ifcool.finpro.core.interfaces.SidangContract;
import org.d3ifcool.finpro.core.mediators.Mediator;
import org.d3ifcool.finpro.core.mediators.ConcreteMediator;
import org.d3ifcool.finpro.core.models.Bimbingan;
import org.d3ifcool.finpro.core.models.Dosen;
import org.d3ifcool.finpro.core.models.Mahasiswa;
import org.d3ifcool.finpro.core.models.Sidang;
import org.d3ifcool.finpro.databinding.ActivityDosenTugasAkhirBinding;
import org.d3ifcool.finpro.dosen.activities.detail.SidangDetailActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import es.dmoral.toasty.Toasty;
import okhttp3.ResponseBody;

import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.EXTRA_MAHASISWA;

public class DosenTugasAkhirActivity extends AppCompatActivity implements BimbinganContract .ViewModel, SidangContract.ViewModel {

    private ActivityDosenTugasAkhirBinding mBinding;
    private Message message = new Message();
    private Mediator mediator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_dosen_tugas_akhir);
        mBinding.setLifecycleOwner(this);
        mediator = new ConcreteMediator(this);
        mediator.setBimbinganPresenter(this);
        mediator.setSidangPresenter(this);

        mediator.setTitleContextWithHomeAsUp("Detail Tugas Akhir");

        mediator.message(message.setComponent("ProgressDialog").setEvent("set"));
        mediator.message(message.setComponent("SessionManager").setEvent("set"));

        mBinding.frgMhsPaCardviewSidang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediator.selectIntent(message.setaClass(SidangDetailActivity.class).setExtraIntent(getIntent().getStringExtra(EXTRA_MAHASISWA)));
            }
        });
        mBinding.frgMhsPaCardviewBimbingan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediator.selectIntent(message.setaClass(DosenTugasAkhirBimbinganActivity.class).setExtraIntent(getIntent().getStringExtra(EXTRA_MAHASISWA)));
            }
        });
        mBinding.layoutAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediator.message(message.setComponent("SidangPresenter").setEvent("updateStatusSidang").setText(getIntent().getStringExtra(EXTRA_MAHASISWA)).setUrl("dijadwalkan"));
            }
        });
        mBinding.layoutTolak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediator.message(message.setComponent("SidangPresenter").setEvent("updateStatusSidang").setText(getIntent().getStringExtra(EXTRA_MAHASISWA)).setUrl("ditolak"));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mediator.message(message.setComponent("BimbinganPresenter").setEvent("getBimbinganByUsername").setText(getIntent().getStringExtra(EXTRA_MAHASISWA)));
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
    public void onGetListBimbingan(List<Bimbingan> bimbinganList, Dosen dosen, Mahasiswa mahasiswa) {
        mBinding.setModel(mahasiswa);
        mediator.setTextView(mBinding.actDsnPaBimbinganTextviewStatusSidang);
        if (mahasiswa.getSidang_status() != null){
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
        }else{
            mediator.message(message.setComponent("TextView").setEvent("setText").setText("Belum terdaftar sidang"));
            mediator.message(message.setComponent("TextView").setEvent("setTextColor").setColor("#FFFF0000"));
            mBinding.frgMhsPaCardviewSidang.setEnabled(false);
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
    public void onGetListMahasiswa(List<Mahasiswa> mahasiswaList) {

    }

    @Override
    public void onGetObjectSidang(Sidang sidang) {

    }

    @Override
    public void onGetObjectMahasiswa(Mahasiswa mahasiswa) {

    }

    @Override
    public void onGetBody(ResponseBody body, String filename) {

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
            case "onSuccess":
                mediator.message(message.setComponent("Toasty").setEvent("Success").setText("Berhasil konfirmasi pengajuan sidang"));
                onResume();
                break;
            default:
                mediator.message(message.setComponent("Toasty").setEvent("Warning").setText(messages));
                break;
        }
    }
}
