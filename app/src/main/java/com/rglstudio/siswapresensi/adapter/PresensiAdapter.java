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

public class PresensiAdapter extends RecyclerView.Adapter<PresensiAdapter.Holder> {
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_item_presensi, viewGroup, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        holder.date.setText("20 Juli 2019");
        holder.sesi.setText("Sesi : Pagi");
        holder.status.setText("Hadir");
    }

    @Override
    public int getItemCount() {
        return 50;
    }

    public class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.date)
        TextView date;
        @BindView(R.id.status)
        TextView status;
        @BindView(R.id.sesi)
        TextView sesi;

        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
