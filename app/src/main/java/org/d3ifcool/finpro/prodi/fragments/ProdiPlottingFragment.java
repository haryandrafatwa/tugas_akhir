package org.d3ifcool.finpro.prodi.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.Message;
import org.d3ifcool.finpro.core.interfaces.PlottingContract;
import org.d3ifcool.finpro.core.mediators.interfaces.prodi.Mediator;
import org.d3ifcool.finpro.core.mediators.prodi.ConcreteMediator;
import org.d3ifcool.finpro.core.models.Plotting;
import org.d3ifcool.finpro.databinding.FragmentKoorPlottingBinding;
import org.d3ifcool.finpro.prodi.activities.editor.ProdiPlottingEditorActivity;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;

import static android.app.Activity.RESULT_OK;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.PICK_EXCEL_REQUEST;

public class ProdiPlottingFragment extends Fragment implements PlottingContract.ViewModel {

    private Message message = new Message();
    private Mediator mediator;
    private boolean status;
    private FragmentKoorPlottingBinding mBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_koor_plotting,container,false);
        mediator = new ConcreteMediator((AppCompatActivity) getActivity());

        mediator.setPlottingPresenter(this);
        mBinding.setPresenter(mediator.getPlottingPresenter());
        mediator.message(message.setComponent("ProgressDialog").setEvent("set"));
        mediator.message(message.setComponent("SessionManager").setEvent("set"));
        mediator.message(message.setComponent("FileHelper").setEvent("set"));
        mediator.message(message.setComponent("ProdiPlottingAdapter").setEvent("set"));
        mediator.setRecyclerView(mBinding.frgKoorDosenHomeRecyclerview);
        mediator.setRelativeLayout(mBinding.includeLayout.viewEmptyview);
        mediator.setRefreshLayout(mBinding.refresh);
        mBinding.setToken(mediator.getSessionToken());

        mediator.message(message.setComponent("PlottingPresenter").setEvent("getAllData"));

        return mBinding.getRoot();
    }

    private void intentPickFile(){
        if (mediator.getPermissionFile()){;
            startActivityForResult(Intent.createChooser(mediator.findFileIntent("XLS"), "Pilih file"), PICK_EXCEL_REQUEST);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_EXCEL_REQUEST && resultCode == RESULT_OK) {
            mediator.message(message.setComponent("PlottingPresenter").setEvent("uploadForm").setUri(data.getData()));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mediator.message(message.setComponent("PlottingPresenter").setEvent("getAllData"));
    }

    @Override
    public void onGetListPlotting(List<Plotting> plotting) {
        ArrayList<Plotting> arrayList = new ArrayList<>();
        arrayList.addAll(plotting);
        mediator.message(message.setComponent("ProdiPlottingAdapter").setEvent("addItem").setItem(arrayList));
        mediator.message(message.setComponent("ProdiPlottingAdapter").setEvent("setAdapter"));
        mediator.message(message.setComponent("RefreshLayout").setEvent("setRefreshing").setEnabled(false));

        if (arrayList.size() == 0) {
            mediator.message(message.setComponent("RelativeLayout").setEvent("setVisibility").setVisibility(View.VISIBLE));
        } else {
            mediator.message(message.setComponent("RelativeLayout").setEvent("setVisibility").setVisibility(View.GONE));
        }
        mediator.message(message.setComponent("PlottingPresenter").setEvent("checkForm"));
    }

    @Override
    public void onGetBody(ResponseBody body, String filename) {
        mediator.message(message.setComponent("FileHelper").setEvent("openFileXLS").setResponseBody(body).setText(filename));
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
            case "File ditemukan.":
                this.status = true;

                mediator.setTextView(mBinding.tvHapus);
                mediator.message(message.setComponent("TextView").setEvent("setVisibility").setVisibility(View.VISIBLE));

                mediator.setTextView(mBinding.tvStatus);
                mediator.message(message.setComponent("TextView").setEvent("setText").setText("Status: Telah Diunggah"));
                mediator.message(message.setComponent("TextView").setEvent("setTextColor").setColor("#ff4CAF50"));

                mediator.setTextView(mBinding.tvDownload);
                mediator.message(message.setComponent("TextView").setEvent("setTextColor").setColor("#ffFF9800"));
                mediator.message(message.setComponent("TextView").setEvent("setEnabled").setEnabled(true));
                break;
            case "File tidak ditemukan.":
                this.status = false;

                mediator.setTextView(mBinding.tvHapus);
                mediator.message(message.setComponent("TextView").setEvent("setVisibility").setVisibility(View.GONE));

                mediator.setTextView(mBinding.tvStatus);
                mediator.message(message.setComponent("TextView").setEvent("setText").setText("Status: Belum Diunggah"));
                mediator.message(message.setComponent("TextView").setEvent("setTextColor").setColor("#ffFF0000"));

                mediator.setTextView(mBinding.tvDownload);
                mediator.message(message.setComponent("TextView").setEvent("setTextColor").setColor("#ffA3A3A3"));
                mediator.message(message.setComponent("TextView").setEvent("setEnabled").setEnabled(false));
                break;
            case "DeleteFormPlot":
                mediator.message(message.setComponent("AlertDialog").setEvent("set"));
                mediator.message(message.setComponent("AlertDialog").setEvent("hapusForm"));
                break;
            case "AddButton":
                mediator.selectIntent(new Message().setaClass(ProdiPlottingEditorActivity.class));
                break;
            case "EmptyList":
                mediator.message(message.setComponent("RelativeLayout").setEvent("setVisibility").setVisibility(View.VISIBLE));
                break;
            case "UploadFormPlot":
                if (status){
                    mediator.message(message.setComponent("AlertDialog").setEvent("set"));
                    mediator.message(message.setComponent("AlertDialog").setEvent("uploadForm"));
                }else{
                    onMessage("ShowProgressDialog");
                    intentPickFile();
                }
                break;
            case "onIntent":
                intentPickFile();
                break;
            case "onSuccess":
                mediator.message(message.setComponent("Handler").setEvent("onSuccess").setText(messages));
                break;
            default:
                mediator.message(message.setComponent("Toasty").setEvent("Warning").setText(messages));
                break;
        }
    }
}
