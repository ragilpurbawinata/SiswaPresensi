package com.rglstudio.siswapresensi.ui.guru.inputnilai;

import com.rglstudio.siswapresensi.model.ResponAddNilai;
import com.rglstudio.siswapresensi.model.ResponGcmId;
import com.rglstudio.siswapresensi.model.ResponSiswa;

public interface SiswaKelasView {
    void onSuccessGetSiswa(ResponSiswa responSiswa);
    void onSuccessGetGcm(ResponGcmId responGcmId);
    void onSuccessAddNilai(ResponAddNilai responAddNilai);

    void onFailed(String msg);

}
