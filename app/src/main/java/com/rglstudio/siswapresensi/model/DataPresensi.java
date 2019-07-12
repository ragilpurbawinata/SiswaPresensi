package com.rglstudio.siswapresensi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataPresensi {
    @SerializedName("kd_absensi")
    @Expose
    private String kdAbsensi;
    @SerializedName("nis")
    @Expose
    private String nis;
    @SerializedName("tanggal")
    @Expose
    private String tanggal;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("sesi")
    @Expose
    private String sesi;

    public String getKdAbsensi() {
        return kdAbsensi;
    }

    public void setKdAbsensi(String kdAbsensi) {
        this.kdAbsensi = kdAbsensi;
    }

    public String getNis() {
        return nis;
    }

    public void setNis(String nis) {
        this.nis = nis;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSesi() {
        return sesi;
    }

    public void setSesi(String sesi) {
        this.sesi = sesi;
    }
}
