package org.d3ifcool.finpro.prodi.activities.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.d3ifcool.finpro.core.interfaces.InformasiContract;
import org.d3ifcool.finpro.core.mediators.prodi.ProdiConcrete;
import org.d3ifcool.finpro.core.models.Informasi;
import org.d3ifcool.finpro.core.presenters.InformasiPresenter;
import org.d3ifcool.finpro.databinding.ActivityKoorInformasiDetailBinding;
import org.d3ifcool.finpro.prodi.activities.editor.update.KoorInformasiUbahActivity;
import org.d3ifcool.finpro.R;

import java.util.List;

import static org.d3ifcool.finpro.core.api.ApiUrl.FinproUrl.URL_FOTO_DOSEN;

public class ProdiInformasiDetailActivity extends AppCompatActivity implements InformasiContract.ViewModel {

    public static final String EXTRA_INFORMASI = "extra_informasi";
    private Informasi extraInfo;

    private InformasiPresenter informasiPresenter;
    private ProdiConcrete mediator;

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
        mediator = new ProdiConcrete(this);
        mediator.message("ProgressDialog","set");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_delete, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int i = item.getItemId();

        if (i == android.R.id.home) {
            finish();

        } else if (i == R.id.toolbar_menu_ubah) {
            startActivity(informasiPresenter.toolbarIntent(extraInfo));
            finish();

        } else if (i == R.id.toolbar_menu_hapus) {
            mediator.message("AlertDialog","set");
            mediator.message("AlertDialog","hapus");
            mediator.getAlertDialog().setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    informasiPresenter.deleteInformasi(extraInfo.getId());
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
                mediator.getProgressDialog().show();
                break;
            case "HideProgressDialog":
                mediator.getProgressDialog().dismiss();
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
