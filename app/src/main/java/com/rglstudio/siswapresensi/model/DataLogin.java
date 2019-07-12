package com.rglstudio.siswapresensi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataLogin {
    @SerializedName("guru")
    @Expose
    private LoginGuru guru;
    @SerializedName("wali")
    @Expose
    private LoginWali wali;

    public LoginGuru getGuru() {
        return guru;
    }

    public void setGuru(LoginGuru guru) {
        this.guru = guru;
    }

    public LoginWali getWali() {
        return wali;
    }

    public void setWali(LoginWali wali) {
        this.wali = wali;
    }
}
