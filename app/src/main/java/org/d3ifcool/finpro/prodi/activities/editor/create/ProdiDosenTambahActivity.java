package org.d3ifcool.finpro.prodi.activities.editor.create;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.d3ifcool.finpro.core.interfaces.works.DosenWorkView;
import org.d3ifcool.finpro.core.mediators.interfaces.prodi.ProdiActivityMediator;
import org.d3ifcool.finpro.core.mediators.prodi.ProdiActivityConcrete;
import org.d3ifcool.finpro.core.presenters.DosenPresenters;
import org.d3ifcool.finpro.R;

public class ProdiDosenTambahActivity extends AppCompatActivity implements DosenWorkView {

    private DosenPresenters dosenPresenters;
    private String nip, nama, kode;
    private ProdiActivityMediator mediator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koor_dosen_tambah);

        setTitle(getString(R.string.title_tambah_dosen));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mediator = new ProdiActivityConcrete(this);

        mediator.message("SessionManager");
        mediator.message("ProgressDialog");

        mediator.Notify(R.id.act_koor__dosen_edittext_nipdosen);
        mediator.Notify(R.id.act_koor_edittext_namadosen);
        mediator.Notify(R.id.act_koor_edittext_kodedosen);
        mediator.Notify(R.id.act_koor_info_button_simpan);

        dosenPresenters = new DosenPresenters(this);
        dosenPresenters.initContext(this);

        mediator.getButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 nip = mediator.getETNIP().getText().toString();
                 nama = mediator.getETNama().getText().toString();
                 kode = mediator.getETKode().getText().toString();

                if (nip.isEmpty()){
                    mediator.getETNIP().setError("nip tidak boleh kosong");
                }else if (nama.isEmpty()){
                    mediator.getETNama().setError("nama tidak boleh kosong");
                }else if (kode.isEmpty()){
                    mediator.getETKode().setError("kode dosen tidak boleh kosong");
                }else{
                    dosenPresenters.createDosen(nip,nama,kode);
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
    public void onFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
