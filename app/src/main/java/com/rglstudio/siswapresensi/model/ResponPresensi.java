package com.rglstudio.siswapresensi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponPresensi {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<DataPresensi> data = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<DataPresensi> getData() {
        return data;
    }

    public void setData(List<DataPresensi> data) {
        this.data = data;
    }
}
