package com.rglstudio.siswapresensi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponGcmId {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private DataGcmId data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public DataGcmId getData() {
        return data;
    }

    public void setData(DataGcmId data) {
        this.data = data;
    }
}
