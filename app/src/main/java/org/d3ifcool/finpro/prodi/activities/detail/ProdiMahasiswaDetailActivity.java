package org.d3ifcool.finpro.prodi.activities.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import org.d3ifcool.finpro.core.helpers.Message;
import org.d3ifcool.finpro.core.interfaces.MahasiswaContract;
import org.d3ifcool.finpro.core.mediators.Mediator;
import org.d3ifcool.finpro.core.mediators.ConcreteMediator;
import org.d3ifcool.finpro.core.models.Mahasiswa;
import org.d3ifcool.finpro.core.models.Plotting;
import org.d3ifcool.finpro.databinding.ActivityProdiMahasiswaDetailBinding;
import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.prodi.activities.editor.ProdiMahasiswaPlotPembimbingActivity;

import java.util.List;


import okhttp3.ResponseBody;

import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.EXTRA_MAHASISWA;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.ROLE_LAK;

public class ProdiMahasiswaDetailActivity extends AppCompatActivity implements MahasiswaContract.ViewModel {

    private Message message = new Message();
    private ActivityProdiMahasiswaDetailBinding binding;
    private Mediator mediator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_prodi_mahasiswa_detail);
        mediator = new ConcreteMediator(this);
        mediator.setMahasiswaPresenter(this);
        binding.setPresenter(mediator.getMahasiswaPresenter());

        mediator.setTitleContextWithHomeAsUp("Detail Mahasiswa");

        message.setMahasiswa(getIntent().getParcelableExtra(EXTRA_MAHASISWA));
        binding.setModel(message.getMahasiswa());

        mediator.message(message.setComponent("ProgressDialog").setEvent("set"));
        mediator.message(message.setComponent("SessionManager").setEvent("set"));
        mediator.message(message.setComponent("ProdiMahasiswaAdapter").setEvent("set"));

        mediator.message(message.setComponent("MahasiswaPresenter").setEvent("getPembimbing"));
        if (mediator.getSessionPengguna().equalsIgnoreCase(ROLE_LAK)){
            mediator.setButton(binding.btnPlotPembimbing);
            mediator.message(message.setComponent("Button").setEvent("setVisibility").setVisibility(View.GONE));
            mediator.setLinearLayout(binding.layoutSk);
            mediator.message(message.setComponent("LinearLayout").setEvent("setVisibility").setVisibility(View.GONE));
            mediator.setTextView(binding.tvChangePembimbing);
            mediator.message(message.setComponent("TextView").setEvent("setVisibility").setVisibility(View.GONE));
            binding.tvChangePembimbing.setVisibility(View.GONE);
        }
        mediator.message(message.setComponent("MahasiswaPresenter").setEvent("getMahasiswaByNIM"));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(mediator.getSessionPengguna().equalsIgnoreCase("prodi")){
            getMenuInflater().inflate(R.menu.menu_edit_delete,menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mediator.message(message.setComponent("Toolbar").setVisibility(item.getItemId()).setEvent("mahasiswa"));
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onGetObjectMahasiswa(Mahasiswa mahasiswa) {
        binding.setModel(mahasiswa);
        message.setMahasiswa(mahasiswa);
        mediator.message(message.setComponent("MahasiswaPresenter").setEvent("getPembimbing"));
    }

    @Override
    public void onGetListMahasiswa(List<Mahasiswa> mahasiswaList) {

    }

    @Override
    public void onSuccessGetPlotting(Plotting plotting) {
        binding.setPlot(plotting);
        message.setPlotting(plotting);

        mediator.setTextView(binding.actKoorProfilStatusSk);
        if (message.getMahasiswa().getSk_status() == 1 || message.getMahasiswa().getSk_status() == 3){
            mediator.message(message.setComponent("TextView").setEvent("setTextColor").setColor("#ffFF0000"));
        }else{
            if(message.getMahasiswa().getSk_status() == 2){
                mediator.message(message.setComponent("TextView").setEvent("setTextColor").setColor("#ff4CAF50"));
            }else{
                mediator.message(message.setComponent("TextView").setEvent("setTextColor").setColor("#ffFF9800"));
            }
        }
    }

    @Override
    public void onGetBody(ResponseBody body, String filename) {

    }

    @Override
    public void onMessage(String messages) {
        switch (messages){
            case "onSuccess":
                mediator.message(message.setComponent("Toasty").setEvent("Success").setText("Berhasil menyimpan data"));
                finish();
                break;
            case "ShowProgressDialog":
                mediator.message(message.setComponent("ProgressDialog").setEvent("show"));
                break;
            case "HideProgressDialog":
                mediator.message(message.setComponent("ProgressDialog").setEvent("dismiss"));
                break;
            case "btnSkUpdate":
                mediator.message(message.setComponent("MahasiswaPresenter").setEvent("updateSKTA"));
                break;
            case "AddPembimbing":
                mediator.message(message.setComponent("MahasiswaPresenter").setEvent("toolbarIntent").setaClass(ProdiMahasiswaPlotPembimbingActivity.class));
                break;
            case "onDelete":
                mediator.message(message.setComponent("MahasiswaPresenter").setEvent("deletePembimbing"));
                break;
            default:
                mediator.message(message.setComponent("Toasty").setEvent("Warning").setText(messages));
                break;
        }
    }

}
