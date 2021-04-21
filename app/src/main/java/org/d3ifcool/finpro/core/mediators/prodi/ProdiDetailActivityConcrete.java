package org.d3ifcool.finpro.core.mediators.prodi;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.squareup.picasso.Picasso;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.mediators.interfaces.prodi.ProdiDetailActivityMediator;
import org.d3ifcool.finpro.core.models.Mahasiswa;
import org.d3ifcool.finpro.core.models.Plotting;
import org.d3ifcool.finpro.prodi.activities.editor.update.KoorMahasiswaUbahActivity;
import org.d3ifcool.finpro.prodi.activities.editor.update.ProdiMahasiswaPlotPembimbingActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

import static org.d3ifcool.finpro.core.api.ApiUrl.FinproUrl.URL_FOTO_MAHASISWA;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.FILE_TYPE_PDF;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.PICK_PDF_REQUEST;

public class ProdiDetailActivityConcrete implements ProdiDetailActivityMediator {

    private static final String EXTRA_MAHASISWA = "extra_mahasiswa";
    private AppCompatActivity app;
    private ProgressDialog progressDialog;
    private TextView tv_nama, tv_nim, tv_angkatan, tv_email, tv_kontak, tv_status_sk, tv_change_pembimbing;
    private CircleImageView circleImageView;
    private LinearLayout empty_pembimbing, list_pembimbing;
    private Mahasiswa mahasiswa;
    private Plotting plotting;
    private Button btn_update_sk, btn_plot_pembimbing;
    private static final int FILE = 44 ;

    public ProdiDetailActivityConcrete(AppCompatActivity app) {
        this.app = app;
    }

    @Override
    public void Notify(int id) {
        switch (id){
            case R.id.act_koor_profil_foto:
                circleImageView = app.findViewById(R.id.act_koor_profil_foto);
                break;
            case R.id.act_koor_profil_nama:
                tv_nama = app.findViewById(R.id.act_koor_profil_nama);
                break;
            case R.id.act_koor_profil_nim:
                tv_nim = app.findViewById(R.id.act_koor_profil_nim);
                break;
            case R.id.act_koor_profil_angkatan_mhs:
                tv_angkatan = app.findViewById(R.id.act_koor_profil_angkatan_mhs);
                break;
            case R.id.act_koor_profil_email:
                tv_email = app.findViewById(R.id.act_koor_profil_email);
                break;
            case R.id.act_koor_profil_kontak:
                tv_kontak = app.findViewById(R.id.act_koor_profil_kontak);
                break;
            case R.id.act_koor_profil_status_sk:
                tv_status_sk = app.findViewById(R.id.act_koor_profil_status_sk);
                break;
            case R.id.tv_change_pembimbing:
                tv_change_pembimbing = app.findViewById(R.id.tv_change_pembimbing);
                if (mahasiswa.getPlot_id() > 0){
                    tv_change_pembimbing.setVisibility(View.VISIBLE);
                }else{
                    tv_change_pembimbing.setVisibility(View.GONE);
                }
                tv_change_pembimbing.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        btn_plot_pembimbing.performClick();
                    }
                });
                break;
            case R.id.btn_update_status_sk:
                btn_update_sk = app.findViewById(R.id.btn_update_status_sk);
                btn_update_sk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (ContextCompat.checkSelfPermission(app, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(app, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                        } else {
                            if (ContextCompat.checkSelfPermission(app, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                                ActivityCompat.requestPermissions(app, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                            } else {
                                Intent intent = new Intent();
                                intent.setType(FILE_TYPE_PDF);
                                String[] mimetypes = {FILE_TYPE_PDF};
                                intent.setAction(Intent.ACTION_GET_CONTENT);
                                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes);
                                app.startActivityForResult(Intent.createChooser(intent, "Pilih file"), PICK_PDF_REQUEST);
                            }
                        }
                    }
                });
                break;
            case R.id.btn_plot_pembimbing:
                btn_plot_pembimbing = app.findViewById(R.id.btn_plot_pembimbing);
                btn_plot_pembimbing.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(app, ProdiMahasiswaPlotPembimbingActivity.class);
                        intent.putExtra(KoorMahasiswaUbahActivity.EXTRA_MAHASISWA, mahasiswa);
                        app.startActivity(intent);
                        app.finish();
                    }
                });
                break;
        }
    }

    @Override
    public void message(String event) {
        switch (event){
            case "ProgressDialog":
                progressDialog = new ProgressDialog(app);
                progressDialog.setMessage(app.getString(R.string.text_progress_dialog));
                break;
            case "SetMahasiswa":
                empty_pembimbing = app.findViewById(R.id.linear_layout);
                list_pembimbing = app.findViewById(R.id.list_pembimbing);

                mahasiswa = app.getIntent().getParcelableExtra(EXTRA_MAHASISWA);
                tv_nama.setText(mahasiswa.getMhs_nama());
                tv_nim.setText(mahasiswa.getMhs_nim());

                if (mahasiswa.getMhs_email() == null){
                    tv_email.setText("-");
                }else {
                    tv_email.setText(mahasiswa.getMhs_email());
                }

                if (mahasiswa.getMhs_kontak() == null){
                    tv_kontak.setText("-");
                }else{
                    tv_kontak.setText(mahasiswa.getMhs_kontak());
                }

                try {
                    if (mahasiswa.getSk_status() == 1){
                        tv_status_sk.setText(R.string.status_sk_pasif);
                        tv_status_sk.setTextColor(Color.RED);
                    }else{
                        Locale locale = new Locale("in", "ID");
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", locale);
                        Date date = format.parse(mahasiswa.getSk_expired());
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(date);
                        String month_name = new SimpleDateFormat("MMMM", locale).format(calendar.getTime());
                        String tempJudul = calendar.get(Calendar.DATE)+" "+month_name+" "+calendar.get(Calendar.YEAR);
                        if(mahasiswa.getSk_status() == 2){
                            tv_status_sk.setText(app.getResources().getString(R.string.status_sk_aktif)+" "+tempJudul);
                            tv_status_sk.setTextColor(app.getResources().getColor(R.color.colorTextGreen));
                        }else if(mahasiswa.getSk_status() == 3){
                            tv_status_sk.setText(app.getResources().getString(R.string.status_sk_kadaluwarsa)+" "+tempJudul+")");
                            tv_status_sk.setTextColor(Color.RED);
                        }else{
                            tv_status_sk.setText(R.string.status_sk_perpanjang);
                            tv_status_sk.setTextColor(app.getResources().getColor(R.color.colorAccent));
                        }
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                tv_angkatan.setText(mahasiswa.getAngkatan());
                Picasso.get().load(URL_FOTO_MAHASISWA + mahasiswa.getMhs_foto()).into(circleImageView);
                break;
            case "SetPlotting":
                if (mahasiswa.getPlot_id() > 0){
                    list_pembimbing.setVisibility(View.VISIBLE);
                    empty_pembimbing.setVisibility(View.GONE);
                    TextView tv_nama_1, tv_nama_2, tv_nip_1, tv_nip_2;
                    tv_nama_1 = app.findViewById(R.id.tv_nama_pembimbing_1);
                    tv_nama_2 = app.findViewById(R.id.tv_nama_pembimbing_2);
                    tv_nip_1 = app.findViewById(R.id.tv_nip_pembimbing_1);
                    tv_nip_2 = app.findViewById(R.id.tv_nip_pembimbing_2);
                    tv_nama_1.setText(plotting.getNama_pembimbing_1());
                    tv_nama_2.setText(plotting.getNama_pembimbing_2());
                    tv_nip_1.setText(plotting.getNip_pembimbing_1());
                    tv_nip_2.setText(plotting.getNip_pembimbing_2());
                }else{
                    empty_pembimbing.setVisibility(View.VISIBLE);
                    list_pembimbing.setVisibility(View.GONE);
                }
                break;
        }
    }

    @Override
    public ProgressDialog getProgressDialog() {
        return this.progressDialog;
    }

    @Override
    public Mahasiswa getMahasiswa() {
        return this.mahasiswa;
    }

    @Override
    public void setPlotting(Plotting plotting) {
        this.plotting = plotting;
    }
}
