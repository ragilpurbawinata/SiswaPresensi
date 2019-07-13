package com.rglstudio.siswapresensi.ui.wali.daftarguru;

import com.rglstudio.siswapresensi.model.ResponGuru;

public interface DaftarGuruView {
    void onSuccess(ResponGuru responGuru);
    void onFailed(String msg);
}
