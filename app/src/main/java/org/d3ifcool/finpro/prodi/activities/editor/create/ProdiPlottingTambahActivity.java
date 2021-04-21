package org.d3ifcool.finpro.prodi.activities.editor.create;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.interfaces.lists.DosenListView;
import org.d3ifcool.finpro.core.interfaces.works.PlottingWorkView;
import org.d3ifcool.finpro.core.mediators.interfaces.prodi.ProdiActivityMediator;
import org.d3ifcool.finpro.core.mediators.prodi.ProdiActivityConcrete;
import org.d3ifcool.finpro.core.models.Dosen;
import org.d3ifcool.finpro.core.presenters.DosenPresenters;
import org.d3ifcool.finpro.core.presenters.PlottingPresenter;

import java.util.ArrayList;
import java.util.List;

public class ProdiPlottingTambahActivity extends AppCompatActivity implements PlottingWorkView, DosenListView {

    private ArrayList<String> usernameList = new ArrayList<>();
    private ArrayList<String> arrayList = new ArrayList<>();
    private PlottingPresenter plottingPresenter;
    private DosenPresenters dosenPresenters;
    private ProdiActivityMediator mediator;
    private String dosen_1,dosen_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koor_plotting_tambah);

        setTitle(getString(R.string.title_plotting_tambah));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mediator = new ProdiActivityConcrete(this);
        mediator.message("SessionManager");
        mediator.message("ProgressDialog");

        plottingPresenter = new PlottingPresenter(this);
        plottingPresenter.initContext(this);

        dosenPresenters = new DosenPresenters(this);
        dosenPresenters.initContext(this);
        dosenPresenters.getDosen();

        mediator.Notify(R.id.act_koor__dosen_spinner_dosen_1);
        mediator.Notify(R.id.act_koor__dosen_spinner_dosen_2);
        mediator.Notify(R.id.act_koor_info_button_simpan);

        mediator.getButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dosen_1.equalsIgnoreCase("-")) {
                    setError((TextView) mediator.getSPDosen1().getSelectedView(),getString(R.string.text_pilih_dosen_dahulu));
                } else if (dosen_2.equalsIgnoreCase("-")) {
                    setError((TextView) mediator.getSPDosen2().getSelectedView(),getString(R.string.text_pilih_dosen_dahulu));
                } else if (dosen_2.equals(dosen_1)) {
                    setError((TextView) mediator.getSPDosen1().getSelectedView(),getString(R.string.text_tidak_boleh_sama));
                    setError((TextView) mediator.getSPDosen2().getSelectedView(),getString(R.string.text_tidak_boleh_sama));
                }else {
                    plottingPresenter.createPlotting(dosen_1, dosen_2);
                    Log.e("TAG", "onClick: Dosen 1:"+dosen_1+"; Dosen 2: "+dosen_2 );
                }
            }
        });

    }

    private void setError(TextView textView,String message){
        textView.setError("");
        textView.setTextColor(Color.RED);
        textView.setText(message);
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

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onGetListDosen(List<Dosen> dosen) {
        arrayList.add("Pilih dosen disini");
        usernameList.add("-");
        for (int i = 0; i < dosen.size(); i++) {
            usernameList.add(dosen.get(i).getUsername());
            arrayList.add(dosen.get(i).getDsn_nama());
        }
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,arrayList);
        mediator.getSPDosen1().setAdapter(spinnerAdapter);
        mediator.getSPDosen2().setAdapter(spinnerAdapter);
        mediator.getSPDosen1().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                dosen_1 = usernameList.get(adapterView.getSelectedItemPosition());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        mediator.getSPDosen2().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                dosen_2 = usernameList.get(adapterView.getSelectedItemPosition());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void isEmptyListDosen() {

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
