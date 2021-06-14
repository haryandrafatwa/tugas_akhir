package org.d3ifcool.finpro.prodi.activities.editor;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.Constant;
import org.d3ifcool.finpro.core.helpers.Message;
import org.d3ifcool.finpro.core.interfaces.DosenContract;
import org.d3ifcool.finpro.core.mediators.prodi.ConcreteMediator;
import org.d3ifcool.finpro.core.models.Dosen;
import org.d3ifcool.finpro.databinding.ActivityKoorDosenEditorBinding;

import java.util.List;

public class ProdiDosenEditorActivity extends AppCompatActivity implements DosenContract.ViewModel {

    private ActivityKoorDosenEditorBinding mBinding;
    private Message message = new Message();
    private ConcreteMediator mediator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_koor_dosen_editor);
        mBinding.setLifecycleOwner(this);
        mediator = new ConcreteMediator(this);
        mediator.setDosenPresenter(this);
        mBinding.setPresenter(mediator.getDosenPresenter());

        setTitle(getString(R.string.title_tambah_dosen));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (getIntent().getParcelableExtra(Constant.ObjectConstanta.EXTRA_DOSEN)!=null){
            setTitle(getString(R.string.button_click_ubah_dosen));
            message.setDosen(getIntent().getParcelableExtra(Constant.ObjectConstanta.EXTRA_DOSEN));
            mediator.setTextView(mBinding.actKoorDosenButtonSimpan);
            mediator.setLinearLayout(mBinding.layoutAdditional);
            mediator.message(message.setComponent("LinearLayout").setEvent("setVisibility").setVisibility(View.VISIBLE));
            mediator.message(message.setComponent("TextView").setEvent("setText").setText("Update Dosen"));
            mediator.message(message.setComponent("DosenPresenter").setEvent("showData"));
            mediator.setTextView(mBinding.actKoorEdittextNipdosen);
            mediator.message(message.setComponent("TextView").setEvent("setEnabled").setEnabled(false));
            message.setEnabled(true);
        }
        mediator.message(message.setComponent("SessionManager").setEvent("set"));
        mediator.message(message.setComponent("ProgressDialog").setEvent("set"));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        mediator.message(message.setComponent("Toolbar").setVisibility(i));
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onGetObjectDosen(Dosen dosen) {

    }

    @Override
    public void onGetListDosen(List<Dosen> dosenList) {

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
            case"onSuccess":
                finish();
                break;
            case "AddButton":
                if (message.isEnabled()){
                    mediator.message(message.setComponent("DosenPresenter").setEvent("updateDosen"));
                }else{
                    mediator.message(message.setComponent("DosenPresenter").setEvent("createDosen"));
                }
                break;
            default:
                mediator.message(message.setComponent("Toasty").setEvent("Warning").setText(messages));
                break;
        }
    }
}
