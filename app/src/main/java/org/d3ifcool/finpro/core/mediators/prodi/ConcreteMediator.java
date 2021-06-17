package org.d3ifcool.finpro.core.mediators.prodi;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.activities.AuthActivity;
import org.d3ifcool.finpro.adapters.InformasiViewAdapter;
import org.d3ifcool.finpro.core.adapters.KegiatanAdapter;
import org.d3ifcool.finpro.core.helpers.FileHelper;
import org.d3ifcool.finpro.core.helpers.Message;
import org.d3ifcool.finpro.core.helpers.MethodHelper;
import org.d3ifcool.finpro.core.helpers.SessionManager;
import org.d3ifcool.finpro.core.interfaces.AuthContract;
import org.d3ifcool.finpro.core.interfaces.BimbinganContract;
import org.d3ifcool.finpro.core.interfaces.DosenContract;
import org.d3ifcool.finpro.core.interfaces.JadwalContract;
import org.d3ifcool.finpro.core.interfaces.MahasiswaContract;
import org.d3ifcool.finpro.core.interfaces.PlottingContract;
import org.d3ifcool.finpro.core.interfaces.ProdiContract;
import org.d3ifcool.finpro.core.mediators.interfaces.prodi.Mediator;
import org.d3ifcool.finpro.core.models.JadwalKegiatan;
import org.d3ifcool.finpro.core.models.manager.AuthManager;
import org.d3ifcool.finpro.core.presenters.AuthPresenter;
import org.d3ifcool.finpro.core.presenters.BimbinganPresenter;
import org.d3ifcool.finpro.core.presenters.DosenPresenter;
import org.d3ifcool.finpro.core.presenters.JadwalPresenter;
import org.d3ifcool.finpro.core.presenters.MahasiswaPresenter;
import org.d3ifcool.finpro.core.presenters.PlottingPresenter;
import org.d3ifcool.finpro.core.presenters.ProdiPresenter;
import org.d3ifcool.finpro.dosen.activities.DosenProfilActivity;
import org.d3ifcool.finpro.dosen.adapters.recyclerview.DosenBimbinganViewAdapter;
import org.d3ifcool.finpro.dosen.adapters.recyclerview.DosenMahasiswaBimbinganViewAdapter;
import org.d3ifcool.finpro.dosen.fragments.DosenMahasiswaBimbinganFragment;
import org.d3ifcool.finpro.fragments.InformasiFragment;
import org.d3ifcool.finpro.lak.LAKJadwalKegiatanFragment;
import org.d3ifcool.finpro.mahasiswa.activities.MahasiswaJadwalKegiatanActivity;
import org.d3ifcool.finpro.mahasiswa.activities.MahasiswaPemberitahuanActivity;
import org.d3ifcool.finpro.mahasiswa.activities.MahasiswaProfilActivity;
import org.d3ifcool.finpro.mahasiswa.adapters.recyclerview.MahasiswaBimbinganViewAdapter;
import org.d3ifcool.finpro.mahasiswa.fragments.MahasiswaTugasAkhirFragment;
import org.d3ifcool.finpro.mahasiswa.fragments.SidangFragment;
import org.d3ifcool.finpro.prodi.activities.KoorProfilActivity;
import org.d3ifcool.finpro.prodi.activities.editor.ProdiMahasiswaEditorActivity;
import org.d3ifcool.finpro.prodi.adapters.ProdiDosenViewAdapter;
import org.d3ifcool.finpro.prodi.adapters.ProdiMahasiswaViewAdapter;
import org.d3ifcool.finpro.prodi.adapters.ProdiPlotPembimbingViewAdapter;
import org.d3ifcool.finpro.prodi.adapters.ProdiPlottingViewAdapter;
import org.d3ifcool.finpro.prodi.adapters.ProdiSKTAViewAdapter;
import org.d3ifcool.finpro.prodi.fragments.ProdiDosenFragment;
import org.d3ifcool.finpro.prodi.fragments.ProdiMahasiswaFragment;
import org.d3ifcool.finpro.prodi.fragments.ProdiPlottingFragment;
import org.d3ifcool.finpro.prodi.fragments.ProdiSKTAFragment;
import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.EXTRA_DEFAULT;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.FILE_TYPE_XLS;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.FILE_TYPE_XLSX;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.ROLE_LAK;

public class ConcreteMediator implements Mediator {

    private AppCompatActivity activity;
    private AuthManager authManager;
    private MethodHelper methodHelper;
    private FileHelper fileHelper;
    private String dosenUsername;

    private PlottingPresenter plottingPresenter;
    private MahasiswaPresenter mahasiswaPresenter;
    private DosenPresenter dosenPresenter;
    private AuthPresenter authPresenter;
    private ProdiPresenter prodiPresenter;
    private BimbinganPresenter bimbinganPresenter;
    private JadwalPresenter jadwalPresenter;

    private RelativeLayout relativeLayout;
    private LinearLayout linearLayout;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private TextView textView;
    private Spinner spinner;
    private CircleImageView circleImageView;
    private CardView cardView;
    private FloatingActionButton floatingActionButton;
    private CompactCalendarView compactCalendarView;

    private ProdiDosenViewAdapter dosenViewAdapter;
    private ProdiMahasiswaViewAdapter mahasiswaViewAdapter;
    private ProdiPlottingViewAdapter plottingViewAdapter;
    private InformasiViewAdapter informasiViewAdapter;
    private ProdiSKTAViewAdapter prodiSKTAViewAdapter;
    private ProdiPlotPembimbingViewAdapter prodiPlotPembimbingViewAdapter;
    private MahasiswaBimbinganViewAdapter mahasiswaBimbinganViewAdapter;
    private DosenMahasiswaBimbinganViewAdapter dosenMahasiswaBimbinganViewAdapter;
    private DosenBimbinganViewAdapter dosenBimbinganViewAdapter;

    private SessionManager sessionManager;
    private AlertDialog.Builder alertDialog;
    private ProgressDialog progressDialog;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private BottomNavigationView bottomNavigationView;

    public ConcreteMediator(AppCompatActivity activity) {
        this.activity = activity;
        authManager  = new AuthManager();
        methodHelper = new MethodHelper(activity);
    }

    private static final String SET = "set";

    @Override
    public void Notify(int id) {
        switch (id){
            case R.id.bottom_menu_dsn_informasi:
                methodHelper.applyFragment(new InformasiFragment(), "DosenInformasiFragment");
                this.activity.setTitle(R.string.title_informasi);
                break;
            case R.id.bottom_menu_dsn_bimbingan:
                methodHelper.applyFragment(new DosenMahasiswaBimbinganFragment(),"DosenMahasiswaBimbinganFragment");
                this.activity.setTitle(R.string.title_bimbingan);
                break;
            case R.id.bottom_menu_dsn_sidang:
                methodHelper.applyFragment(new DosenMahasiswaBimbinganFragment(),"DosenJudulFragment");
                this.activity.setTitle(R.string.title_sidang);
                break;

            case R.id.toolbar_menu_keluar:
                message("AlertDialog",SET);
                message("AlertDialog","logout");
                break;

            case R.id.toolbar_menu_hapus:
                message("AlertDialog",SET);
                message("AlertDialog","hapus");
                break;

            case R.id.nav_menu_informasi:
                Notify(R.id.bottom_menu_dsn_informasi);
                break;
            case R.id.nav_menu_mahasiswa:
                this.activity.setTitle(R.string.title_mahasiswa);
                methodHelper.applyFragment(new ProdiMahasiswaFragment(),"ProdiMahasiswaFragment");
                break;
            case R.id.nav_menu_dosen:
                this.activity.setTitle(R.string.title_dosen);
                methodHelper.applyFragment(new ProdiDosenFragment(),"ProdiDosenFragment");
                break;
            case R.id.nav_menu_plotting:
                this.activity.setTitle(R.string.menu_plotting_pembimbing);
                methodHelper.applyFragment(new ProdiPlottingFragment(),"ProdiPlottingFragment");
                break;
            case R.id.nav_menu_skta:
                this.activity.setTitle(R.string.menu_sk_ta);
                methodHelper.applyFragment(new ProdiSKTAFragment(),"ProdiSKTAFragment");
                break;
            default:
                break;
        }
    }

    @Override
    public void message(String component, String event) {
        switch (component){
            case "ProdiPlottingAdapter":
                switch (event){
                    case SET:
                        plottingViewAdapter = new ProdiPlottingViewAdapter(activity);
                        plottingViewAdapter.setToken(getSessionToken());
                        plottingViewAdapter.setPlottingPresenter(this.plottingPresenter);
                        break;
                }
                break;
            case "InformasiViewAdapter":
                switch (event){
                    case SET:
                        informasiViewAdapter = new InformasiViewAdapter(activity);
                        break;
                }
                break;
            case "ProdiMahasiswaAdapter":
                switch (event){
                    case SET:
                        mahasiswaViewAdapter = new ProdiMahasiswaViewAdapter(activity);
                        break;
                }
                break;
            case "ProdiDosenAdapter":
                switch (event){
                    case SET:
                        dosenViewAdapter = new ProdiDosenViewAdapter(activity);
                        break;
                }
                break;
            case "FileHelper":
                switch (event){
                    case SET:
                        fileHelper = new FileHelper(activity.getApplicationContext(),activity);
                        break;
                }
                break;
            case "RefreshLayout":
                switch (event){
                    case "false":
                        this.refreshLayout.setRefreshing(false);
                        break;
                    case "true":
                        this.refreshLayout.setRefreshing(true);
                        break;
                }
            case "ProgressDialog":
                switch (event){
                    case SET:
                        progressDialog = new ProgressDialog(activity);
                        progressDialog.setMessage(activity.getString(R.string.text_progress_dialog));
                        break;
                    case "show":
                        progressDialog.show();
                        break;
                    case "dismiss":
                        progressDialog.dismiss();
                        break;
                }
                break;
            case "SessionManager":
                switch (event){
                    case SET:
                        sessionManager = new SessionManager(this.activity);
                        break;
                }
                break;
            case "AlertDialog":
                switch (event){
                    case SET:
                        alertDialog = new AlertDialog.Builder(activity).setIcon(android.R.drawable.ic_dialog_alert).setNegativeButton("Batal",null);
                        break;

                    case "logout":
                        alertDialog
                                .setTitle(R.string.dialog_keluar_title)
                                .setMessage(R.string.dialog_keluar_text)
                                .setPositiveButton("Keluar", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intentKeluar = new Intent(activity, AuthActivity.class);
                                        activity.startActivity(intentKeluar);
                                        authManager.logout(sessionManager.getSessionToken());
                                        sessionManager.removeSession();
                                        authManager.initContext(activity);
                                        activity.finish();
                                    }
                                })
                                .show();
                        break;

                    case "hapus":
                        alertDialog
                                .setTitle(R.string.dialog_hapus_title)
                                .setMessage(R.string.dialog_hapus_text);
                        break;

                    case "ubah":
                        alertDialog
                                .setTitle(R.string.dialog_ubah_title)
                                .setMessage(R.string.dialog_ubah_text);
                        break;

                    case "hapusForm":
                        alertDialog
                                .setTitle(R.string.dialog_hapus_form_plot)
                                .setMessage(R.string.dialog_hapus_form_plot_text);
                        break;

                    case "uploadForm":
                        alertDialog
                                .setTitle(R.string.dialog_upload_form_plot)
                                .setMessage(R.string.dialog_upload_text);
                        break;
                }
                break;
            case "Warning":
                Toasty.warning(activity,event,Toasty.LENGTH_LONG).show();
                break;
            case "Info":
                Toasty.info(activity,event,Toasty.LENGTH_LONG).show();
                break;
            case "Success":
                Toasty.success(activity,event,Toasty.LENGTH_LONG).show();
                break;
            case "Normal":
                Toasty.normal(activity,event,Toasty.LENGTH_LONG).show();
                break;
            case "Error":
                Toasty.error(activity,event,Toasty.LENGTH_LONG).show();
                break;
            default:
                break;
        }
    }

    @Override
    public void message(Message message){
        switch (message.getComponent()){
            case "JadwalPresenter":
                switch (message.getEvent()){
                    case "getAllData":
                        jadwalPresenter.getAllJadwal(getSessionToken());
                        break;
                    case "createJadwal":
                        jadwalPresenter.createJadwal(getSessionToken());
                        break;
                    case "updateBimbingan":
                        bimbinganPresenter.updateBimbingan(getSessionToken(),message.getBimbingan().getBimbingan_id());
                        break;
                    case "acceptBimbingan":
                        bimbinganPresenter.ubahStatus(getSessionToken(),message.getBimbingan().getBimbingan_id(),"approve");
                        break;
                    case "rejectBimbingan":
                        bimbinganPresenter.ubahStatus(getSessionToken(),message.getBimbingan().getBimbingan_id(),"decline");
                        break;
                }
                break;
            case "BimbinganPresenter":
                switch (message.getEvent()){
                    case "getAllData":
                        bimbinganPresenter.getAllBimbingan(getSessionToken());
                        break;
                    case "getBimbinganByUsername":
                        bimbinganPresenter.getBimbinganByParameter(getSessionToken(),message.getText());
                        break;
                    case "showData":
                        Date date;
                        Locale locale = new Locale("in", "ID");
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", locale);
                        try {
                            date = format.parse(message.getBimbingan().getBimbingan_tanggal());
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(date);
                            String month_name = new SimpleDateFormat("MMMM", Locale.ENGLISH).format(calendar.getTime());
                            bimbinganPresenter.tglBimbingan.set(calendar.get(Calendar.DATE)+" "+month_name+" "+calendar.get(Calendar.YEAR));
                            bimbinganPresenter.reviewBimbingan.set(message.getBimbingan().getBimbingan_review());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "createBimbingan":
                        bimbinganPresenter.createBimbingan(getSessionToken(),message.getText());
                        break;
                    case "updateBimbingan":
                        bimbinganPresenter.updateBimbingan(getSessionToken(),message.getBimbingan().getBimbingan_id());
                        break;
                    case "acceptBimbingan":
                        bimbinganPresenter.ubahStatus(getSessionToken(),message.getBimbingan().getBimbingan_id(),"approve");
                        break;
                    case "rejectBimbingan":
                        bimbinganPresenter.ubahStatus(getSessionToken(),message.getBimbingan().getBimbingan_id(),"decline");
                        break;
                }
                break;
            case "ProdiPresenter":
                switch (message.getEvent()){
                    case "getProdiByNIP":
                        prodiPresenter.getProdiByNIP(getSessionToken(),getSessionUsername());
                        break;
                    case "showData":
                        prodiPresenter.nama.set(message.getKoordinator().getKoor_nama());
                        prodiPresenter.nip.set(message.getKoordinator().getKoor_nip());
                        prodiPresenter.kode.set(message.getKoordinator().getKoor_kode());
                        prodiPresenter.kontak.set(message.getKoordinator().getKoor_kontak());
                        prodiPresenter.email.set(message.getKoordinator().getKoor_email());
                        break;
                }
                break;
            case "AuthPresenter":
                switch (message.getEvent()){
                    case "getCurrentUser":
                        authPresenter.getUser(getSessionToken());
                        break;
                }
                break;
            case "DosenPresenter":
                switch (message.getEvent()){
                    case "getAllData":
                        dosenPresenter.getAllDosen(getSessionToken());
                        break;
                    case "showData":
                        dosenPresenter.nama.set(message.getDosen().getDsn_nama());
                        dosenPresenter.nip.set(message.getDosen().getDsn_nip());
                        dosenPresenter.kode.set(message.getDosen().getDsn_kode());
                        dosenPresenter.kontak.set(message.getDosen().getDsn_kontak());
                        dosenPresenter.email.set(message.getDosen().getDsn_email());
                        dosenPresenter.kuota_bimbingan.set(message.getDosen().getKuota_bimbingan());
                        dosenPresenter.kuota_reviewer.set(message.getDosen().getKuota_reviewer());
                        break;
                    case "createDosen":
                        dosenPresenter.createDosen(getSessionToken());
                        break;
                    case "updateDosen":
                        dosenPresenter.updateDosen(getSessionToken());
                        break;
                }
                break;
            case "MahasiswaPresenter":
                switch (message.getEvent()){
                    case "getAllData":
                        mahasiswaPresenter.getAllMahasiswa(getSessionToken());
                        break;
                    case "getMahasiswaByNIM":
                        mahasiswaPresenter.getMahasiswaByNIM(getSessionToken(),message.getMahasiswa().getMhs_nim());
                        break;
                    case "getMahasiswaBySession":
                        mahasiswaPresenter.getMahasiswaByNIM(getSessionToken(),getSessionUsername());
                        break;
                    case "getPembimbing":
                        mahasiswaPresenter.getPembimbing(getSessionToken(),message.getMahasiswa().getPlot_id());
                        break;
                    case "deletePembimbing":
                        mahasiswaPresenter.deletePembimbing(getSessionToken(),message.getMahasiswa().getMhs_nim());
                        break;
                    case "updateSKTA":
                        message(message.setComponent("AlertDialog").setEvent("set"));
                        message(message.setComponent("AlertDialog").setEvent("updateSKTA"));
                        break;
                    case "toolbarIntent":
                        activity.startActivity(mahasiswaPresenter.toolbarIntent(message.getMahasiswa(),message.getaClass()));
                        break;
                    case "showData":
                        mahasiswaPresenter.nama.set(message.getMahasiswa().getMhs_nama());
                        mahasiswaPresenter.nim.set(message.getMahasiswa().getMhs_nim());
                        mahasiswaPresenter.angkatan.set(message.getMahasiswa().getAngkatan());
                        mahasiswaPresenter.email.set(message.getMahasiswa().getMhs_email());
                        mahasiswaPresenter.telp.set(message.getMahasiswa().getMhs_kontak());
                        mahasiswaPresenter.judul.set(message.getMahasiswa().getJudul());
                        mahasiswaPresenter.judulInggris.set(message.getMahasiswa().getJudul_inggris());
                        break;
                    case "createMahasiswa":
                        mahasiswaPresenter.createMahasiswa(getSessionToken());
                        break;
                    case "updateMahasiswa":
                        mahasiswaPresenter.updateMahasiswa(getSessionToken());
                        break;
                }
                break;
            case "PlottingPresenter":
                switch (message.getEvent()){
                    case "getAllData":
                        plottingPresenter.getAllPlotting(getSessionToken());
                        break;
                    case "uploadForm":
                        plottingPresenter.uploadForm(getSessionToken(),fileHelper.getFile(message.getUri()));
                        break;
                    case "updatePlotting":
                        plottingPresenter.updatePlotting(getSessionToken(),message.getPlotting().getId(),message.getText(),message.getUrl());
                        break;
                    case "createPlotting":
                        plottingPresenter.createPlotting(getSessionToken(),message.getText(),message.getUrl());
                        break;
                    case "checkForm":
                        plottingPresenter.checkFrom(getSessionToken());
                        break;
                }
                break;
            case "DosenBimbinganViewAdapter":
                switch (message.getEvent()){
                    case SET:
                        dosenBimbinganViewAdapter = new DosenBimbinganViewAdapter(activity);
                        break;
                    case "addItem":
                        dosenBimbinganViewAdapter.addItem(message.getItem());
                        break;
                    case "setAdapter":
                        recyclerView.setAdapter(dosenBimbinganViewAdapter);
                        break;
                }
                break;
            case "DosenMahasiswaBimbinganViewAdapter":
                switch (message.getEvent()){
                    case SET:
                        dosenMahasiswaBimbinganViewAdapter = new DosenMahasiswaBimbinganViewAdapter(activity);
                        break;
                    case "addItem":
                        dosenMahasiswaBimbinganViewAdapter.addItem(message.getItem());
                        break;
                    case "setAdapter":
                        recyclerView.setAdapter(dosenMahasiswaBimbinganViewAdapter);
                        break;
                }
                break;
            case "MahasiswaBimbinganViewAdapter":
                switch (message.getEvent()){
                    case SET:
                        mahasiswaBimbinganViewAdapter = new MahasiswaBimbinganViewAdapter(activity);
                        mahasiswaBimbinganViewAdapter.setBimbinganPresenter(bimbinganPresenter);
                        mahasiswaBimbinganViewAdapter.setToken(getSessionToken());
                        break;
                    case "addItem":
                        mahasiswaBimbinganViewAdapter.addItem(message.getItem());
                        break;
                    case "setAdapter":
                        recyclerView.setAdapter(mahasiswaBimbinganViewAdapter);
                        break;
                }
                break;
            case "ProdiPlotPembimbingViewAdapter":
                switch (message.getEvent()){
                    case SET:
                        prodiPlotPembimbingViewAdapter = new ProdiPlotPembimbingViewAdapter(activity);
                        break;
                    case "addItem":
                        prodiPlotPembimbingViewAdapter.addItem(message.getItem());
                        break;
                    case "setAdapter":
                        recyclerView.setAdapter(prodiPlotPembimbingViewAdapter);
                        break;
                    case "setToken":
                        prodiPlotPembimbingViewAdapter.setToken(getSessionToken());
                        break;
                    case "setPresenter":
                        prodiPlotPembimbingViewAdapter.setMahasiswaPresenter(mahasiswaPresenter);
                        prodiPlotPembimbingViewAdapter.setMhs_nim(message.getMahasiswa().getMhs_nim());
                        break;
                }
                break;
            case "ProdiPlottingAdapter":
                switch (message.getEvent()){
                    case SET:
                        plottingViewAdapter = new ProdiPlottingViewAdapter(activity);
                        plottingViewAdapter.setToken(getSessionToken());
                        plottingViewAdapter.setPlottingPresenter(this.plottingPresenter);
                        break;
                    case "addItem":
                        plottingViewAdapter.addItem(message.getItem());
                        break;
                    case "setAdapter":
                        recyclerView.setAdapter(plottingViewAdapter);
                        break;
                }
                break;
            case "ProdiSKTAViewAdapter":
                switch (message.getEvent()){
                    case SET:
                        prodiSKTAViewAdapter = new ProdiSKTAViewAdapter(activity);
                        break;
                    case "addItem":
                        prodiSKTAViewAdapter.addItem(message.getItem());
                        break;
                    case "setAdapter":
                        recyclerView.setAdapter(prodiSKTAViewAdapter);
                        break;
                }
                break;
            case "InformasiViewAdapter":
                switch (message.getEvent()){
                    case SET:
                        informasiViewAdapter = new InformasiViewAdapter(activity);
                        break;
                }
                break;
            case "ProdiMahasiswaAdapter":
                switch (message.getEvent()){
                    case SET:
                        mahasiswaViewAdapter = new ProdiMahasiswaViewAdapter(activity);
                        break;
                    case "addItem":
                        mahasiswaViewAdapter.addItem(message.getItem());
                        break;
                    case "setAdapter":
                        recyclerView.setAdapter(mahasiswaViewAdapter);
                        mahasiswaViewAdapter.setPengguna(getSessionPengguna());
                        break;
                }
                break;
            case "ProdiDosenAdapter":
                switch (message.getEvent()){
                    case SET:
                        dosenViewAdapter = new ProdiDosenViewAdapter(activity);
                        break;
                    case "addItem":
                        dosenViewAdapter.addItem(message.getItem());
                        break;
                    case "setAdapter":
                        recyclerView.setAdapter(dosenViewAdapter);
                        break;
                }
                break;
            case "FileHelper":
                switch (message.getEvent()){
                    case SET:
                        fileHelper = new FileHelper(activity.getApplicationContext(),activity);
                        break;
                    case "openFile":
                        fileHelper.openFile(message.getBody(),message.getText());
                        break;
                }
                break;
            case "MethodHelper":
                switch (message.getEvent()){
                    case SET:
                        methodHelper = new MethodHelper(activity);
                        break;
                    case "setDataPicker":
                        methodHelper.setDatePicker(activity, textView);
                        break;
                    case "secondDataPicker":
                        methodHelper.secondDatePicker(activity, textView);
                        break;
                }
                break;
            case "Spinner":
                switch (message.getEvent()){
                    case "setAdapter":
                        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(activity,android.R.layout.simple_spinner_dropdown_item,message.getItem());
                        this.spinner.setAdapter(spinnerAdapter);
                        break;
                    case "setSelection1":
                        this.spinner.setSelection(message.getItem().indexOf(message.getPlotting().getNip_pembimbing_1()));
                        break;
                    case "setSelection2":
                        this.spinner.setSelection(message.getItem().indexOf(message.getPlotting().getNip_pembimbing_2()));
                        break;
                }
                break;
            case "CircleImageView":
                switch (message.getEvent()){
                    case "setVisibility":
                        this.circleImageView.setVisibility(message.getVisibility());
                        break;
                    case "setImage":
                        Picasso.get().load(message.getUrl()).into(circleImageView);
                        break;
                }
                break;
            case "CompactCalendar":
                switch (message.getEvent()){
                    case "setListener":
                        SimpleDateFormat formatter = new SimpleDateFormat("MMMM, yyyy",new Locale("in","ID"));
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",new Locale("in","ID"));
                        textView.setText(formatter.format(new Date()));
                        compactCalendarView.removeAllEvents();
                        for (int i = 0; i < message.getKegiatanList().size(); i++) {
                            JadwalKegiatan jadwalKegiatan = message.getKegiatanList().get(i);
                            try {
                                Calendar start = Calendar.getInstance();
                                Calendar end = Calendar.getInstance();
                                start.setTime(dateFormat.parse(jadwalKegiatan.getTanggal_mulai()));
                                end.setTime(dateFormat.parse(jadwalKegiatan.getTanggal_akhir()));
                                end.add(Calendar.DATE,1);
                                for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
                                    compactCalendarView.addEvent(new Event(Color.RED,date.getTime(),jadwalKegiatan.getNama_kegiatan()));
                                }
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                        this.compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
                            @Override
                            public void onDayClick(Date dateClicked) {
                                List<Event> events = compactCalendarView.getEvents(dateClicked);
                                ArrayList<String> dataList = new ArrayList<>();
                                for (int i = 0; i < events.size(); i++) {
                                    dataList.add(events.get(i).getData().toString());
                                }
                                KegiatanAdapter kegiatanAdapter = new KegiatanAdapter(activity);
                                kegiatanAdapter.setDosens(dataList);
                                recyclerView.setAdapter(kegiatanAdapter);
                            }

                            @Override
                            public void onMonthScroll(Date firstDayOfNewMonth) {
                                textView.setText(formatter.format(firstDayOfNewMonth));
                            }
                        });
                        break;
                    case "setTextColor":
                        this.textView.setTextColor(Color.parseColor(message.getColor()));
                        break;
                    case "setVisibility":
                        this.textView.setVisibility(message.getVisibility());
                        break;
                    case "setEnabled":
                        this.textView.setEnabled(message.isEnabled());
                        break;
                }
                break;
            case "TextView":
                switch (message.getEvent()){
                    case "setText":
                        this.textView.setText(message.getText());
                        break;
                    case "setTextColor":
                        this.textView.setTextColor(Color.parseColor(message.getColor()));
                        break;
                    case "setVisibility":
                        this.textView.setVisibility(message.getVisibility());
                        break;
                    case "setEnabled":
                        this.textView.setEnabled(message.isEnabled());
                        break;
                }
                break;
            case "FloatButton":
                switch (message.getEvent()){
                    case "setVisibility":
                        this.floatingActionButton.setVisibility(message.getVisibility());
                        break;
                    case "setEnabled":
                        this.floatingActionButton.setEnabled(message.isEnabled());
                        break;
                }
                break;
            case "CardView":
                switch (message.getEvent()){
                    case "setVisibility":
                        this.cardView.setVisibility(message.getVisibility());
                        break;
                    case "setOnClick":
                        this.cardView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                selectIntent(message);
                            }
                        });
                }
                break;
            case "LinearLayout":
                switch (message.getEvent()){
                    case "setVisibility":
                        this.linearLayout.setVisibility(message.getVisibility());
                        break;
                }
                break;
            case "RelativeLayout":
                switch (message.getEvent()){
                    case "setVisibility":
                        this.relativeLayout.setVisibility(message.getVisibility());
                        break;
                }
                break;
            case "RefreshLayout":
                switch (message.getEvent()){
                    case "setRefreshing":
                        this.refreshLayout.setRefreshing(message.isEnabled());
                        break;
                }
                break;
            case "ProgressDialog":
                switch (message.getEvent()){
                    case SET:
                        progressDialog = new ProgressDialog(activity);
                        progressDialog.setMessage(activity.getString(R.string.text_progress_dialog));
                        break;
                    case "show":
                        progressDialog.show();
                        break;
                    case "dismiss":
                        progressDialog.dismiss();
                        break;
                }
                break;
            case "SessionManager":
                switch (message.getEvent()){
                    case SET:
                        sessionManager = new SessionManager(this.activity);
                        break;
                    case "updateKoor":
                        sessionManager.updateSessionKoor(message.getKoordinator().getKoor_nip(),message.getKoordinator().getKoor_nama(),message.getKoordinator().getKoor_kode(),
                                message.getKoordinator().getKoor_kontak(),message.getKoordinator().getKoor_email());
                        break;
                    case "createMahasiswa":
                        sessionManager.createSessionDataMahasiswa(message.getMahasiswa());
                        break;
                }
                break;
            case "AlertDialog":
                switch (message.getEvent()){
                    case SET:
                        alertDialog = new AlertDialog.Builder(activity).setIcon(android.R.drawable.ic_dialog_alert).setNegativeButton("Batal",null);
                        break;

                    case "logout":
                        alertDialog
                                .setTitle(R.string.dialog_keluar_title)
                                .setMessage(R.string.dialog_keluar_text)
                                .setPositiveButton("Keluar", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intentKeluar = new Intent(activity, AuthActivity.class);
                                        activity.startActivity(intentKeluar);
                                        authManager.logout(sessionManager.getSessionToken());
                                        sessionManager.removeSession();
                                        authManager.initContext(activity);
                                        activity.finish();
                                    }
                                })
                                .show();
                        break;

                    case "hapus":
                        alertDialog
                                .setTitle(R.string.dialog_hapus_title)
                                .setMessage(R.string.dialog_hapus_text);
                        break;

                    case "ubah":
                        alertDialog
                                .setTitle(R.string.dialog_ubah_title)
                                .setMessage(R.string.dialog_ubah_text);
                        break;

                    case "hapusForm":
                        alertDialog
                                .setTitle(R.string.dialog_hapus_form_plot)
                                .setMessage(R.string.dialog_hapus_form_plot_text)
                                .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        plottingPresenter.showProgress();
                                        plottingPresenter.deleteForm(getSessionToken());
                                    }
                                }).show();
                        break;

                        case "hapusDataMahasiswa":
                        alertDialog
                                .setTitle(R.string.dialog_hapus_title)
                                .setMessage(R.string.dialog_hapus_text)
                                .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        mahasiswaPresenter.deleteMahasiswa(getSessionToken(),message.getMahasiswa().getMhs_nim());
                                    }
                                }).show();
                        break;

                        case "hapusDataDosen":
                        alertDialog
                                .setTitle(R.string.dialog_hapus_title)
                                .setMessage(R.string.dialog_hapus_text)
                                .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dosenPresenter.deleteDosen(getSessionToken(),message.getDosen().getDsn_nip());
                                    }
                                }).show();
                        break;

                    case "uploadForm":
                        alertDialog
                                .setTitle(R.string.dialog_upload_form_plot)
                                .setMessage(R.string.dialog_upload_text)
                                .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        plottingPresenter.showProgress();
                                        plottingPresenter.onIntent();
                                    }
                                }).show();
                        break;

                    case "updateSKTA":
                        alertDialog
                                .setTitle(R.string.dialog_update_skta)
                                .setMessage(R.string.dialog_update_skta_text)
                                .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        mahasiswaPresenter.updateSKTA(getSessionToken(),message.getMahasiswa().getMhs_nim());
                                    }
                                }).show();
                        break;
                }
                break;
            case "BottomNavigationView":
                switch (message.getEvent()){
                    case "setOnNavigationItemSelectedListener":
                        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                            @Override
                            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                                message(message.setComponent("BottomNavigation").setVisibility(item.getItemId()).setEvent("mahasiswa"));
                                return true;
                            }
                        });
                        break;
                    case "setFirstOpen":
                        switch (message.getText()){
                            case "mahasiswa":
                                bottomNavigationView.getMenu().getItem(0).setChecked(true);
                                bottomNavigationView.setSelectedItemId(R.id.bottom_menu_mhs_informasi);
                                break;
                        }
                        break;
                }
                break;
            case "Toasty":
                switch (message.getEvent()){
                    case "Warning":
                        Toasty.warning(activity,message.getText(),Toasty.LENGTH_LONG).show();
                        break;
                    case "Info":
                        Toasty.info(activity,message.getText(),Toasty.LENGTH_LONG).show();
                        break;
                    case "Success":
                        Toasty.success(activity,message.getText(),Toasty.LENGTH_LONG).show();
                        break;
                    case "Normal":
                        Toasty.normal(activity,message.getText(),Toasty.LENGTH_LONG).show();
                        break;
                    case "Error":
                        Toasty.error(activity,message.getText(),Toasty.LENGTH_LONG).show();
                        break;
                }
                break;
            case "Handler":
                switch (message.getEvent()){
                    case "onSuccess":
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                plottingPresenter.onRefresh(getSessionToken());
                            }
                        },300);
                        break;
                }
                break;
            case "Toolbar":
                switch (message.getVisibility()){
                    case R.id.toolbar_menu_hapus:
                        switch (message.getEvent()){
                            case "mahasiswa":
                                message(message.setComponent("AlertDialog").setEvent("set"));
                                message(message.setComponent("AlertDialog").setEvent("hapusDataMahasiswa"));
                                break;
                            case "dosen":
                                message(message.setComponent("AlertDialog").setEvent("set"));
                                message(message.setComponent("AlertDialog").setEvent("hapusDataDosen"));
                                break;
                        }
                        break;
                    case R.id.toolbar_menu_ubah:
                        switch (message.getEvent()){
                            case "mahasiswa":
                                activity.startActivity(mahasiswaPresenter.toolbarIntent(message.getMahasiswa(), ProdiMahasiswaEditorActivity.class));
                                break;
                            case "dosen":
                                activity.startActivity(dosenPresenter.toolbarIntent(message.getDosen()));
                                break;
                        }
                        break;
                    case R.id.toolbar_menu_ubah_yes:
                        switch (message.getEvent()){
                            case "koordinator":
                                prodiPresenter.updateProdi(getSessionToken(),message.getKoordinator().getUsername());
                                break;
                        }
                        break;
                    case R.id.toolbar_menu_hanya_ubah:
                        switch (message.getEvent()){
                            case "koordinator":
                                activity.startActivity(prodiPresenter.toolbarIntent(message.getKoordinator()));
                                break;
                        }
                        break;
                    case R.id.toolbar_menu_pemberitahuan:
                        switch (message.getEvent()){
                            case "mahasiswa":
                                selectIntent(message.setaClass(MahasiswaPemberitahuanActivity.class));
                                break;
                        }
                        break;
                    case R.id.toolbar_menu_profil:
                        switch (message.getEvent()){
                            case "mahasiswa":
                                selectIntent(message.setaClass(MahasiswaProfilActivity.class));
                                break;
                            case "dosen":
                                selectIntent(message.setaClass(DosenProfilActivity.class));
                                break;
                            default:
                                selectIntent(message.setaClass(KoorProfilActivity.class));
                                break;
                        }
                        break;
                    case R.id.toolbar_menu_jadwal_kegiatan:
                        switch (message.getEvent()){
                            case "mahasiswa":
                                selectIntent(message.setaClass(MahasiswaJadwalKegiatanActivity.class));
                                break;
                        }
                        break;
                    case R.id.toolbar_menu_keluar:
                        message("AlertDialog",SET);
                        message("AlertDialog","logout");
                        break;
                        case android.R.id.home:
                        activity.finish();
                        break;
                }
                break;
            case "BottomNavigation":
                switch (message.getVisibility()){
                    case R.id.bottom_menu_mhs_informasi:
                        methodHelper.applyFragment(new InformasiFragment(),"InformasiFragment");
                        activity.setTitle(R.string.title_informasi);
                        break;
                    case R.id.bottom_menu_mhs_ta:
                        methodHelper.applyFragment(new MahasiswaTugasAkhirFragment(),"TugasAkhirFragment");
                        activity.setTitle(R.string.title_ta);
                        break;
                    case R.id.bottom_menu_mhs_sidang:
                        methodHelper.applyFragment(new SidangFragment(), "SidangFragment");
                        activity.setTitle(R.string.title_sidang);
                        break;
                    case R.id.bottom_menu_mahasiswa:
                        methodHelper.applyFragment(new ProdiMahasiswaFragment(),"ProdiMahasiswaFragment");
                        activity.setTitle(R.string.title_mahasiswa_ta);
                        break;
                    case R.id.bottom_menu_mhs_jadwal_kegiatan:
                        methodHelper.applyFragment(new LAKJadwalKegiatanFragment(), "LAKJadwalKegiatanFragment");
                        activity.setTitle(R.string.title_jadwal_kegiatan);
                        break;
                }
                break;
            default:
                break;
        }
    }

    public void setRelativeLayout(RelativeLayout relativeLayout) {
        this.relativeLayout = relativeLayout;
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public void setRefreshLayout(SwipeRefreshLayout refreshLayout) {
        this.refreshLayout = refreshLayout;
    }

    public void setLinearLayout(LinearLayout linearLayout) {
        this.linearLayout = linearLayout;
    }

    public void setSpinner(Spinner spinner) {
        this.spinner = spinner;
    }

    public void setCardView(CardView cardView) {
        this.cardView = cardView;
    }

    public void setCircleImageView(CircleImageView circleImageView) {
        this.circleImageView = circleImageView;
    }

    public void setBottomNavigationView(BottomNavigationView bottomNavigationView) {
        this.bottomNavigationView = bottomNavigationView;
    }

    public void setFloatingActionButton(FloatingActionButton floatingActionButton) {
        this.floatingActionButton = floatingActionButton;
    }

    public void setCompactCalendarView(CompactCalendarView compactCalendarView) {
        this.compactCalendarView = compactCalendarView;
    }

    @Override
    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    public void setActionBarDrawerToggle(DrawerLayout drawerLayout, Toolbar toolbar) {
        actionBarDrawerToggle = new ActionBarDrawerToggle(activity,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        actionBarDrawerToggle.syncState();
    }

    public void setPlottingPresenter(PlottingContract.ViewModel viewModel){
        this.plottingPresenter = new PlottingPresenter(viewModel);
    }

    public void setMahasiswaPresenter(MahasiswaContract.ViewModel viewModel){
        this.mahasiswaPresenter = new MahasiswaPresenter(viewModel);
    }

    public void setDosenPresenter(DosenContract.ViewModel viewModel) {
        this.dosenPresenter = new DosenPresenter(viewModel);
    }

    public void setAuthPresenter(AuthContract.ViewModel viewModel) {
        this.authPresenter = new AuthPresenter(viewModel);
    }

    public void setProdiPresenter(ProdiContract.ViewModel viewModel) {
        this.prodiPresenter = new ProdiPresenter(viewModel);
    }

    public void setBimbinganPresenter(BimbinganContract.ViewModel viewModel) {
        this.bimbinganPresenter = new BimbinganPresenter(viewModel);
    }

    public void setJadwalPresenter(JadwalContract.ViewModel viewModel) {
        this.jadwalPresenter = new JadwalPresenter(viewModel);
    }

    @Override
    public String getSessionToken() {
        return this.sessionManager.getSessionToken();
    }

    public String getSessionUsername() {
        return this.sessionManager.getSessionUsername();
    }

    public String getSessionPengguna(){
        return this.sessionManager.getSessionPengguna();
    }

    @Override
    public boolean getPermissionFile() {
        return fileHelper.permissionCheck();
    }

    public void selectIntent(Message message){
        Intent intent = new Intent(activity, message.getaClass());
        if (!TextUtils.isEmpty(message.getExtraIntent())){
            intent.putExtra(EXTRA_DEFAULT,message.getExtraIntent());
        }
        activity.startActivity(intent);
    }

    public Intent findFileIntent(){
        Intent intent = new Intent();
        intent.setType(FILE_TYPE_XLS);
        String[] mimetypes = {FILE_TYPE_XLS,FILE_TYPE_XLSX};
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes);
        return intent;
    }

    public String getUsernameDosen(){
        return this.dosenUsername;
    }

    public FileHelper getFileHelper() {
        return fileHelper;
    }

    public AlertDialog.Builder getAlertDialog() {
        return alertDialog;
    }

    public ActionBarDrawerToggle getActionBarDrawerToggle() {
        return actionBarDrawerToggle;
    }

    public ProgressDialog getProgressDialog(){
        return this.progressDialog;
    }

    public SessionManager getSessionManager(){
        return this.sessionManager;
    }

    public ProdiDosenViewAdapter getDosenViewAdapter() {
        return dosenViewAdapter;
    }

    public ProdiMahasiswaViewAdapter getMahasiswaViewAdapter() {
        return mahasiswaViewAdapter;
    }

    public ProdiPlottingViewAdapter getPlottingViewAdapter(){ return plottingViewAdapter; }

    public InformasiViewAdapter getInformasiViewAdapter() {
        return informasiViewAdapter;
    }

    public PlottingPresenter getPlottingPresenter() {
        return plottingPresenter;
    }

    public MahasiswaPresenter getMahasiswaPresenter() {
        return mahasiswaPresenter;
    }

    public DosenPresenter getDosenPresenter() {
        return dosenPresenter;
    }

    public AuthPresenter getAuthPresenter() {
        return authPresenter;
    }

    public ProdiPresenter getProdiPresenter() {
        return prodiPresenter;
    }

    public BimbinganPresenter getBimbinganPresenter() {
        return bimbinganPresenter;
    }

    public JadwalPresenter getJadwalPresenter() {
        return jadwalPresenter;
    }
}
