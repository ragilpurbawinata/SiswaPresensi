package com.rglstudio.siswapresensi.ui.wali.presensi;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.rglstudio.siswapresensi.model.ResponPresensi;
import com.rglstudio.siswapresensi.service.AppController;

import java.util.HashMap;
import java.util.Map;

import timber.log.Timber;

public class PresensiPresenter {
    private PresensiView presensiView;

    public PresensiPresenter(PresensiView presensiView) {
        this.presensiView = presensiView;
    }

    public void getPresensi(String url, final String nis, final String startDate, final String endDate){
        final StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Timber.e("RESPONSE "+response);
                        ResponPresensi responPresensi = new Gson().fromJson(response, ResponPresensi.class);
                        if (responPresensi.getSuccess()){
                            presensiView.onSuccess(responPresensi);
                        }
                        else {
                            presensiView.onFailed("Data presensi tidak ditemukan");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Timber.e("ERROR "+error.getMessage());
                        presensiView.onFailed("Eror silahkan coba beberapa saat lagi");
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("nis", nis);
                params.put("start_date", startDate);
                params.put("end_date", endDate);

                Timber.e("PARAMS " + params);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(request);
    }
}
