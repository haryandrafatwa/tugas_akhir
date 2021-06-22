package org.d3ifcool.finpro.prodi.activities.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.d3ifcool.finpro.core.helpers.Message;
import org.d3ifcool.finpro.core.interfaces.DosenContract;
import org.d3ifcool.finpro.core.mediators.interfaces.prodi.Mediator;
import org.d3ifcool.finpro.core.mediators.prodi.ConcreteMediator;
import org.d3ifcool.finpro.core.models.Dosen;
import org.d3ifcool.finpro.databinding.ActivityProdiDosenDetailBinding;
import org.d3ifcool.finpro.R;

import java.util.List;

import static org.d3ifcool.finpro.core.api.ApiUrl.FinproUrl.URL_FOTO_DOSEN;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.EXTRA_DOSEN;

public class ProdiDosenDetailActivity extends AppCompatActivity implements DosenContract.ViewModel {

    private Message message = new Message();
    private Mediator mediator;
    private ActivityProdiDosenDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_prodi_dosen_detail);
        mediator = new ConcreteMediator(this);
        mediator.setDosenPresenter(this);

        message.setDosen(getIntent().getParcelableExtra(EXTRA_DOSEN));
        binding.setModel(message.getDosen());

        mediator.setTitleContextWithHomeAsUp("Detail Dosen");

        mediator.message(message.setComponent("ProgressDialog").setEvent("set"));
        mediator.message(message.setComponent("SessionManager").setEvent("set"));
        mediator.setCircleImageView(binding.actKoorProfilFotoDosen);
        mediator.message(message.setComponent("CircleImageView").setEvent("setImage").setUrl(URL_FOTO_DOSEN+message.getDosen().getDsn_foto()));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mediator.message(message.setComponent("DosenPresenter").setEvent("getDosenByNIP"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_delete,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        mediator.message(message.setComponent("Toolbar").setVisibility(i).setEvent("dosen"));
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onGetObjectDosen(Dosen dosen) {
        binding.setModel(dosen);
        message.setDosen(dosen);
    }

    @Override
    public void onGetListDosen(List<Dosen> dosenList) {

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
                finish();
                break;
            default:
                mediator.message(message.setComponent("Toasty").setEvent("Warning").setText(messages));
                break;
        }
    }
}
