package org.d3ifcool.finpro.prodi.activities.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.d3ifcool.finpro.core.helpers.Message;
import org.d3ifcool.finpro.core.interfaces.MahasiswaContract;
import org.d3ifcool.finpro.core.mediators.prodi.ConcreteMediator;
import org.d3ifcool.finpro.core.models.Mahasiswa;
import org.d3ifcool.finpro.core.models.Plotting;
import org.d3ifcool.finpro.databinding.ActivityProdiMahasiswaDetailBinding;
import org.d3ifcool.finpro.R;

import java.util.List;

import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.EXTRA_MAHASISWA;

public class ProdiMahasiswaDetailActivity extends AppCompatActivity implements MahasiswaContract.ViewModel {

    private Message message = new Message();
    private ActivityProdiMahasiswaDetailBinding binding;
    private ConcreteMediator mediator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_prodi_mahasiswa_detail);
        mediator = new ConcreteMediator(this);
        mediator.setMahasiswaPresenter(this);
        binding.setPresenter(mediator.getMahasiswaPresenter());

        setTitle(getString(R.string.title_mahasiswa_detail));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0f);

        message.setMahasiswa(getIntent().getParcelableExtra(EXTRA_MAHASISWA));
        binding.setModel(message.getMahasiswa());

        mediator.message(message.setComponent("ProgressDialog").setEvent("set"));
        mediator.message(message.setComponent("SessionManager").setEvent("set"));
        mediator.message(message.setComponent("ProdiMahasiswaAdapter").setEvent("set"));

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

        mediator.message(message.setComponent("MahasiswaPresenter").setEvent("getPembimbing"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_delete,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        mediator.message(message.setComponent("Toolbar").setVisibility(i).setEvent("mahasiswa"));
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onGetObjectMahasiswa(Mahasiswa mahasiswa) {

    }

    @Override
    public void onGetListMahasiswa(List<Mahasiswa> mahasiswaList) {

    }

    @Override
    public void onSuccessGetPlotting(Plotting plotting) {
        binding.setPlot(plotting);
    }

    @Override
    public void onMessage(String messages) {
        switch (messages){
            case "onSuccess":
                finish();
                break;
            case "ShowProgressDialog":
                mediator.message(message.setComponent("ProgressDialog").setEvent("show"));
                break;
            case "HideProgressDialog":
                mediator.message(message.setComponent("ProgressDialog").setEvent("dismiss"));
                break;
            default:
                mediator.message(message.setComponent("Toasty").setEvent(messages));
                break;
        }
    }
}
