package org.d3ifcool.finpro.prodi.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.models.Mahasiswa;
import org.d3ifcool.finpro.prodi.activities.detail.ProdiMahasiswaDetailActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

import static org.d3ifcool.finpro.core.api.ApiUrl.FinproUrl.URL_FOTO_MAHASISWA;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.EXTRA_MAHASISWA;

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
public class ProdiSKTAViewAdapter extends RecyclerView.Adapter<ProdiSKTAViewAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Mahasiswa> mMahasiswa;
    private int layoutType;

    public ProdiSKTAViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setmMahasiswa(ArrayList<Mahasiswa> mMahasiswa) {
        this.mMahasiswa = mMahasiswa;
        notifyDataSetChanged();
    }

    public void setLayoutType(int layoutType) {
        this.layoutType = layoutType;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(layoutType,parent,false);
        return new ViewHolder(view);
    }


    @Override
    public int getItemCount() {
        return mMahasiswa.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView nama_mhs, nim_mhs, status_judul;
        CircleImageView foto;

        public ViewHolder(View itemView) {
            super(itemView);
            nama_mhs = itemView.findViewById(R.id.ctn_koor_textview_nama_mahasiswa);
            nim_mhs = itemView.findViewById(R.id.ctn_koor_mahasiswa_nim);
            status_judul = itemView.findViewById(R.id.ctn_koor_status_judul);
            foto = itemView.findViewById(R.id.ctn_koor_mahasiswa_circle_image);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        String VAR_STATUS = "Status: ";
        String PERPANJANG = "Sedang mengajukan perpanjangan.";
        String PASIF = "SK Belum diterbitkan.";
        String OVER = "Kadaluwarsa (";
        String tempJudul;

        try {
            Locale locale = new Locale("in", "ID");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", locale);
            if(mMahasiswa.get(position).getSk_status() == 3){
                Date date = format.parse(mMahasiswa.get(position).getSk_expired());
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                String month_name = new SimpleDateFormat("MMMM", locale).format(calendar.getTime());
                tempJudul = VAR_STATUS + OVER + calendar.get(Calendar.DATE)+" "+month_name+" "+calendar.get(Calendar.YEAR) +")";
                holder.status_judul.setTextColor(mContext.getResources().getColor(R.color.colorBackgroundRed));
            }else if(mMahasiswa.get(position).getSk_status() == 4){
                tempJudul = VAR_STATUS + PERPANJANG;
                holder.status_judul.setTextColor(Color.GRAY);
            }else{
                tempJudul = VAR_STATUS + PASIF;
                holder.status_judul.setTextColor(mContext.getResources().getColor(R.color.colorBackgroundRed));
            }
            holder.status_judul.setText(tempJudul);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.nama_mhs.setText(mMahasiswa.get(position).getMhs_nama());
        holder.nim_mhs.setText(mMahasiswa.get(position).getMhs_nim());
        Picasso.get().load(URL_FOTO_MAHASISWA + mMahasiswa.get(position).getMhs_foto()).into(holder.foto);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ProdiMahasiswaDetailActivity.class);
                Mahasiswa parcelMahasiswa = mMahasiswa.get(position);
                intent.putExtra(EXTRA_MAHASISWA, parcelMahasiswa);
                mContext.startActivity(intent);
            }
        });

    }

}
