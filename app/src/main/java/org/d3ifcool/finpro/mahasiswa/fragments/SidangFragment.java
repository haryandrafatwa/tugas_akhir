package org.d3ifcool.finpro.mahasiswa.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.Message;
import org.d3ifcool.finpro.core.interfaces.SidangContract;
import org.d3ifcool.finpro.core.mediators.Mediator;
import org.d3ifcool.finpro.core.mediators.ConcreteMediator;
import org.d3ifcool.finpro.core.models.Mahasiswa;
import org.d3ifcool.finpro.core.models.Sidang;
import org.d3ifcool.finpro.databinding.FragmentMahasiswaSidangDetailBinding;
import org.d3ifcool.finpro.dosen.activities.editor.NilaiSidangEditorActivity;

import okhttp3.ResponseBody;

import static android.app.Activity.RESULT_OK;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.EXTRA_DEFAULT;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.PICK_EXCEL_REQUEST;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.PICK_PDF_REQUEST;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.ROLE_PRODI;


/**
 * A simple {@link Fragment} subclass.
 */
public class SidangFragment extends Fragment implements SidangContract.ViewModel {

    private Message message = new Message();
    private FragmentMahasiswaSidangDetailBinding mBinding;
    private Mediator mediator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_mahasiswa_sidang_detail,container,false);
        mBinding.setLifecycleOwner(this);
        mediator = new ConcreteMediator((AppCompatActivity) getActivity());
        mediator.setSidangPresenter(this);

        mediator.message(message.setComponent("ProgressDialog").setEvent("set"));
        mediator.message(message.setComponent("SessionManager").setEvent("set"));
        mediator.message(message.setComponent("FileHelper").setEvent("set"));

        mediator.setButton(mBinding.btnDownloadFormRevisi);
        mediator.message(message.setComponent("Button").setEvent("setOnClick").setText("uploadRevisi"));

        return mBinding.getRoot();

    }

    private void intentPickFile(String event){
        if (mediator.getPermissionFile()){
            if (event.equalsIgnoreCase("uploadFormRevisi")){
                startActivityForResult(Intent.createChooser(mediator.findFileIntent("PDF"), "Pilih file"), PICK_PDF_REQUEST);
            }else{
                startActivityForResult(Intent.createChooser(mediator.findFileIntent("PDF"), "Pilih file"), PICK_EXCEL_REQUEST);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_PDF_REQUEST && resultCode == RESULT_OK) {
            mediator.message(message.setComponent("SidangPresenter").setEvent("uploadFormRevisi").setUri(data.getData()));
        }else if (requestCode == PICK_EXCEL_REQUEST && resultCode == RESULT_OK) {
            mediator.message(message.setComponent("SidangPresenter").setEvent("uploadDraftJurnal").setUri(data.getData()));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mediator.message(message.setComponent("SidangPresenter").setEvent("getMahasiswaSidangByUsername").setText(mediator.getSessionUsername()));
    }

    @Override
    public void onGetObjectSidang(Sidang sidang) {

    }

    @Override
    public void onGetObjectMahasiswa(Mahasiswa mahasiswa) {
        mBinding.setModel(mahasiswa);
        message.setMahasiswa(mahasiswa);
        if (mahasiswa.getSidang_status() == null || mahasiswa.getSidang_status().equalsIgnoreCase("ditolak")){
            mBinding.disableView.setVisibility(View.VISIBLE);
            mBinding.btnAddSidangReguler.setVisibility(View.VISIBLE);
            mBinding.btnAddSidangReguler.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intentPickFile("uploadDraftJurnal");
                }
            });
        }else{
            if (mahasiswa.getSidang_status().equalsIgnoreCase("terjadwalkan")){
                mBinding.layoutContent.setVisibility(View.VISIBLE);
            }else if(mahasiswa.getSidang_status().equalsIgnoreCase("dijadwalkan")){
                mBinding.disableView.setVisibility(View.VISIBLE);
                mBinding.disMenunggu.setVisibility(View.VISIBLE);
            }else{
                mBinding.layoutContent.setVisibility(View.VISIBLE);
            }
        }

        mBinding.btnAddSidangReguler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mahasiswa.getPlot_pembimbing() == 0){
                    onMessage("Anda belum memiliki pembimbing");
                }
            }
        });
        mBinding.frgMhsPaCardviewNilaiPembimbing1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediator.selectIntent(message.setaClass(NilaiSidangEditorActivity.class).setExtraIntent(mediator.getSessionUsername()).setSecondExtraIntent("Pembimbing 1"));
            }
        });
        mBinding.frgMhsPaCardviewNilaiPembimbing2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediator.selectIntent(message.setaClass(NilaiSidangEditorActivity.class).setExtraIntent(mahasiswa.getMhs_nim()).setSecondExtraIntent("Pembimbing 2"));
            }
        });
        mBinding.frgMhsPaCardviewNilaiPenguji1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediator.selectIntent(message.setaClass(NilaiSidangEditorActivity.class).setExtraIntent(mahasiswa.getMhs_nim()).setSecondExtraIntent("Penguji 1"));
            }
        });
        mBinding.frgMhsPaCardviewNilaiPenguji2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediator.selectIntent(message.setaClass(NilaiSidangEditorActivity.class).setExtraIntent(mahasiswa.getMhs_nim()).setSecondExtraIntent("Penguji 2"));
            }
        });
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
            case "onUploadRevisi":
                intentPickFile("uploadFormRevisi");
                break;
            case "onSuccess":
                mediator.message(message.setComponent("Toasty").setEvent("Success").setText("Berhasil upload data!"));
                onResume();
                break;
            default:
                mediator.message(message.setComponent("Toasty").setEvent("Warning").setText(messages));
                break;
        }
    }
}
