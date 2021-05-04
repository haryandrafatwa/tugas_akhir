package org.d3ifcool.finpro.prodi.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.d3ifcool.finpro.core.models.Informasi;
import org.d3ifcool.finpro.prodi.activities.detail.ProdiInformasiDetailActivity;
import org.d3ifcool.finpro.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

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
public class ProdiInformasiViewAdapter extends RecyclerView.Adapter<ProdiInformasiViewAdapter.ViewHolder> {

    private ArrayList<Informasi> data;
    private Context context;

    public ProdiInformasiViewAdapter(Context context) {
        this.context = context;
    }

    public void addItem(ArrayList<Informasi> data){
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public ProdiInformasiViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_list_all_informasi, parent, false);
        return new ViewHolder(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(ProdiInformasiViewAdapter.ViewHolder holder, final int position) {

        holder.infoDosen.setText(data.get(position).getPenerbit());
        holder.infoIsi.setText(data.get(position).getInfo_deskripsi());
        holder.infoJudul.setText(data.get(position).getInfo_judul());

        try {
            Date date;
            Locale locale = new Locale("in", "ID");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", locale);
            date = format.parse(data.get(position).getTanggal());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            String month_name = new SimpleDateFormat("MMMM", locale).format(calendar.getTime());
            String curTime = String.format("%02d:%02d", (calendar.get(Calendar.HOUR)+7), calendar.get(Calendar.MINUTE));
            holder.infoTanggal.setText(calendar.get(Calendar.DATE)+" "+month_name+" "+calendar.get(Calendar.YEAR)+" - "+curTime);
            data.get(position).setTanggal(calendar.get(Calendar.DATE)+" "+month_name+" "+calendar.get(Calendar.YEAR)+" - "+curTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        Picasso.get().load(URL_FOTO_DOSEN+sessionManager.getSessionDosenFoto()).into(holder.foto);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProdiInformasiDetailActivity.class);
                Informasi parcelInfo = data.get(position);
                intent.putExtra(ProdiInformasiDetailActivity.EXTRA_INFORMASI, parcelInfo);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView infoJudul, infoIsi, infoTanggal, infoDosen;
        CircleImageView foto;

        public ViewHolder(View itemView) {
            super(itemView);
            foto = itemView.findViewById(R.id.ctn_koor_circle_image);
            foto = itemView.findViewById(R.id.ctn_all_info_photo);
            infoJudul = itemView.findViewById(R.id.ctn_all_info_textview_judul);
            infoIsi = itemView.findViewById(R.id.ctn_all_info_textview_isi);
            infoTanggal = itemView.findViewById(R.id.ctn_all_info_textview_tanggal);
            infoDosen = itemView.findViewById(R.id.ctn_all_info_textview_nama);
        }
    }
}
