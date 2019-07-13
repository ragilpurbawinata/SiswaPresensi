package com.rglstudio.siswapresensi.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rglstudio.siswapresensi.R;
import com.rglstudio.siswapresensi.model.DataNilai;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NilaiAdapter extends RecyclerView.Adapter<NilaiAdapter.Holder> {
    private List<DataNilai> list;

    public NilaiAdapter(List<DataNilai> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_item_nilai, viewGroup, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        DataNilai dataNilai = list.get(i);

        holder.mapelName.setText(dataNilai.getNamaMapel());
        holder.nilai.setText(dataNilai.getNilai());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.mapelName)
        TextView mapelName;
        @BindView(R.id.mapelNilai)
        TextView nilai;

        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
