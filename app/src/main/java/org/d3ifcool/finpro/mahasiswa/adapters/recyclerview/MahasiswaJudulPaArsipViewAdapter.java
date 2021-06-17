package org.d3ifcool.finpro.mahasiswa.adapters.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.mahasiswa.activities.detail.MahasiswaJudulPaArsipDetailActivity;
import org.d3ifcool.finpro.core.models.Judul;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MahasiswaJudulPaArsipViewAdapter extends RecyclerView.Adapter<MahasiswaJudulPaArsipViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Judul> judul;

    public MahasiswaJudulPaArsipViewAdapter(Context context) {
        this.context = context;
    }

    public void additem(ArrayList<Judul> juduls){
        this.judul = juduls;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView judulpa, kategori;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            judulpa = itemView.findViewById(R.id.ctn_all_mahasiswa_textview_nama);
            kategori = itemView.findViewById(R.id.ctn_all_mahasiswa_textview_judul);
        }
    }

    @NonNull
    @Override
    public MahasiswaJudulPaArsipViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.content_list_all_mahasiswa_judul, parent, false);
        return new MahasiswaJudulPaArsipViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MahasiswaJudulPaArsipViewAdapter.ViewHolder holder, final int position) {
        holder.judulpa.setText(judul.get(position).getJudul());
        holder.kategori.setText(judul.get(position).getKategori_nama());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MahasiswaJudulPaArsipDetailActivity.class);
                Judul parcelJudul = judul.get(position);
                intent.putExtra(MahasiswaJudulPaArsipDetailActivity.EXTRA_JUDUL, parcelJudul);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return judul.size();
    }
}