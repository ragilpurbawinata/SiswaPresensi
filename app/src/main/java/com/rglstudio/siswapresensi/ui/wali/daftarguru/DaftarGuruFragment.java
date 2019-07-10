package com.rglstudio.siswapresensi.ui.wali.daftarguru;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rglstudio.siswapresensi.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DaftarGuruFragment extends Fragment {


    public DaftarGuruFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_daftar_guru, container, false);
    }

}
