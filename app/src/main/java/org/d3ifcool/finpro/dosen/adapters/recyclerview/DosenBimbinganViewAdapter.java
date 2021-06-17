package org.d3ifcool.finpro.dosen.adapters.recyclerview;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.dosen.activities.detail.BimbinganDetailActivity;
import org.d3ifcool.finpro.core.models.Bimbingan;

import java.util.ArrayList;

import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.EXTRA_BIMBINGAN;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.STATUS_BIMBINGAN_PENDING;

public class DosenBimbinganViewAdapter extends RecyclerView.Adapter<DosenBimbinganViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Bimbingan> dataBimbingan;

    public DosenBimbinganViewAdapter(Context context) {
        this.context = context;
    }

    public void addItem(ArrayList<Bimbingan> dataBimbingan){
        this.dataBimbingan = dataBimbingan;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView infoIsi, infoTanggal, status;
        LinearLayout container;

        public ViewHolder(View itemView) {
            super(itemView);
            // -------------------------------------------------------------------------------------
            infoIsi = itemView.findViewById(R.id.ctn_mhs_pa_bimbingan_textview_isi);
            infoTanggal = itemView.findViewById(R.id.ctn_mhs_pa_bimbingan_textview_tanggal);
            status = itemView.findViewById(R.id.ctn_mhs_pa_bimbingan_textview_status);
            container = itemView.findViewById(R.id.layout_editor);
            // -------------------------------------------------------------------------------------
        }
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Bimbingan bimbingan = dataBimbingan.get(position);
        if (dataBimbingan.get(position).getBimbingan_status().equals(STATUS_BIMBINGAN_PENDING)){
            holder.status.setText("Status: Belum Approve");
            holder.status.setTextColor(context.getResources().getColor(R.color.colorAccent));
        } else if (bimbingan.getBimbingan_status().equalsIgnoreCase("approve")){
            holder.status.setText("Status: Sudah Approve");
            holder.status.setTextColor(context.getResources().getColor(R.color.colorTextGreen));
        }else{
            holder.status.setText("Status: Ditolak");
            holder.status.setTextColor(context.getResources().getColor(R.color.colorTextRed));
        }
        holder.container.setVisibility(View.GONE);
        holder.infoTanggal.setText(bimbingan.tglBimbingan());
        holder.infoIsi.setText(bimbingan.getBimbingan_review());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentData = new Intent(context, BimbinganDetailActivity.class);
                intentData.putExtra(EXTRA_BIMBINGAN, bimbingan);
                context.startActivity(intentData);
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
        return dataBimbingan.size();
    }

}