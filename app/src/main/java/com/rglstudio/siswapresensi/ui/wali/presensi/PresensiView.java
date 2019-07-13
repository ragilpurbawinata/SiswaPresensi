package com.rglstudio.siswapresensi.ui.wali.presensi;

import com.rglstudio.siswapresensi.model.ResponPresensi;

public interface PresensiView {
    void onSuccess(ResponPresensi responPresensi);
    void onFailed(String msg);
}
