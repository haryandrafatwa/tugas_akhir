package org.d3ifcool.finpro.prodi.activities.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.d3ifcool.finpro.core.interfaces.DosenContract;
import org.d3ifcool.finpro.core.mediators.prodi.ProdiConcrete;
import org.d3ifcool.finpro.core.models.Dosen;
import org.d3ifcool.finpro.core.presenters.DosenPresenter;
import org.d3ifcool.finpro.databinding.ActivityProdiDosenDetailBinding;
import org.d3ifcool.finpro.R;

import java.util.List;

import static org.d3ifcool.finpro.core.api.ApiUrl.FinproUrl.URL_FOTO_DOSEN;

public class ProdiDosenDetailActivity extends AppCompatActivity implements DosenContract.ViewModel {

    public static final String EXTRA_DOSEN = "extra_dosen";
    private Dosen extraDosen;
    private DosenPresenter dosenPresenter;
    private ProdiConcrete mediator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityProdiDosenDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_prodi_dosen_detail);
        extraDosen = getIntent().getParcelableExtra(EXTRA_DOSEN);
        binding.setModel(extraDosen);

        setTitle("Detail Dosen");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0f);

        dosenPresenter = new DosenPresenter(this);

        mediator = new ProdiConcrete(this);
        mediator.message("ProgressDialog","set");
        mediator.loadImage(URL_FOTO_DOSEN+extraDosen.getDsn_foto(),binding.actKoorProfilFotoDosen);
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
            startActivity(dosenPresenter.toolbarIntent(extraDosen));
            finish();
        } else if (i == R.id.toolbar_menu_hapus) {
            mediator.message("AlertDialog","set");
            mediator.message("AlertDialog","hapus");
            mediator.getAlertDialog().setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dosenPresenter.deleteDosen(extraDosen.getDsn_nip());
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
    public void onGetObjectDosen(Dosen dosen) {

    }

    @Override
    public void isEmptyObjectDosen() {

    }

    @Override
    public void onGetListDosen(List<Dosen> dosenList) {

    }

    @Override
    public void isEmptyListDosen() {

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickFloatButton() {

    }
}
