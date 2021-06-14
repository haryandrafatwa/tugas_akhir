package org.d3ifcool.finpro.core.mediators.prodi;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.squareup.picasso.Picasso;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.activities.AuthActivity;
import org.d3ifcool.finpro.activities.TentangKamiActivity;
import org.d3ifcool.finpro.adapters.InformasiViewAdapter;
import org.d3ifcool.finpro.core.helpers.FileHelper;
import org.d3ifcool.finpro.core.helpers.Message;
import org.d3ifcool.finpro.core.helpers.MethodHelper;
import org.d3ifcool.finpro.core.helpers.SessionManager;
import org.d3ifcool.finpro.core.interfaces.DosenContract;
import org.d3ifcool.finpro.core.interfaces.MahasiswaContract;
import org.d3ifcool.finpro.core.interfaces.PlottingContract;
import org.d3ifcool.finpro.core.mediators.interfaces.prodi.Mediator;
import org.d3ifcool.finpro.core.models.manager.AuthManager;
import org.d3ifcool.finpro.core.models.manager.MahasiswaManager;
import org.d3ifcool.finpro.core.presenters.DosenPresenter;
import org.d3ifcool.finpro.core.presenters.MahasiswaPresenter;
import org.d3ifcool.finpro.core.presenters.PlottingPresenter;
import org.d3ifcool.finpro.dosen.activities.DosenJadwalKegiatanActivity;
import org.d3ifcool.finpro.dosen.activities.DosenJudulPaArsipActivity;
import org.d3ifcool.finpro.dosen.activities.DosenKuotaDosenActivity;
import org.d3ifcool.finpro.dosen.activities.DosenPemberitahuanActivity;
import org.d3ifcool.finpro.dosen.activities.DosenProfilActivity;
import org.d3ifcool.finpro.dosen.fragments.parent.DosenJudulFragment;
import org.d3ifcool.finpro.dosen.fragments.parent.DosenPaFragment;
import org.d3ifcool.finpro.fragments.InformasiFragment;
import org.d3ifcool.finpro.prodi.adapters.ProdiDosenViewAdapter;
import org.d3ifcool.finpro.prodi.adapters.ProdiMahasiswaViewAdapter;
import org.d3ifcool.finpro.prodi.adapters.ProdiPlottingViewAdapter;
import org.d3ifcool.finpro.prodi.fragments.ProdiDosenFragment;
import org.d3ifcool.finpro.prodi.fragments.ProdiMahasiswaFragment;
import org.d3ifcool.finpro.prodi.fragments.ProdiPlottingFragment;
import org.d3ifcool.finpro.prodi.fragments.ProdiSKTAFragment;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.FILE_TYPE_XLS;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.FILE_TYPE_XLSX;

public class ConcreteMediator implements Mediator {

    private AppCompatActivity activity;
    private AuthManager authManager;
    private MethodHelper methodHelper;
    private FileHelper fileHelper;
    private String dosenUsername;

    private PlottingPresenter plottingPresenter;
    private MahasiswaPresenter mahasiswaPresenter;
    private DosenPresenter dosenPresenter;

    private RelativeLayout relativeLayout;
    private LinearLayout linearLayout;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private TextView textView;
    private Spinner spinner;
    private CircleImageView circleImageView;

    private ProdiDosenViewAdapter dosenViewAdapter;
    private ProdiMahasiswaViewAdapter mahasiswaViewAdapter;
    private ProdiPlottingViewAdapter plottingViewAdapter;
    private InformasiViewAdapter informasiViewAdapter;

    private SessionManager sessionManager;
    private AlertDialog.Builder alertDialog;
    private ProgressDialog progressDialog;
    private ActionBarDrawerToggle actionBarDrawerToggle;

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
            case R.id.bottom_menu_dsn_pa:
                methodHelper.applyFragment(new DosenPaFragment(),"DosenPAFragment");
                this.activity.setTitle(R.string.title_proyekakhir);
                break;
            case R.id.bottom_menu_dsn_judulpa:
                methodHelper.applyFragment(new DosenJudulFragment(),"DosenJudulFragment");
                this.activity.setTitle(R.string.title_judulta);
                break;

                //todo bisa dijadiin satu sama yg lain
            case R.id.toolbar_menu_pemberitahuan:
                selectIntent(DosenPemberitahuanActivity.class);
                break;

            //todo bisa dijadiin satu sama yg lain
            case R.id.toolbar_menu_profil:
                selectIntent(DosenProfilActivity.class);
                break;

            case R.id.toolbar_menu_jadwal_kegiatan:
                selectIntent(DosenJadwalKegiatanActivity.class);
                break;

            case R.id.toolbar_menu_arsip_judul:
                selectIntent(DosenJudulPaArsipActivity.class);
                break;

            case R.id.toolbar_menu_tentang_kami:
                selectIntent(TentangKamiActivity.class);
                break;

            case R.id.toolbar_menu_kuota_dosen:
                selectIntent(DosenKuotaDosenActivity.class);
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
    public void message(String component, String event, Uri uri) {
        switch (component){
            case "Plotting":
                switch (event){
                    case "upload":
                        plottingPresenter.uploadForm(getSessionToken(),fileHelper.getFile(uri));
                        break;
                }
                break;
            case "Mahasiswa":
                switch (event){
                    case "upload":
                        mahasiswaPresenter.updateSKTA(getSessionToken(),mahasiswaPresenter.nim.get(),fileHelper.getFile(uri));
                        break;
                }
                break;
        }
    }

    @Override
    public void message(Message message) {
        switch (message.getComponent()){
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
                    case "getPembimbing":
                        mahasiswaPresenter.getPembimbing(getSessionToken(),message.getMahasiswa().getPlot_id());
                        break;
                    case "updateSKTA":
                        mahasiswaPresenter.updateSKTA(getSessionToken(),message.getMahasiswa().getMhs_nim(), fileHelper.getFile(message.getUri()));
                        break;
                    case "toolbarIntent":
                        mahasiswaPresenter.toolbarIntent(message.getMahasiswa());
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
                                activity.startActivity(mahasiswaPresenter.toolbarIntent(message.getMahasiswa()));
                                break;
                            case "dosen":
                                activity.startActivity(dosenPresenter.toolbarIntent(message.getDosen()));
                                break;
                        }
                        break;
                    default:
                        activity.finish();
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

    public void setCircleImageView(CircleImageView circleImageView) {
        this.circleImageView = circleImageView;
    }

    @Override
    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    @Override
    public String getSessionToken() {
        return this.sessionManager.getSessionToken();
    }

    @Override
    public boolean getPermissionFile() {
        return fileHelper.permissionCheck();
    }

    public void selectIntent(Class aClass){
        Intent intent = new Intent(activity, aClass);
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
}
