package com.rglstudio.siswapresensi.util;

import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

public class TextRequired implements TextWatcher {

    private TextInputLayout tlText;

    public TextRequired(TextInputLayout tlText) {
        this.tlText = tlText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        tlText.setErrorEnabled(false);
        tlText.setError("");
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (TextUtils.isEmpty(s.toString())) {
            tlText.setErrorEnabled(true);
            tlText.setError("Required");
        }
    }
}
