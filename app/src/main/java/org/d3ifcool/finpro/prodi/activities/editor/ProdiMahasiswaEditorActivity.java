package org.d3ifcool.finpro.prodi.activities.editor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.d3ifcool.finpro.core.helpers.Message;
import org.d3ifcool.finpro.core.interfaces.MahasiswaContract;
import org.d3ifcool.finpro.core.mediators.Mediator;
import org.d3ifcool.finpro.core.mediators.ConcreteMediator;
import org.d3ifcool.finpro.core.models.Mahasiswa;
import org.d3ifcool.finpro.core.models.Plotting;
import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.databinding.ActivityProdiMahasiswaEditorBinding;

import java.util.List;

import okhttp3.ResponseBody;

import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.EXTRA_MAHASISWA;

public class ProdiMahasiswaEditorActivity extends AppCompatActivity implements MahasiswaContract.ViewModel {

    private ActivityProdiMahasiswaEditorBinding mBinding;
    private Message message = new Message();
    private Mediator mediator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_prodi_mahasiswa_editor);
        mBinding.setLifecycleOwner(this);
        mediator = new ConcreteMediator(this);
        mediator.setMahasiswaPresenter(this);
        mBinding.setPresenter(mediator.getMahasiswaPresenter());

        setTitle(getString(R.string.title_mahasiswa_tambah));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (getIntent().getParcelableExtra(EXTRA_MAHASISWA)!=null){
            setTitle(getString(R.string.button_click_ubah_mahasiswa));
            message.setMahasiswa(getIntent().getParcelableExtra(EXTRA_MAHASISWA));
            mediator.setTextView(mBinding.actKoorMahasiswaButtonSimpan);
            mediator.setLinearLayout(mBinding.layoutAdditional);
            mediator.message(message.setComponent("LinearLayout").setEvent("setVisibility").setVisibility(View.VISIBLE));
            mediator.message(message.setComponent("TextView").setEvent("setText").setText("Update Mahasiswa"));
            mediator.message(message.setComponent("MahasiswaPresenter").setEvent("showData"));
            mediator.setTextView(mBinding.actKoorEdittextNimMahasiswa);
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
    public void onGetObjectMahasiswa(Mahasiswa mahasiswa) {

    }

    @Override
    public void onGetListMahasiswa(List<Mahasiswa> mahasiswaList) {

    }

    @Override
    public void onSuccessGetPlotting(Plotting plotting) {

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
            case"onSuccess":
                finish();
                break;
            case "AddButton":
                if (message.isEnabled()){
                    mediator.message(message.setComponent("MahasiswaPresenter").setEvent("updateMahasiswa"));
                }else{
                    mediator.message(message.setComponent("MahasiswaPresenter").setEvent("createMahasiswa"));
                }
                break;
            default:
                mediator.message(message.setComponent("Toasty").setEvent("Warning").setText(messages));
                break;
        }
    }
}
