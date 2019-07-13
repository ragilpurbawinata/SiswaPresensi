package com.rglstudio.siswapresensi.ui.wali.nilai;

import com.rglstudio.siswapresensi.model.ResponNilai;

public interface NilaiView {
    void onSuccess(ResponNilai responNilai);
    void onFailed(String msg);
}
