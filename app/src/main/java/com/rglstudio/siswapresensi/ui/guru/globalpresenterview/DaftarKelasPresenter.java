package com.rglstudio.siswapresensi.ui.guru.globalpresenterview;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.rglstudio.siswapresensi.model.ResponKelas;
import com.rglstudio.siswapresensi.service.AppController;

import timber.log.Timber;

public class DaftarKelasPresenter {
    private DaftarKelasView kelasView;

    public DaftarKelasPresenter(DaftarKelasView kelasView) {
        this.kelasView = kelasView;
    }

    public void getKelas(String url){
        final StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Timber.e("RESPONSE "+response);
                        ResponKelas responKelas = new Gson().fromJson(response, ResponKelas.class);
                        if (responKelas.getSuccess()){
                            kelasView.onSuccess(responKelas);
                        }
                        else {
                            kelasView.onFailed("Data tidak ditemukan");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Timber.e("ERROR "+error.getMessage());
                        kelasView.onFailed("Eror silahkan coba beberapa saat lagi");
                    }
                });
        AppController.getInstance().addToRequestQueue(request);
    }
}
