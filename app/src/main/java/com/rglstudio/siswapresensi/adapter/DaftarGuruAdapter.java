package com.rglstudio.siswapresensi.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rglstudio.siswapresensi.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DaftarGuruAdapter extends RecyclerView.Adapter<DaftarGuruAdapter.Holder> {
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_item_guru, viewGroup, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        holder.name.setText("Budiman");
        holder.telp.setText("080000000 | bantul");
        holder.mapel.setText("Matematika");
    }

    @Override
    public int getItemCount() {
        return 50;
    }

    public class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.nameGuru)
        TextView name;
        @BindView(R.id.telpGuru)
        TextView telp;
        @BindView(R.id.mapelGuru)
        TextView mapel;

        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
