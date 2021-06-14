package org.d3ifcool.finpro.activities.detail;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.Constant;
import org.d3ifcool.finpro.core.interfaces.InformasiContract;
import org.d3ifcool.finpro.core.mediators.prodi.ConcreteMediator;
import org.d3ifcool.finpro.core.models.Informasi;
import org.d3ifcool.finpro.core.presenters.InformasiPresenter;
import org.d3ifcool.finpro.databinding.ActivityKoorInformasiDetailBinding;

import java.util.List;

public class InformasiDetailActivity extends AppCompatActivity implements InformasiContract.ViewModel {

    public static final String EXTRA_INFORMASI = "extra_informasi";
    private Informasi extraInfo;

    private InformasiPresenter informasiPresenter;
    private ConcreteMediator mediator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityKoorInformasiDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_koor_informasi_detail);
        extraInfo = getIntent().getParcelableExtra(EXTRA_INFORMASI);
        binding.setModel(extraInfo);

        setTitle("Detail Informasi");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0f);

        informasiPresenter = new InformasiPresenter(this);
        mediator = new ConcreteMediator(this);
        mediator.message("ProgressDialog","set");
        mediator.message("SessionManager","set");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_delete, menu);
        if (mediator.getSessionManager().getSessionPengguna().equalsIgnoreCase(Constant.ObjectConstanta.ROLE_DOSEN)){
            if (!mediator.getSessionManager().getSessionDosenNama().equalsIgnoreCase(extraInfo.getPenerbit())){
                menu.clear();
            }
        }else{
            if (!mediator.getSessionManager().getSessionKoorNama().equalsIgnoreCase(extraInfo.getPenerbit())){
                menu.clear();
            }
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int i = item.getItemId();

        if (i == android.R.id.home) {
            finish();

        } else if (i == R.id.toolbar_menu_ubah) {
            startActivity(informasiPresenter.toolbarIntent(extraInfo));
        } else if (i == R.id.toolbar_menu_hapus) {
            mediator.message("AlertDialog","set");
            mediator.message("AlertDialog","hapus");
            mediator.getAlertDialog().setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    informasiPresenter.deleteInformasi(mediator.getSessionManager().getSessionToken(),extraInfo.getId());
                }
            }).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onGetObjectInformasi(Informasi informasi) {

    }

    @Override
    public void onGetListInformasi(List<Informasi> informasiList) {

    }

    @Override
    public void onMessage(String message) {
        switch (message){
            case "ShowProgressDialog":
                mediator.message("ProgressDialog","show");
                break;
            case "HideProgressDialog":
                mediator.message("ProgressDialog","dismiss");
                break;
            case "onSuccess":
                finish();
                break;
            default:
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
