package org.d3ifcool.finpro.core.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import org.d3ifcool.finpro.R;
import java.util.ArrayList;

public class KegiatanAdapter extends RecyclerView.Adapter<KegiatanAdapter.ViewHolder> {
    private Context context;
    private ArrayList<String> data;

    public KegiatanAdapter(Context context) {
        this.context = context;
    }


    public void setDosens(ArrayList<String> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.content_list_all_spinner, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.nama_kegiatan.setText(data.get(position));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView nama_kegiatan;

        public ViewHolder(View itemView) {
            super(itemView);
            nama_kegiatan = itemView.findViewById(R.id.list_item);
        }
    }
}
