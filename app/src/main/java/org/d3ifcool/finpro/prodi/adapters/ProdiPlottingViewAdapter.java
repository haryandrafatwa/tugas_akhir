package org.d3ifcool.finpro.prodi.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.models.Plotting;
import org.d3ifcool.finpro.core.presenters.PlottingPresenter;
import org.d3ifcool.finpro.prodi.activities.editor.ProdiPlottingEditorActivity;

import java.util.ArrayList;

public class ProdiPlottingViewAdapter extends RecyclerView.Adapter<ProdiPlottingViewAdapter.ViewHolder> {

    private ArrayList<Plotting> data;
    private Context context;
    private int layoutType;
    private PlottingPresenter plottingPresenter;
    private String token;

    public ProdiPlottingViewAdapter(Context context) {
        this.context = context;
    }

    public void addItem(ArrayList<Plotting> data){
        this.data = data;
        notifyDataSetChanged();
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setPlottingPresenter(PlottingPresenter plottingPresenter) {
        this.plottingPresenter = plottingPresenter;
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

        Plotting model = data.get(position);

        holder.tv_indexing.setText((position+1)+"");
        holder.tv_nama_dosen_1.setText(model.getNama_pembimbing_1());
        holder.tv_nama_dosen_2.setText(model.getNama_pembimbing_2());
        holder.linearLayout.setVisibility(View.VISIBLE);
        holder.ib_hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.layout_hapus.performClick();
            }
        });
        holder.layout_hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plottingPresenter.deletePlotting(token,model.getId());
            }
        });
        holder.ib_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.layout_edit.performClick();
            }
        });
        holder.layout_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProdiPlottingEditorActivity.class);
                intent.putExtra("PlottingModel",model);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv_indexing, tv_nama_dosen_1, tv_nama_dosen_2;
        LinearLayout linearLayout, layout_hapus, layout_edit;
        ImageButton ib_hapus,ib_edit;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_indexing = itemView.findViewById(R.id.tv_indexing);
            tv_nama_dosen_1 = itemView.findViewById(R.id.ctn_koor_textview_nama_dosen_1);
            tv_nama_dosen_2 = itemView.findViewById(R.id.ctn_koor_textview_nama_dosen_2);
            linearLayout = itemView.findViewById(R.id.layout_action);
            layout_hapus = itemView.findViewById(R.id.layout_hapus);
            layout_edit = itemView.findViewById(R.id.layout_edit);
            ib_hapus = itemView.findViewById(R.id.ib_tolak);
            ib_edit = itemView.findViewById(R.id.ib_terima);
        }
    }
}
