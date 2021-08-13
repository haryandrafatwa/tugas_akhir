package org.d3ifcool.finpro.dosen.adapters.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.models.Mahasiswa;
import org.d3ifcool.finpro.dosen.activities.DosenSidangActivity;
import org.d3ifcool.finpro.dosen.activities.DosenTugasAkhirActivity;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import es.dmoral.toasty.Toasty;

import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.EXTRA_MAHASISWA;

public class DosenMahasiswaBimbinganViewAdapter extends RecyclerView.Adapter<DosenMahasiswaBimbinganViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Mahasiswa> data;
    private String pengguna;

    public DosenMahasiswaBimbinganViewAdapter(Context context) {
        this.context = context;
    }

    public void addItem(ArrayList<Mahasiswa> data){
        this.data = data;
        notifyDataSetChanged();
    }

    public void setPengguna(String pengguna) {
        this.pengguna = pengguna;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView judul, kategori, kelompok;

        public ViewHolder(View itemView) {
            super(itemView);
            // -------------------------------------------------------------------------------------
            judul = itemView.findViewById(R.id.ctn_all_mahasiswa_textview_nama);
            kategori = itemView.findViewById(R.id.ctn_all_mahasiswa_textview_judul);
            // -------------------------------------------------------------------------------------
        }
    }

    @Override
    public void onBindViewHolder(final DosenMahasiswaBimbinganViewAdapter.ViewHolder holder, final int position) {
        holder.judul.setText(data.get(position).getMhs_nama());
        holder.kategori.setText(data.get(position).getJudul());
        if (pengguna.equalsIgnoreCase("pembimbing")){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentData = new Intent(context, DosenTugasAkhirActivity.class);
                    intentData.putExtra(EXTRA_MAHASISWA, data.get(position).getMhs_nim());
                    context.startActivity(intentData);
                }
            });
        }else{
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentData = new Intent(context, DosenSidangActivity.class);
                    intentData.putExtra(EXTRA_MAHASISWA, data.get(position).getMhs_nim());
                    context.startActivity(intentData);
                }
            });
        }
    }

    @NonNull
    @Override
    public DosenMahasiswaBimbinganViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_list_all_mahasiswa_judul, parent, false);
        return new DosenMahasiswaBimbinganViewAdapter.ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
