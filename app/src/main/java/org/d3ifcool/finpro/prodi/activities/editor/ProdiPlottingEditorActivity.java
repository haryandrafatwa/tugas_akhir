package org.d3ifcool.finpro.prodi.activities.editor;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.Message;
import org.d3ifcool.finpro.core.interfaces.DosenContract;
import org.d3ifcool.finpro.core.interfaces.PlottingContract;
import org.d3ifcool.finpro.core.mediators.Mediator;
import org.d3ifcool.finpro.core.mediators.ConcreteMediator;
import org.d3ifcool.finpro.core.models.Dosen;
import org.d3ifcool.finpro.core.models.Mahasiswa;
import org.d3ifcool.finpro.core.models.Plotting;
import org.d3ifcool.finpro.core.presenters.DosenPresenter;
import org.d3ifcool.finpro.databinding.ActivityKoorPlottingTambahBinding;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;

public class ProdiPlottingEditorActivity extends AppCompatActivity implements PlottingContract.ViewModel, DosenContract.ViewModel {

    private Message message = new Message();
    private DosenPresenter dosenPresenter;
    private ActivityKoorPlottingTambahBinding mBinding;

    private Mediator mediator;
    private String dosen_1,dosen_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_koor_plotting_tambah);
        mediator = new ConcreteMediator(this);
        mediator.setPlottingPresenter(this);
        mediator.setDosenPresenter(this);
        mBinding.setPresenter(mediator.getPlottingPresenter());

        mediator.setTitleContextWithHomeAsUp("Tambah Plotting");

        if (getIntent().getParcelableExtra("PlottingModel")!=null){
            mediator.setTitleContextWithHomeAsUp("Ubah Plotting");
            message.setPlotting(getIntent().getParcelableExtra("PlottingModel"));
            mediator.setTextView(mBinding.actKoorInfoButtonSimpan);
            mediator.message(message.setComponent("TextView").setEvent("setText").setText("Ubah Plotting"));
            message.setEnabled(true);
        }

        mediator.message(message.setComponent("SessionManager").setEvent("set"));
        mediator.message(message.setComponent("ProgressDialog").setEvent("set"));

        mediator.message(message.setComponent("DosenPresenter").setEvent("getAllData"));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int i = item.getItemId();
        mediator.message(message.setComponent("Toolbar").setVisibility(i));
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onGetObjectDosen(Dosen dosen) {

    }

    @Override
    public void onGetListDosen(List<Dosen> dosen) {
        ArrayList<String> usernameList = new ArrayList<>();
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Pilih dosen disini");
        usernameList.add("-");
        for (int i = 0; i < dosen.size(); i++) {
            usernameList.add(dosen.get(i).getDsn_nip());
            arrayList.add(dosen.get(i).getDsn_nama());
        }
        mediator.setSpinner(mBinding.actKoorDosenSpinnerDosen1);
        mediator.message(message.setComponent("Spinner").setEvent("setAdapter").setItem(arrayList));
        dosen_1 = mediator.getUsernameDosen();
        mediator.setSpinner(mBinding.actKoorDosenSpinnerDosen2);
        mediator.message(message.setComponent("Spinner").setEvent("setAdapter").setItem(arrayList));
        dosen_2 = mediator.getUsernameDosen();
        if (message.isEnabled()){
            if (usernameList.contains(message.getPlotting().getNip_dosen_2())){
                mediator.message(message.setComponent("Spinner").setEvent("setSelection2").setItem(usernameList));
            }
            if (usernameList.contains(message.getPlotting().getNip_dosen_1())){
                mediator.setSpinner(mBinding.actKoorDosenSpinnerDosen1);
                mediator.message(message.setComponent("Spinner").setEvent("setSelection1").setItem(usernameList));
            }
        }
        message.setItem(usernameList);
        mBinding.actKoorDosenSpinnerDosen1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dosen_1 = message.getItem().get(parent.getSelectedItemPosition()).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mBinding.actKoorDosenSpinnerDosen2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dosen_2 = message.getItem().get(parent.getSelectedItemPosition()).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onGetObjectMahasiswa(Mahasiswa mahasiswa) {

    }

    @Override
    public void onGetListMahasiswa(List<Mahasiswa> mahasiswasList) {

    }

    @Override
    public void onGetListPlotting(List<Plotting> plottingList) {

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
            case"onSuccess":
                finish();
                break;
            case "AddButton":
                if (dosen_1.equalsIgnoreCase("-")) {
                    mediator.setTextView((TextView)mBinding.actKoorDosenSpinnerDosen1.getSelectedView());
                    mediator.message(message.setComponent("TextView").setEvent("setText").setText("Pilih dosen terlebih dahulu"));
                    mediator.message(message.setComponent("TextView").setEvent("setTextColor").setColor("#ffFF0000"));
                } else if (dosen_2.equals(dosen_1)) {
                    mediator.message(message.setComponent("TextView").setEvent("setText").setText("Dosen tidak boleh sama"));
                    mediator.message(message.setComponent("TextView").setEvent("setTextColor"));
                    mediator.setTextView((TextView)mBinding.actKoorDosenSpinnerDosen2.getSelectedView());
                    mediator.message(message.setComponent("TextView").setEvent("setText").setText("Dosen tidak boleh sama"));
                    mediator.message(message.setComponent("TextView").setEvent("setTextColor"));
                } else if (dosen_2.equalsIgnoreCase("-")) {
                    mediator.message(message.setComponent("TextView").setEvent("setText").setText("Pilih dosen terlebih dahulu"));
                    mediator.message(message.setComponent("TextView").setEvent("setTextColor"));
                } else {
                    if (message.isEnabled()){
                        mediator.message(message.setComponent("PlottingPresenter").setEvent("updatePlotting").setText(dosen_1).setUrl(dosen_2));
                    }else{
                        mediator.message(message.setComponent("PlottingPresenter").setEvent("createPlotting").setText(dosen_1).setUrl(dosen_2));
                    }
                }
                break;
            default:
                mediator.message(message.setComponent("Toasty").setEvent("Warning").setText(messages));
                break;
        }
    }

    @Override
    public void onGetBody(ResponseBody body, String filename) {

    }
}
