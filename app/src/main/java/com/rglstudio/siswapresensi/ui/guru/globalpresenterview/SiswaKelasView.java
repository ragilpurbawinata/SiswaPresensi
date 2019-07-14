package com.rglstudio.siswapresensi.ui.guru.globalpresenterview;

import com.rglstudio.siswapresensi.model.ResponAddNilai;
import com.rglstudio.siswapresensi.model.ResponAddPresensi;
import com.rglstudio.siswapresensi.model.ResponGcmId;
import com.rglstudio.siswapresensi.model.ResponSiswa;

public interface SiswaKelasView {
    void onSuccessGetSiswa(ResponSiswa responSiswa);
    void onSuccessGetGcm(ResponGcmId responGcmId);
    void onSuccessAddNilai(ResponAddNilai responAddNilai);
    void onSuccessAddPresensi(ResponAddPresensi responAddPresensi);

    void onFailed(String msg);

}
