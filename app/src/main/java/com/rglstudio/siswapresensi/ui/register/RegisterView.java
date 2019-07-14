package com.rglstudio.siswapresensi.ui.register;

import com.rglstudio.siswapresensi.model.ResponRegister;

public interface RegisterView {
    void onSuccess(ResponRegister responRegister);
    void onFailed(String msg);
}
