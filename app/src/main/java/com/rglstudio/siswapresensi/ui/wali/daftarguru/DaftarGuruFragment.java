package com.rglstudio.siswapresensi.ui.wali.daftarguru;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rglstudio.siswapresensi.R;
import com.rglstudio.siswapresensi.adapter.DaftarGuruAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DaftarGuruFragment extends Fragment {
    @BindView(R.id.rvGuru)
    RecyclerView rvGuru;


    public DaftarGuruFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daftar_guru, container, false);
        ButterKnife.bind(this, view);

        rvGuru.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvGuru.setAdapter(new DaftarGuruAdapter());
        return view;
    }

}
