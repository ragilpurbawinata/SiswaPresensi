package com.rglstudio.siswapresensi.ui.guru.inputnilai;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.rglstudio.siswapresensi.model.ResponAddNilai;
import com.rglstudio.siswapresensi.model.ResponGcmId;
import com.rglstudio.siswapresensi.model.ResponSiswa;
import com.rglstudio.siswapresensi.service.AppController;

import java.util.HashMap;
import java.util.Map;

import timber.log.Timber;

public class SiswaKelasPresenter {
    private SiswaKelasView kelasView;

    public SiswaKelasPresenter(SiswaKelasView kelasView) {
        this.kelasView = kelasView;
    }

    public void getSiswa(String url, final String kdKelas){
        final StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Timber.e("RESPONSE "+response);
                        ResponSiswa responSiswa = new Gson().fromJson(response, ResponSiswa.class);
                        if (responSiswa.getSuccess()){
                            kelasView.onSuccessGetSiswa(responSiswa);
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
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("kd_kelas", kdKelas);

                Timber.e("PARAMS " + params);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(request);
    }

    public void getGcm(String url, final String nis){
        final StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Timber.e("RESPONSE "+response);
                        ResponGcmId responGcmId = new Gson().fromJson(response, ResponGcmId.class);
                        if (responGcmId.getSuccess()){
                            kelasView.onSuccessGetGcm(responGcmId);
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

    public void addNilai(String url, final String nis, final String kdMapel, final String nilai){
        final StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Timber.e("RESPONSE "+response);
                        ResponAddNilai responAddNilai = new Gson().fromJson(response, ResponAddNilai.class);
                        if (responAddNilai.getSuccess()){
                            kelasView.onSuccessAddNilai(responAddNilai);
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
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("nis", nis);
                params.put("kd_mapel", kdMapel);
                params.put("nilai", nilai);

                Timber.e("PARAMS " + params);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(request);
    }
}
