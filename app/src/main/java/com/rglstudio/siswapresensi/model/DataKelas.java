package com.rglstudio.siswapresensi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataKelas {
    @SerializedName("kd_kelas")
    @Expose
    private String kdKelas;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("kapasitas")
    @Expose
    private String kapasitas;

    public String getKdKelas() {
        return kdKelas;
    }

    public void setKdKelas(String kdKelas) {
        this.kdKelas = kdKelas;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKapasitas() {
        return kapasitas;
    }

    public void setKapasitas(String kapasitas) {
        this.kapasitas = kapasitas;
    }

}
