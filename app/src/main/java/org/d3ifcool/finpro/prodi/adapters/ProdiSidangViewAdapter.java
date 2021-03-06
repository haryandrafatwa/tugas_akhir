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
import org.d3ifcool.finpro.dosen.activities.detail.SidangDetailActivity;
import org.d3ifcool.finpro.prodi.activities.detail.ProdiMahasiswaDetailActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

import static org.d3ifcool.finpro.core.api.ApiUrl.FinproUrl.URL_FOTO_MAHASISWA;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.EXTRA_DEFAULT;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.EXTRA_MAHASISWA;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.STATUS_SIDANG_LULUS;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.STATUS_SIDANG_LULUS_BERSYARAT;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.STATUS_SIDANG_TIDAK_LULUS;

public class ProdiSidangViewAdapter extends RecyclerView.Adapter<ProdiSidangViewAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Mahasiswa> mMahasiswa;

    public ProdiSidangViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void addItem(ArrayList<Mahasiswa> mMahasiswa) {
        this.mMahasiswa = mMahasiswa;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.content_list_koor_mahasiswa,parent,false);
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
        String tempJudul;

        try {
            Locale locale = new Locale("in", "ID");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", locale);
            if(mMahasiswa.get(position).getSidang_status().equalsIgnoreCase("terjadwalkan")){
                Date date = format.parse(mMahasiswa.get(position).getSidang_tanggal());
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                String month_name = new SimpleDateFormat("MMMM", locale).format(calendar.getTime());
                tempJudul = VAR_STATUS + "Sidang pada " + calendar.get(Calendar.DATE)+" "+month_name+" "+calendar.get(Calendar.YEAR);
                holder.status_judul.setTextColor(mContext.getResources().getColor(R.color.colorSecondary));
            }else if(mMahasiswa.get(position).getSidang_status().equalsIgnoreCase("revisi")){
                tempJudul = VAR_STATUS + STATUS_SIDANG_LULUS_BERSYARAT;
                holder.status_judul.setTextColor(Color.GRAY);
            }else if(mMahasiswa.get(position).getSidang_status().equalsIgnoreCase("tidak lulus")){
                tempJudul = VAR_STATUS + STATUS_SIDANG_TIDAK_LULUS;
                holder.status_judul.setTextColor(mContext.getResources().getColor(R.color.colorBackgroundRed));
            }else{
                tempJudul = VAR_STATUS + STATUS_SIDANG_LULUS;
                holder.status_judul.setTextColor(mContext.getResources().getColor(R.color.colorTextGreen));
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
                Intent intent = new Intent(mContext, SidangDetailActivity.class);
                Mahasiswa parcelMahasiswa = mMahasiswa.get(position);
                intent.putExtra(EXTRA_DEFAULT, parcelMahasiswa.getMhs_nim());
                mContext.startActivity(intent);
            }
        });

    }

}
