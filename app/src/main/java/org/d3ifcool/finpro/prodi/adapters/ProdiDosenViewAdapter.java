package org.d3ifcool.finpro.prodi.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.d3ifcool.finpro.core.models.Dosen;
import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.prodi.activities.detail.ProdiDosenDetailActivity;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

import static org.d3ifcool.finpro.core.api.ApiUrl.FinproUrl.URL_FOTO_DOSEN;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.EXTRA_DOSEN;

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
public class ProdiDosenViewAdapter extends RecyclerView.Adapter<ProdiDosenViewAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Dosen> dosens;

    public ProdiDosenViewAdapter(Context context) {
        this.context = context;
    }


    public void addItem(ArrayList<Dosen> dosens) {
        this.dosens = dosens;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.content_list_koor_dosen, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.nama_dosen.setText(dosens.get(position).getDsn_nama());
        holder.nip_dosen.setText(dosens.get(position).getDsn_nip());
//        holder.bimbing.setText("6");
        Picasso.get().load(URL_FOTO_DOSEN+dosens.get(position).getDsn_foto()).into(holder.foto);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProdiDosenDetailActivity.class);
                Dosen parcelDosen = dosens.get(position);
                intent.putExtra(EXTRA_DOSEN, parcelDosen);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dosens.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView nama_dosen, nip_dosen, bimbing;
        CircleImageView foto;
        public ViewHolder(View itemView) {
            super(itemView);
            nama_dosen = itemView.findViewById(R.id.ctn_koor_textview_nama_dosen);
            nip_dosen = itemView.findViewById(R.id.ctn_koor_dosen_nip);
            foto = itemView.findViewById(R.id.ctn_koor_dosen_circle_image);
//            bimbing = itemView.findViewById(R.id.ctn_koor_dosen_bimbing);

        }
    }
}
