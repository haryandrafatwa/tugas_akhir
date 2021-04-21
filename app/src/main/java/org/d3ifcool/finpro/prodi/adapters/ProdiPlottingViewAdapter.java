package org.d3ifcool.finpro.prodi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.models.Plotting;

import java.util.ArrayList;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * Finpro
 * Copyright (C) 08/03/2019.
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
public class ProdiPlottingViewAdapter extends RecyclerView.Adapter<ProdiPlottingViewAdapter.ViewHolder> {

    private ArrayList<Plotting> data;
    private Context context;
    private int layoutType;

    public ProdiPlottingViewAdapter(Context context) {
        this.context = context;
    }

    public void addItem(ArrayList<Plotting> data){
        this.data = data;
        notifyDataSetChanged();
    }

    public void setLayoutType(int layouyType) {
        this.layoutType = layouyType;
    }

    @Override
    public ProdiPlottingViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_list_koor_plotting, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ProdiPlottingViewAdapter.ViewHolder holder, final int position) {

        holder.tv_indexing.setText((position+1)+"");
        holder.tv_nama_dosen_1.setText(data.get(position).getNama_pembimbing_1());
        holder.tv_nama_dosen_2.setText(data.get(position).getNama_pembimbing_2());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv_indexing, tv_nama_dosen_1, tv_nama_dosen_2;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_indexing = itemView.findViewById(R.id.tv_indexing);
            tv_nama_dosen_1 = itemView.findViewById(R.id.ctn_koor_textview_nama_dosen_1);
            tv_nama_dosen_2 = itemView.findViewById(R.id.ctn_koor_textview_nama_dosen_2);
        }
    }
}
