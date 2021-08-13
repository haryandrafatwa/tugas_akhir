package org.d3ifcool.finpro.dosen.activities.editor;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.Message;
import org.d3ifcool.finpro.core.interfaces.SidangContract;
import org.d3ifcool.finpro.core.mediators.Mediator;
import org.d3ifcool.finpro.core.mediators.ConcreteMediator;
import org.d3ifcool.finpro.core.models.Mahasiswa;
import org.d3ifcool.finpro.core.models.Sidang;
import org.d3ifcool.finpro.databinding.ActivityReviewSidangEditorBinding;

import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.ResponseBody;

import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.EXTRA_DEFAULT;

public class ReviewSidangEditorActivity extends AppCompatActivity implements SidangContract.ViewModel {

    private ActivityReviewSidangEditorBinding mBinding;
    private Message message = new Message();
    private Mediator mediator;
    private String status_sidang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_review_sidang_editor);
        mBinding.setLifecycleOwner(this);
        mediator = new ConcreteMediator(this);
        mediator.setSidangPresenter(this);
        mBinding.setPresenter(mediator.getSidangPresenter());

        mediator.setTitleContextWithHomeAsUp("Review Sidang");

        mediator.message(message.setComponent("ProgressDialog").setEvent("set"));
        mediator.message(message.setComponent("SessionManager").setEvent("set"));

        mediator.message(message.setComponent("SidangPresenter").setEvent("getSidangByNIM").setText(getIntent().getStringExtra(EXTRA_DEFAULT)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mediator.message(message.setComponent("Toolbar").setVisibility(item.getItemId()));
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onGetObjectSidang(Sidang sidang) {
        message.setSidang(sidang);
        mediator.message(message.setComponent("SidangPresenter").setEvent("showData").setText("review_sidang"));
        ArrayList<String> spinnerList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.array_status_sidang)));
        if(sidang.getNilai_total().equalsIgnoreCase("D") || sidang.getNilai_total().equalsIgnoreCase("E")){
            spinnerList.remove(0);
            spinnerList.remove(1);
        }else{
            spinnerList.remove(2);
        }
        mediator.setSpinner(mBinding.spinnerStatusSidang);
        mediator.message(message.setComponent("Spinner").setEvent("setAdapter").setItem(spinnerList));
        mBinding.spinnerStatusSidang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                status_sidang = spinnerList.get(parent.getSelectedItemPosition()).toLowerCase();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onGetObjectMahasiswa(Mahasiswa mahasiswa) {

    }

    @Override
    public void onGetBody(ResponseBody body, String filename) {

    }

    @Override
    public void onMessage(String messages) {
        switch (messages){
            case "ShowProgressDialog":
                mediator.message(message.setComponent("ProgressDialog").setEvent("show"));
                break;
            case "HideProgressDialog":
                mediator.message(message.setComponent("ProgressDialog").setEvent("dismiss"));
                break;
            case "onSuccess":
                mediator.message(message.setComponent("Toasty").setEvent("Success").setText("Berhasil menyimpan data review"));
                finish();
                break;
            case "saveReview":
                mediator.message(message.setComponent("SidangPresenter").setEvent("saveReview").setText(getIntent().getStringExtra(EXTRA_DEFAULT)).setUrl(status_sidang));
                break;
            default:
                mediator.message(message.setComponent("Toasty").setEvent("Warning").setText(messages));
                break;
        }
    }
}
