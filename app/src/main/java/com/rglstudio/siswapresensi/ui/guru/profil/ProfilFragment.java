package com.rglstudio.siswapresensi.ui.guru.profil;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.rglstudio.siswapresensi.R;
import com.rglstudio.siswapresensi.util.MyPref;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfilFragment extends Fragment {
    @BindView(R.id.namaGuru)
    TextView name;
    @BindView(R.id.mapelGuru)
    TextView mapel;
    @BindView(R.id.alamatGuru)
    TextView alamat;
    @BindView(R.id.teleponGuru)
    TextView telp;

    private MyPref pref;

    public ProfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profil, container, false);
        ButterKnife.bind(this, view);

        initView();
        return view;
    }

    private void initView() {
        pref = new MyPref(getContext());

        name.setText(pref.getKeyUserGuruName());
        mapel.setText(pref.getKeyUserKdMapel()+" | "+pref.getKeyUserNameMapel());
        alamat.setText(pref.getKeyUserGuruAddress());
        telp.setText(pref.getKeyUserGuruTelp());
    }

}
