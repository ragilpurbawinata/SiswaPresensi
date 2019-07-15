package com.rglstudio.siswapresensi.ui.login;

import com.rglstudio.siswapresensi.model.ResponAddGcmId;
import com.rglstudio.siswapresensi.model.ResponLogin;

public interface LoginView {
    void onSuccess(ResponLogin responLogin);
    void onSuccessGcm(ResponAddGcmId responAddGcmId);
    void onFailed(String msg);
}
