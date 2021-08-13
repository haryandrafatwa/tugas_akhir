package org.d3ifcool.finpro.prodi.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.models.Plotting;
import org.d3ifcool.finpro.core.presenters.MahasiswaPresenter;

import java.util.ArrayList;

public class ProdiPlotPembimbingViewAdapter extends RecyclerView.Adapter<ProdiPlotPembimbingViewAdapter.ViewHolder> {

    private ArrayList<Plotting> data;
    private Context context;
    private String mhs_nim;
    private String token;
    private MahasiswaPresenter mahasiswaPresenter;

    public ProdiPlotPembimbingViewAdapter(Context context) {
        this.context = context;
    }

    public void addItem(ArrayList<Plotting> data){
        this.data = data;
        notifyDataSetChanged();
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setMhs_nim(String mhs_nim) {
        this.mhs_nim = mhs_nim;
    }

    public void setMahasiswaPresenter(MahasiswaPresenter mahasiswaPresenter) {
        this.mahasiswaPresenter = mahasiswaPresenter;
    }

    @Override
    public ProdiPlotPembimbingViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_list_koor_plotting, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ProdiPlotPembimbingViewAdapter.ViewHolder holder, final int position) {

        holder.tv_indexing.setText((position+1)+"");
        holder.tv_nama_dosen_1.setText(data.get(position).getNama_dosen_1());
        holder.tv_nama_dosen_2.setText(data.get(position).getNama_dosen_2());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog
                        .Builder(context)
                        .setTitle(context.getResources().getString(R.string.title_tambah_pembimbing))
                        .setMessage(context.getResources().getString(R.string.dialog_tambah_pembimbing))

                        .setPositiveButton(R.string.iya, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                mahasiswaPresenter.addPembimbing(token,mhs_nim,data.get(position).getId());
                            }
                        })
                        .setNegativeButton(R.string.tidak, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
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
