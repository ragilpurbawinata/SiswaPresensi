package com.rglstudio.siswapresensi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataNilai {
    @SerializedName("kd_nilai")
    @Expose
    private String kdNilai;
    @SerializedName("nis")
    @Expose
    private String nis;
    @SerializedName("kd_mapel")
    @Expose
    private String kdMapel;
    @SerializedName("nilai")
    @Expose
    private String nilai;
    @SerializedName("nama_mapel")
    @Expose
    private String namaMapel;

    public String getKdNilai() {
        return kdNilai;
    }

    public void setKdNilai(String kdNilai) {
        this.kdNilai = kdNilai;
    }

    public String getNis() {
        return nis;
    }

    public void setNis(String nis) {
        this.nis = nis;
    }

    public String getKdMapel() {
        return kdMapel;
    }

    public void setKdMapel(String kdMapel) {
        this.kdMapel = kdMapel;
    }

    public String getNilai() {
        return nilai;
    }

    public void setNilai(String nilai) {
        this.nilai = nilai;
    }

    public String getNamaMapel() {
        return namaMapel;
    }

    public void setNamaMapel(String namaMapel) {
        this.namaMapel = namaMapel;
    }
}
