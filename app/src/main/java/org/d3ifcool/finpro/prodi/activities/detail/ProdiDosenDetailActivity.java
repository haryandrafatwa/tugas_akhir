package org.d3ifcool.finpro.prodi.activities.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import de.hdodenhof.circleimageview.CircleImageView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
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
    private ProgressDialog progressDialog;
    private TextView tv_nama, tv_kode, tv_nip, tv_kontak, tv_email, tv_batas_bimbingan, tv_batas_reviewer;
    private CircleImageView circleImageView;
    private String nip;

    private ActivityProdiDosenDetailBinding binding;
    private ProdiConcrete mediator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prodi_dosen_detail);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_prodi_dosen_detail);

        setTitle(getString(R.string.title_dosen_detail));
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
            new AlertDialog
                    .Builder(this)
                    .setTitle(getString(R.string.dialog_hapus_title))
                    .setMessage(getString(R.string.dialog_hapus_text))

                    .setPositiveButton(R.string.iya, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Continue with delete operation
                            dosenPresenters.deleteDosen(nip);
                        }
                    })

                    .setNegativeButton(R.string.tidak, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
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
