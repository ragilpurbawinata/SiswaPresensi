package com.rglstudio.siswapresensi.ui.login;

import com.rglstudio.siswapresensi.model.ResponLogin;

public interface LoginView {
    void onSuccess(ResponLogin responLogin);
    void onFailed(String msg);
}
