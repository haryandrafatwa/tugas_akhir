package org.d3ifcool.finpro.prodi.activities;

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
import org.d3ifcool.finpro.core.interfaces.lists.NotifikasiListView;
import org.d3ifcool.finpro.core.interfaces.works.NotifikasiWorkView;
import org.d3ifcool.finpro.core.models.Notifikasi;
import org.d3ifcool.finpro.core.presenters.NotifikasiPresenter;
import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.prodi.adapters.KoorPemberitahuanViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class KoorPemberitahuanActivity extends AppCompatActivity implements NotifikasiListView, NotifikasiWorkView {

    private RecyclerView recyclerView;
    private NotifikasiPresenter notifikasiPresenter;
    private ProgressDialog progressDialog;

    private ArrayList<Notifikasi> notifikasiArrayList = new ArrayList<>();
    private KoorPemberitahuanViewAdapter adapter;
    private View empty_view;

    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koor_pemberitahuan);

        setTitle(getString(R.string.title_pemberitahuan));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressDialog = new ProgressDialog(this);

        notifikasiPresenter = new NotifikasiPresenter(this, this);
        notifikasiPresenter.initContext(this);

        sessionManager = new SessionManager(this);

        adapter = new KoorPemberitahuanViewAdapter(this);
        recyclerView = findViewById(R.id.all_recyclerview_pemberitahuan);
        empty_view = findViewById(R.id.view_emptyview);
        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, linearLayoutManager.getOrientation());

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(itemDecoration);

        adapter.setNotifikasiPresenter(notifikasiPresenter);

        notifikasiPresenter.sortNotifikasi(sessionManager.getSessionKoorNip(), sessionManager.getSessionKoorNama());

    }

    @Override
    protected void onResume() {
        super.onResume();
        notifikasiPresenter.sortNotifikasi(sessionManager.getSessionKoorNip(), sessionManager.getSessionKoorNama());
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

        if (notifikasiArrayList.size() != 0){
            empty_view.setVisibility(View.GONE);
        } else {
            empty_view.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onMessage(String message) {
        switch (message){
            case "ShowProgressDialog":
                progressDialog.show();
                break;
            case "HideProgressDialog":
                progressDialog.hide();
                break;
            case "EmptyList":
                empty_view.setVisibility(View.VISIBLE);
                break;
            default:
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
