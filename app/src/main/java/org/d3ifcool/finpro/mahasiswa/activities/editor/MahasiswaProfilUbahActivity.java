package org.d3ifcool.finpro.mahasiswa.activities.editor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.Constant;
import org.d3ifcool.finpro.core.helpers.Message;
import org.d3ifcool.finpro.core.helpers.SessionManager;
import org.d3ifcool.finpro.core.interfaces.MahasiswaContract;
import org.d3ifcool.finpro.core.mediators.ConcreteMediator;
import org.d3ifcool.finpro.core.mediators.Mediator;
import org.d3ifcool.finpro.core.models.Mahasiswa;
import org.d3ifcool.finpro.core.models.Plotting;
import org.d3ifcool.finpro.databinding.ActivityDosenProfilUbahBinding;
import org.d3ifcool.finpro.databinding.ActivityMahasiswaProfilUbahBinding;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.ResponseBody;

import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.EMAIL_PATTERN;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.PHONE_PATTERN;
import static org.d3ifcool.finpro.core.api.ApiUrl.FinproUrl.URL_FOTO_MAHASISWA;

public class MahasiswaProfilUbahActivity extends AppCompatActivity implements MahasiswaContract.ViewModel {

    private ActivityMahasiswaProfilUbahBinding mbinding;
    private Message message = new Message();
    private Mediator mediator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mbinding = DataBindingUtil.setContentView(this, R.layout.activity_mahasiswa_profil_ubah);
        mbinding.setLifecycleOwner(this);
        mediator = new ConcreteMediator(this);
        mediator.setMahasiswaPresenter(this);
        mbinding.setPresenter(mediator.getMahasiswaPresenter());

        mediator.setTitleContextWithHomeAsUp("Ubah Profile");

        message.setMahasiswa(getIntent().getParcelableExtra(Constant.ObjectConstanta.EXTRA_MAHASISWA));

        mediator.message(message.setComponent("ProgressDialog").setEvent("set"));
        mediator.message(message.setComponent("SessionManager").setEvent("set"));

        mediator.message(message.setComponent("MahasiswaPresenter").setEvent("showData"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_accept, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mediator.message(message.setComponent("Toolbar").setVisibility(item.getItemId()).setEvent("mahasiswa"));
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
            case "onSuccess":
                mediator.message(message.setComponent("SessionManager").setEvent("updateMahasiswa"));
                mediator.message(message.setComponent("Toasty").setEvent("Success").setText("Update Berhasil!"));
                finish();
                break;
            default:
                mediator.message(message.setComponent("Toasty").setEvent("Warning").setText(messages));
                break;
        }
    }
}