package org.d3ifcool.finpro.prodi.activities.editor.create;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.d3ifcool.finpro.core.interfaces.works.MahasiswaWorkView;
import org.d3ifcool.finpro.core.mediators.interfaces.prodi.ProdiActivityMediator;
import org.d3ifcool.finpro.core.mediators.prodi.ProdiActivityConcrete;
import org.d3ifcool.finpro.core.models.Plotting;
import org.d3ifcool.finpro.core.presenters.MahasiswaPresenter;
import org.d3ifcool.finpro.R;

public class ProdiMahasiswaTambahActivity extends AppCompatActivity implements MahasiswaWorkView {

    private MahasiswaPresenter mahasiswaPresenter;
    private ProdiActivityMediator mediator;
    private String nama,nim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koor_mahasiswa_tambah);

        setTitle(getString(R.string.title_mahasiswa_tambah));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mahasiswaPresenter = new MahasiswaPresenter(this);
        mahasiswaPresenter.initContext(this);

        mediator = new ProdiActivityConcrete(this);
        mediator.message("SessionManager");
        mediator.message("ProgressDialog");

        mediator.Notify(R.id.act_koor__dosen_edittext_nipdosen);
        mediator.Notify(R.id.act_koor_edittext_namadosen);
        mediator.Notify(R.id.act_koor_info_button_simpan);

        mediator.getButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nim = mediator.getETNIP().getText().toString();
                nama = mediator.getETNama().getText().toString();

                if (nim.isEmpty()) {
                    mediator.getETNIP().setError(getString(R.string.text_tidak_boleh_kosong));
                } else if (nama.isEmpty()) {
                    mediator.getETNama().setError(getString(R.string.text_tidak_boleh_kosong));
                }else {
                    mahasiswaPresenter.createMahasiswa(nim, nama);
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int i = item.getItemId();
        if (i == android.R.id.home) {
            finish();
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
    public void onSuccesGetPlotting(Plotting plotting) {

    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
