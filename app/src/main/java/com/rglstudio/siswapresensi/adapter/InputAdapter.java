package com.rglstudio.siswapresensi.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rglstudio.siswapresensi.R;
import com.rglstudio.siswapresensi.model.DataSiswa;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InputAdapter extends RecyclerView.Adapter<InputAdapter.Holder> {
    private List<DataSiswa> list;
    private SiswaKelasListerner listerner;

    public InputAdapter(List<DataSiswa> list, SiswaKelasListerner listerner) {
        this.list = list;
        this.listerner = listerner;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_item_input, viewGroup, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        final DataSiswa dataSiswa = list.get(i);

        holder.nama.setText(dataSiswa.getNama());

        holder.btInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listerner.onInputClick(dataSiswa);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.siswaName)
        TextView nama;
        @BindView(R.id.inputNilai)
        ImageView btInput;

        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface SiswaKelasListerner{
        void onInputClick(DataSiswa dataSiswa);
    }
}
