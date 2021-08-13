package org.d3ifcool.finpro.dosen.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.d3ifcool.finpro.core.helpers.SessionManager;
import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.interfaces.NotifikasiContract;
import org.d3ifcool.finpro.dosen.adapters.recyclerview.DosenPemberitahuanViewAdapter;
import org.d3ifcool.finpro.core.models.Notifikasi;
import org.d3ifcool.finpro.core.presenters.NotifikasiPresenter;

import java.util.ArrayList;
import java.util.List;

public class DosenPemberitahuanActivity extends AppCompatActivity implements NotifikasiContract.ViewModel {

    private RecyclerView recyclerView;
    private NotifikasiPresenter notifikasiPresenter;
    private ProgressDialog progressDialog;

    private ArrayList<Notifikasi> notifikasiArrayList = new ArrayList<>();
    private DosenPemberitahuanViewAdapter adapter;
    private View empty_view;

    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_pemberitahuan);

        setTitle(getString(R.string.title_pemberitahuan));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressDialog = new ProgressDialog(this);
        notifikasiPresenter = new NotifikasiPresenter(this);
        notifikasiPresenter.initContext(this);
        sessionManager = new SessionManager(this);

        adapter = new DosenPemberitahuanViewAdapter(this);
        recyclerView = findViewById(R.id.all_recyclerview_pemberitahuan);
        empty_view = findViewById(R.id.view_emptyview);
        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, linearLayoutManager.getOrientation());

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(itemDecoration);

        adapter.setNotifikasiPresenter(notifikasiPresenter);

        notifikasiPresenter.sortNotifikasi(sessionManager.getSessionDosenNip(), sessionManager.getSessionDosenNama());

    }

    @Override
    protected void onResume() {
        super.onResume();
        notifikasiPresenter.sortNotifikasi(sessionManager.getSessionDosenNip(), sessionManager.getSessionDosenNama());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onGetListNotifikasi(List<Notifikasi> notifikasiList) {

        notifikasiArrayList.clear();
        notifikasiArrayList.addAll(notifikasiList);
        adapter.addItem(notifikasiArrayList);
        recyclerView.setAdapter(adapter);

        if (notifikasiArrayList.size() == 0){
            empty_view.setVisibility(View.VISIBLE);
        } else {
            empty_view.setVisibility(View.GONE);
        }

    }

    @Override
    public void isEmptyListNotifikasi() {

    }

    @Override
    public void onGetObjectNotifikasi(Notifikasi notifikasi) {

    }

    @Override
    public void isEmptyObjectNotifikasi() {

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
            case "EmptyList":
                empty_view.setVisibility(View.VISIBLE);
            default:
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
