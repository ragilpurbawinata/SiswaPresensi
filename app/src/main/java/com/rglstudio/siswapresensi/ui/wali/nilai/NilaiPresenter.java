package com.rglstudio.siswapresensi.ui.wali.nilai;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.rglstudio.siswapresensi.model.ResponNilai;
import com.rglstudio.siswapresensi.service.AppController;

import java.util.HashMap;
import java.util.Map;

import timber.log.Timber;

public class NilaiPresenter {
    private NilaiView nilaiView;

    public NilaiPresenter(NilaiView nilaiView) {
        this.nilaiView = nilaiView;
    }

    public void getNilai(String url, final String nis){
        final StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Timber.e("RESPONSE "+response);
                        ResponNilai responNilai = new Gson().fromJson(response, ResponNilai.class);
                        if (responNilai.getSuccess()){
                            nilaiView.onSuccess(responNilai);
                        }
                        else {
                            nilaiView.onFailed("Data nilai tidak ditemukan");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Timber.e("ERROR "+error.getMessage());
                        nilaiView.onFailed("Eror silahkan coba beberapa saat lagi");
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("nis", nis);

                Timber.e("PARAMS " + params);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(request);
    }
}
