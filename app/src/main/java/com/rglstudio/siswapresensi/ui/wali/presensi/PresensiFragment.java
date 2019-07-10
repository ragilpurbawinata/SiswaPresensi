package com.rglstudio.siswapresensi.ui.wali.presensi;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rglstudio.siswapresensi.R;
import com.rglstudio.siswapresensi.adapter.PresensiAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PresensiFragment extends Fragment {
    @BindView(R.id.rvPresensi)
    RecyclerView rvPresensi;

    public PresensiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_presensi, container, false);
        ButterKnife.bind(this, view);

        rvPresensi.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvPresensi.setAdapter(new PresensiAdapter());
        return view;
    }

}
