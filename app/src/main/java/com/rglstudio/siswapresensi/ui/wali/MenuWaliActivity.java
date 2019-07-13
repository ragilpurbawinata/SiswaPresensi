package com.rglstudio.siswapresensi.ui.wali;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.rglstudio.siswapresensi.R;
import com.rglstudio.siswapresensi.ui.login.LoginActivity;
import com.rglstudio.siswapresensi.ui.wali.daftarguru.DaftarGuruFragment;
import com.rglstudio.siswapresensi.ui.wali.nilai.NilaiFragment;
import com.rglstudio.siswapresensi.ui.wali.presensi.PresensiFragment;
import com.rglstudio.siswapresensi.util.MyPref;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuWaliActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nav_view)
    BottomNavigationView navView;

    final Fragment fragNilai = new NilaiFragment();
    final Fragment fragPresensi = new PresensiFragment();
    final Fragment fragGuru = new DaftarGuruFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragNilai;

    private MyPref pref;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_nilai:
                    fm.beginTransaction().hide(active).show(fragNilai).commit();
                    active = fragNilai;
                    return true;
                case R.id.nav_presensi:
                    fm.beginTransaction().hide(active).show(fragPresensi).commit();
                    active = fragPresensi;
                    return true;
                case R.id.nav_guru:
                    fm.beginTransaction().hide(active).show(fragGuru).commit();
                    active = fragGuru;
                    return true;
                case R.id.nav_keluar:
                    pref.logout();
                    Intent intent = new Intent(MenuWaliActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_wali);
        ButterKnife.bind(this);

        pref = new MyPref(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Nama siswa : "+pref.getKeyUserSiswaName());
        getSupportActionBar().setSubtitle("NIS : "+pref.getKeyUserNis()+" | Kelas : "+pref.getKeyUserSiswaKelas());

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fm.beginTransaction().add(R.id.fragment_container, fragPresensi, "fragPresensi").hide(fragPresensi).commit();
        fm.beginTransaction().add(R.id.fragment_container, fragGuru, "fragGuru").hide(fragGuru).commit();
        fm.beginTransaction().add(R.id.fragment_container, fragNilai, "fragNilai").commit();
    }

}
