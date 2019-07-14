package com.rglstudio.siswapresensi.ui.guru.inputnilai;

import com.rglstudio.siswapresensi.model.ResponKelas;

public interface DaftarKelasView {
    void onSuccess(ResponKelas responKelas);
    void onFailed(String msg);
}
