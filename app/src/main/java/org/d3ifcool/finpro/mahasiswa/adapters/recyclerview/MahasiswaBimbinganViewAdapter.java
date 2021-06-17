package org.d3ifcool.finpro.mahasiswa.adapters.recyclerview;

import android.app.AlertDialog;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.presenters.BimbinganPresenter;
import org.d3ifcool.finpro.dosen.activities.detail.BimbinganDetailActivity;
import org.d3ifcool.finpro.core.models.Bimbingan;
import org.d3ifcool.finpro.mahasiswa.activities.editor.MahasiswaBimbinganEditorActivity;

import java.util.ArrayList;

import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.EXTRA_BIMBINGAN;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.STATUS_BIMBINGAN_PENDING;

public class MahasiswaBimbinganViewAdapter extends RecyclerView.Adapter<MahasiswaBimbinganViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Bimbingan> data;
    private String token;
    private BimbinganPresenter bimbinganPresenter;

    public MahasiswaBimbinganViewAdapter(Context context) {
        this.context = context;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void addItem(ArrayList<Bimbingan> data){
        this.data = data;
        notifyDataSetChanged();
    }

    public void setBimbinganPresenter(BimbinganPresenter bimbinganPresenter) {
        this.bimbinganPresenter = bimbinganPresenter;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView infoIsi, infoTanggal, status, edit, hapus;
        LinearLayout container, editor;

        public ViewHolder(View itemView) {
            super(itemView);
            infoIsi = itemView.findViewById(R.id.ctn_mhs_pa_bimbingan_textview_isi);
            infoTanggal = itemView.findViewById(R.id.ctn_mhs_pa_bimbingan_textview_tanggal);
            status = itemView.findViewById(R.id.ctn_mhs_pa_bimbingan_textview_status);
            edit = itemView.findViewById(R.id.tv_edit);
            hapus = itemView.findViewById(R.id.tv_hapus);
            container = itemView.findViewById(R.id.container_bimbingan);
            editor = itemView.findViewById(R.id.layout_editor);
        }
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (data.get(position).getBimbingan_status().equals(STATUS_BIMBINGAN_PENDING)){
            holder.status.setText("Status: Belum Approve");
            holder.editor.setVisibility(View.VISIBLE);
            holder.status.setTextColor(context.getResources().getColor(R.color.colorAccent));
        } else if (data.get(position).getBimbingan_status().equalsIgnoreCase("approve")){
            holder.status.setText("Status: Sudah Approve");
            holder.status.setTextColor(context.getResources().getColor(R.color.colorTextGreen));
            holder.editor.setVisibility(View.GONE);
        }else{
            holder.status.setText("Status: Ditolak");
            holder.status.setTextColor(context.getResources().getColor(R.color.colorTextRed));
            holder.editor.setVisibility(View.GONE);
        }
        holder.infoTanggal.setText(data.get(position).tglBimbingan());
        holder.infoIsi.setText(data.get(position).getBimbingan_review());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentData = new Intent(context, BimbinganDetailActivity.class);
                Bimbingan parcelBimbingan = data.get(position);
                intentData.putExtra(EXTRA_BIMBINGAN, parcelBimbingan);
                context.startActivity(intentData);
            }
        });
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentData = new Intent(context, MahasiswaBimbinganEditorActivity.class);
                Bimbingan parcelBimbingan = data.get(position);
                intentData.putExtra(EXTRA_BIMBINGAN, parcelBimbingan);
                context.startActivity(intentData);
            }
        });
        holder.hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog
                        .Builder(context)
                        .setTitle(context.getResources().getString(R.string.dialog_hapus_title))
                        .setMessage(context.getResources().getString(R.string.dialog_hapus_text))
                        .setPositiveButton(R.string.iya, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                bimbinganPresenter.deleteBimbingan(token,data.get(position).getBimbingan_id());
                            }
                        })
                        .setNegativeButton(R.string.tidak, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_list_all_ta_bimbingan, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}