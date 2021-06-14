package org.d3ifcool.finpro.prodi.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.d3ifcool.finpro.core.models.Mahasiswa;
import org.d3ifcool.finpro.prodi.activities.detail.ProdiMahasiswaDetailActivity;
import org.d3ifcool.finpro.R;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

import static org.d3ifcool.finpro.core.api.ApiUrl.FinproUrl.URL_FOTO_MAHASISWA;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.EXTRA_MAHASISWA;

public class ProdiMahasiswaViewAdapter extends RecyclerView.Adapter<ProdiMahasiswaViewAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Mahasiswa> mMahasiswa;

    public ProdiMahasiswaViewAdapter(Context mContext) {
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
        String ADA = "Sudah mendapatkan pembimbing.";
        String TIDAK = "Belum mendapatkan pembimbing.";

        if(mMahasiswa.get(position).getPlot_id() > 0){
            holder.status_judul.setTextColor(mContext.getResources().getColor(R.color.colorBackgroundGreen));
            String tempJudul = VAR_STATUS + ADA;
            holder.status_judul.setText(tempJudul);
        }else{
            holder.status_judul.setTextColor(mContext.getResources().getColor(R.color.colorBackgroundRed));
            String tempJudul = VAR_STATUS + TIDAK;
            holder.status_judul.setText(tempJudul);
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
