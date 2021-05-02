package org.d3ifcool.finpro.prodi.activities.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.d3ifcool.finpro.core.interfaces.works.DosenWorkView;
import org.d3ifcool.finpro.core.mediators.prodi.ProdiConcrete;
import org.d3ifcool.finpro.core.models.Dosen;
import org.d3ifcool.finpro.core.presenters.DosenPresenters;
import org.d3ifcool.finpro.databinding.ActivityProdiDosenDetailBinding;
import org.d3ifcool.finpro.prodi.activities.editor.update.KoorDosenUbahActivity;
import org.d3ifcool.finpro.R;

import static org.d3ifcool.finpro.core.api.ApiUrl.FinproUrl.URL_FOTO_DOSEN;

public class ProdiDosenDetailActivity extends AppCompatActivity implements DosenWorkView {

    public static final String EXTRA_DOSEN = "extra_dosen";
    private Dosen extraDosen;
    private DosenPresenters dosenPresenters;

    private ActivityProdiDosenDetailBinding binding;
    private ProdiConcrete mediator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_prodi_dosen_detail);

        setTitle("Detail Dosen");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0f);

        dosenPresenters = new DosenPresenters(this);
        dosenPresenters.initContext(this);

        mediator = new ProdiConcrete(this);
        mediator.message("ProgressDialog","set");

        extraDosen = getIntent().getParcelableExtra(EXTRA_DOSEN);
        binding.setModel(extraDosen);
        Picasso.get().load(URL_FOTO_DOSEN + extraDosen.getDsn_foto()).into(binding.actKoorProfilFotoDosen);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_delete,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int i = item.getItemId();
        if (i == android.R.id.home) {
            finish();
        } else if (i == R.id.toolbar_menu_ubah) {
            Intent intentUbah = new Intent(ProdiDosenDetailActivity.this, KoorDosenUbahActivity.class);
            intentUbah.putExtra(KoorDosenUbahActivity.EXTRA_DOSEN, extraDosen);
            startActivity(intentUbah);
            finish();
        } else if (i == R.id.toolbar_menu_hapus) {
            mediator.message("AlertDialog","set");
            mediator.message("AlertDialog","hapus");
            mediator.getAlertDialog().setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dosenPresenters.deleteDosen(extraDosen.getDsn_nip());
                }
            });
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProgress() {
        mediator.getProgressDialog().show();
    }

    @Override
    public void hideProgress() {
        mediator.getProgressDialog().dismiss();
    }

    @Override
    public void onSucces() {
        finish();
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
