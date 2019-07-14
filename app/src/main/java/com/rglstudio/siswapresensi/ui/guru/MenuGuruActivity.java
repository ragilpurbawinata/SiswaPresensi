package com.rglstudio.siswapresensi.ui.guru;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.rglstudio.siswapresensi.R;
import com.rglstudio.siswapresensi.ui.guru.inputnilai.InputNilaiFragment;
import com.rglstudio.siswapresensi.ui.guru.inputpresensi.InputPresensiFragment;
import com.rglstudio.siswapresensi.ui.guru.profil.ProfilFragment;
import com.rglstudio.siswapresensi.ui.login.LoginActivity;
import com.rglstudio.siswapresensi.util.MyPref;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuGuruActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nav_view)
    BottomNavigationView navView;

    final Fragment fragNilai = new InputNilaiFragment();
    final Fragment fragPresensi = new InputPresensiFragment();
    final Fragment fragProfil = new ProfilFragment();
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
                case R.id.nav_profil:
                    fm.beginTransaction().hide(active).show(fragProfil).commit();
                    active = fragProfil;
                    return true;
                case R.id.nav_keluar:
                    confirmLogout();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_guru);
        ButterKnife.bind(this);

        pref = new MyPref(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(pref.getKeyUserGuruName());
        getSupportActionBar().setSubtitle("Mapel : "+pref.getKeyUserNameMapel());

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fm.beginTransaction().add(R.id.fragment_container, fragPresensi, "fragPresensi").hide(fragPresensi).commit();
        fm.beginTransaction().add(R.id.fragment_container, fragProfil, "fragProfil").hide(fragProfil).commit();
        fm.beginTransaction().add(R.id.fragment_container, fragNilai, "fragNilai").commit();
    }

    private void confirmLogout(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("Yakin ingin keluar ?");
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                pref.logout();
                Intent intent = new Intent(MenuGuruActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        alertDialog.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog ad = alertDialog.create();
        ad.show();
    }
}
