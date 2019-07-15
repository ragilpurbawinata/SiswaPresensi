package com.rglstudio.siswapresensi.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rglstudio.siswapresensi.R;
import com.rglstudio.siswapresensi.model.DataPresensi;
import com.rglstudio.siswapresensi.util.DateFormatUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PresensiAdapter extends RecyclerView.Adapter<PresensiAdapter.Holder> {
    private List<DataPresensi> list;

    public List<DataPresensi> getList() {
        return list;
    }

    public void setList(List<DataPresensi> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_item_presensi, viewGroup, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        DataPresensi dataPresensi = list.get(i);

        holder.date.setText(DateFormatUtil.formatDisplay(dataPresensi.getTanggal(), "yyyy-MM-dd",
                "E, d MMMM yyyy"));
        holder.sesi.setText("Sesi : "+dataPresensi.getSesi());
        holder.status.setText(dataPresensi.getStatus());

        switch (dataPresensi.getStatus()){
            case "Hadir":
                holder.status.setTextColor(Color.parseColor("#3da330"));
                break;
            case "Izin":
                holder.status.setTextColor(Color.parseColor("#ffc107"));
                break;
            case "Alpha":
                holder.status.setTextColor(Color.parseColor("#e23e3e"));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
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
