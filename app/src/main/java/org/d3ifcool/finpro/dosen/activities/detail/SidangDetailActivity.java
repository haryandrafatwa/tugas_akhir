package org.d3ifcool.finpro.dosen.activities.detail;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.Message;
import org.d3ifcool.finpro.core.interfaces.SidangContract;
import org.d3ifcool.finpro.core.mediators.Mediator;
import org.d3ifcool.finpro.core.mediators.ConcreteMediator;
import org.d3ifcool.finpro.core.models.Mahasiswa;
import org.d3ifcool.finpro.core.models.Sidang;
import org.d3ifcool.finpro.databinding.ActivityDosenSidangDetailBinding;
import org.d3ifcool.finpro.dosen.activities.editor.NilaiSidangEditorActivity;
import org.d3ifcool.finpro.dosen.activities.editor.ReviewSidangEditorActivity;

import okhttp3.ResponseBody;

import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.EXTRA_DEFAULT;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.ROLE_PRODI;

public class SidangDetailActivity extends AppCompatActivity implements SidangContract.ViewModel {

    private ActivityDosenSidangDetailBinding mBinding;
    private Message message = new Message();
    private Mediator mediator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_dosen_sidang_detail);
        mBinding.setLifecycleOwner(this);
        mediator = new ConcreteMediator(this);
        mediator.setSidangPresenter(this);

        mediator.setTitleContextWithHomeAsUp("Detail Sidang Tugas Akhir");

        mediator.message(message.setComponent("ProgressDialog").setEvent("set"));
        mediator.message(message.setComponent("SessionManager").setEvent("set"));
        mediator.message(message.setComponent("FileHelper").setEvent("set"));

        mBinding.frgMhsPaCardviewSidang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediator.getSessionPengguna().equalsIgnoreCase(ROLE_PRODI)){
                    if (message.getMahasiswa().getNilai_pembimbing_1() != null && message.getMahasiswa().getNilai_pembimbing_2() != null &&
                            message.getMahasiswa().getNilai_penguji_1() != null && message.getMahasiswa().getNilai_penguji_2() != null){
                        mediator.selectIntent(message.setaClass(ReviewSidangEditorActivity.class).setExtraIntent(getIntent().getStringExtra(EXTRA_DEFAULT)));
                    }else{
                        onMessage("Nilai sidang belum lengkap.");
                    }
                }else{
                    if (mediator.getSessionUsername().equalsIgnoreCase(message.getMahasiswa().getNip_penguji_1()) || mediator.getSessionUsername().equalsIgnoreCase(message.getMahasiswa().getNip_penguji_2())){
                        if (message.getMahasiswa().getNilai_pembimbing_1() != null && message.getMahasiswa().getNilai_pembimbing_2() != null &&
                                message.getMahasiswa().getNilai_penguji_1() != null && message.getMahasiswa().getNilai_penguji_2() != null){
                            mediator.selectIntent(message.setaClass(ReviewSidangEditorActivity.class).setExtraIntent(getIntent().getStringExtra(EXTRA_DEFAULT)));
                        }else{
                            onMessage("Nilai sidang belum lengkap.");
                        }
                    }else{
                        onMessage("Anda tidak memiliki hak untuk mengakses ini.");
                    }
                }
            }
        });
        mBinding.frgMhsPaCardviewNilaiPembimbing1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediator.getSessionPengguna().equalsIgnoreCase(ROLE_PRODI)){
                    mediator.selectIntent(message.setaClass(NilaiSidangEditorActivity.class).setExtraIntent(getIntent().getStringExtra(EXTRA_DEFAULT)).setSecondExtraIntent("Pembimbing 1"));
                }else{
                    if (mediator.getSessionUsername().equalsIgnoreCase(message.getMahasiswa().getNip_pembimbing_1())){
                        mediator.selectIntent(message.setaClass(NilaiSidangEditorActivity.class).setExtraIntent(getIntent().getStringExtra(EXTRA_DEFAULT)).setSecondExtraIntent("Pembimbing 1"));
                    }else{
                        onMessage("Anda tidak memiliki hak untuk mengakses ini.");
                    }
                }
            }
        });
        mBinding.frgMhsPaCardviewNilaiPembimbing2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediator.getSessionPengguna().equalsIgnoreCase(ROLE_PRODI)){
                    mediator.selectIntent(message.setaClass(NilaiSidangEditorActivity.class).setExtraIntent(getIntent().getStringExtra(EXTRA_DEFAULT)).setSecondExtraIntent("Pembimbing 2"));
                }else{
                    if (mediator.getSessionUsername().equalsIgnoreCase(message.getMahasiswa().getNip_pembimbing_2())){
                        mediator.selectIntent(message.setaClass(NilaiSidangEditorActivity.class).setExtraIntent(getIntent().getStringExtra(EXTRA_DEFAULT)).setSecondExtraIntent("Pembimbing 2"));
                    }else{
                        onMessage("Anda tidak memiliki hak untuk mengakses ini.");
                    }
                }
            }
        });
        mBinding.frgMhsPaCardviewNilaiPenguji1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediator.getSessionPengguna().equalsIgnoreCase(ROLE_PRODI)){
                    mediator.selectIntent(message.setaClass(NilaiSidangEditorActivity.class).setExtraIntent(getIntent().getStringExtra(EXTRA_DEFAULT)).setSecondExtraIntent("Penguji 1"));
                }else{
                    if (mediator.getSessionUsername().equalsIgnoreCase(message.getMahasiswa().getNip_penguji_1())){
                        mediator.selectIntent(message.setaClass(NilaiSidangEditorActivity.class).setExtraIntent(getIntent().getStringExtra(EXTRA_DEFAULT)).setSecondExtraIntent("Penguji 1"));
                    }else{
                        onMessage("Anda tidak memiliki hak untuk mengakses ini.");
                    }
                }
            }
        });
        mBinding.frgMhsPaCardviewNilaiPenguji2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediator.getSessionPengguna().equalsIgnoreCase(ROLE_PRODI)){
                    mediator.selectIntent(message.setaClass(NilaiSidangEditorActivity.class).setExtraIntent(getIntent().getStringExtra(EXTRA_DEFAULT)).setSecondExtraIntent("Penguji 2"));
                }else{
                    if (mediator.getSessionUsername().equalsIgnoreCase(message.getMahasiswa().getNip_penguji_2())){
                        mediator.selectIntent(message.setaClass(NilaiSidangEditorActivity.class).setExtraIntent(getIntent().getStringExtra(EXTRA_DEFAULT)).setSecondExtraIntent("Penguji 2"));
                    }else{
                        onMessage("Anda tidak memiliki hak untuk mengakses ini.");
                    }
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mediator.message(message.setComponent("SidangPresenter").setEvent("getMahasiswaSidangByUsername").setText(getIntent().getStringExtra(EXTRA_DEFAULT)));
        mediator.message(message.setComponent("SidangPresenter").setEvent("checkFormRevisi").setText(getIntent().getStringExtra(EXTRA_DEFAULT)));
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
    public void onGetObjectMahasiswa(Mahasiswa mahasiswa) {
        mBinding.setModel(mahasiswa);
        message.setMahasiswa(mahasiswa);
        if (mediator.getSessionPengguna().equalsIgnoreCase(ROLE_PRODI)){
            if(mahasiswa.getNilai_total() != null){
                mediator.setButton(mBinding.btnDownloadBeritaAcara);
                mediator.message(message.setComponent("Button").setEvent("setVisibility").setVisibility(View.VISIBLE));
            }
        }
    }

    @Override
    public void onGetBody(ResponseBody body, String filename) {
        mediator.message(message.setComponent("FileHelper").setEvent("openFilePDF").setResponseBody(body).setText(filename));
    }

    @Override
    public void onGetObjectSidang(Sidang sidang) {

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
            case "File ditemukan.":
                mediator.setButton(mBinding.btnDownloadFormRevisi);
                mediator.message(message.setComponent("Button").setEvent("setVisibility").setVisibility(View.VISIBLE));
                mBinding.btnDownloadFormRevisi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mediator.message(message.setComponent("SidangPresenter").setEvent("downloadFormRevisi").setText(getIntent().getStringExtra(EXTRA_DEFAULT)));
                    }
                });
                break;
            case "File tidak ditemukan.":
                mediator.setButton(mBinding.btnDownloadFormRevisi);
                mediator.message(message.setComponent("Button").setEvent("setVisibility").setVisibility(View.GONE));
                break;
            default:
                mediator.message(message.setComponent("Toasty").setEvent("Warning").setText(messages));
                break;
        }
    }
}
