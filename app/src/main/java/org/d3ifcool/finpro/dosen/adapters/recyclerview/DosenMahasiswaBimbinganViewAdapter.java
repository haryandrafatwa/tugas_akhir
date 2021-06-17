package org.d3ifcool.finpro.dosen.adapters.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.models.Mahasiswa;
import org.d3ifcool.finpro.dosen.activities.DosenTugasAkhirActivity;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.EXTRA_MAHASISWA;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * Finpro
 * Copyright (C) 28/02/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Line     : bullbee117
 * Phone    : 081357108568
 * Majors   : D3 Teknik Informatika 2016
 * Campus   : Telkom University
 * -----------------------------------------
 * id.amirisback.frogobox
 */
public class DosenMahasiswaBimbinganViewAdapter extends RecyclerView.Adapter<DosenMahasiswaBimbinganViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Mahasiswa> data;

    public DosenMahasiswaBimbinganViewAdapter(Context context) {
        this.context = context;
    }

    public void addItem(ArrayList<Mahasiswa> data){
        this.data = data;
        notifyDataSetChanged();
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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentData = new Intent(context, DosenTugasAkhirActivity.class);
                intentData.putExtra(EXTRA_MAHASISWA, data.get(position).getMhs_nim());
                context.startActivity(intentData);
            }
        });
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
