package com.rglstudio.siswapresensi.ui.wali.daftarguru;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.rglstudio.siswapresensi.model.ResponGuru;
import com.rglstudio.siswapresensi.service.AppController;

import timber.log.Timber;

public class DaftarGuruPresenter {
    private DaftarGuruView guruView;

    public DaftarGuruPresenter(DaftarGuruView guruView) {
        this.guruView = guruView;
    }

    public void getGuru(String url){
        final StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Timber.e("RESPONSE "+response);
                        ResponGuru responGuru = new Gson().fromJson(response, ResponGuru.class);
                        if (responGuru.getSuccess()){
                            guruView.onSuccess(responGuru);
                        }
                        else {
                            guruView.onFailed("Data guru tidak ditemukan");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Timber.e("ERROR "+error.getMessage());
                        guruView.onFailed("Eror silahkan coba beberapa saat lagi");
                    }
                });
        AppController.getInstance().addToRequestQueue(request);
    }
}
