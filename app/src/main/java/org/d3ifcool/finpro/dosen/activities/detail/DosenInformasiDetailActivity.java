package org.d3ifcool.finpro.dosen.activities.detail;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.SessionManager;
import org.d3ifcool.finpro.core.interfaces.InformasiContract;
import org.d3ifcool.finpro.core.interfaces.works.InformasiWorkView;
import org.d3ifcool.finpro.core.models.Informasi;
import org.d3ifcool.finpro.core.models.Plotting;
import org.d3ifcool.finpro.dosen.activities.editor.update.DosenInformasiUbahActivity;
import org.d3ifcool.finpro.core.presenters.InformasiPresenter;

import java.util.List;

import static org.d3ifcool.finpro.core.api.ApiUrl.FinproUrl.URL_FOTO_DOSEN;

public class DosenInformasiDetailActivity extends AppCompatActivity implements InformasiContract.ViewModel {

    public static final String EXTRA_INFORMASI = "extra_informasi";
    private Informasi extraInfo;
    private InformasiPresenter informasiPresenter;
    private ProgressDialog progressDialog;
    private SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_informasi_detail);

        setTitle(getString(R.string.title_informasi_detail));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sessionManager = new SessionManager(this);

        TextView textView_judul = findViewById(R.id.act_dsn_info_detail_textview_judul);
        TextView textView_isi = findViewById(R.id.act_dsn_info_detail_textview_isi);
        TextView textView_tanggal = findViewById(R.id.act_dsn_info_detail_textview_tanggal);
        TextView textView_dosen = findViewById(R.id.act_dsn_info_detail_textview_dosen);
        CircleImageView imageView_foto = findViewById(R.id.ctn_all_info_photo);

        extraInfo = getIntent().getParcelableExtra(EXTRA_INFORMASI);
        String judul = extraInfo.getInfo_judul();
        String isi = extraInfo.getInfo_deskripsi();
        String tanggal = extraInfo.getTanggal();
        String dosen = extraInfo.getPenerbit();
        String foto = sessionManager.getSessionDosenFoto();

        textView_judul.setText(judul);
        textView_isi.setText(isi);
        textView_tanggal.setText(tanggal);
        textView_dosen.setText(dosen);
        Picasso.get().load(URL_FOTO_DOSEN+foto).into(imageView_foto);

        informasiPresenter = new InformasiPresenter(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.text_progress_dialog));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        String publisher = extraInfo.getPenerbit();
        if (publisher.equalsIgnoreCase(sessionManager.getSessionDosenNama())) {
            getMenuInflater().inflate(R.menu.menu_edit_delete, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int i = item.getItemId();

        if (i == android.R.id.home) {
            finish();

        } else if (i == R.id.toolbar_menu_ubah) {
            Intent intentUbah = new Intent(DosenInformasiDetailActivity.this, DosenInformasiUbahActivity.class);
            intentUbah.putExtra(DosenInformasiUbahActivity.EXTRA_INFORMASI, extraInfo);
            startActivity(intentUbah);
            finish();

        } else if (i == R.id.toolbar_menu_hapus) {

            new AlertDialog
                    .Builder(this)
                    .setTitle(getString(R.string.dialog_hapus_title))
                    .setMessage(getString(R.string.dialog_hapus_text))

                    .setPositiveButton(R.string.iya, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            informasiPresenter.deleteInformasi(extraInfo.getId());
                        }
                    })

                    .setNegativeButton(R.string.tidak, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();

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
                progressDialog.show();
                break;
            case "HideProgressDialog":
                progressDialog.dismiss();
                break;
            case "onSuccess":
                finish();
            default:
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
