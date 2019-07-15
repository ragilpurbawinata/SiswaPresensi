package com.rglstudio.siswapresensi.ui.login;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.rglstudio.siswapresensi.model.ResponAddGcmId;
import com.rglstudio.siswapresensi.model.ResponLogin;
import com.rglstudio.siswapresensi.service.AppController;

import java.util.HashMap;
import java.util.Map;

import timber.log.Timber;

public class LoginPresenter {
    private LoginView loginView;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
    }

    public void login(String url, final String username, final String pass, final String as){
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Timber.e("RESPONSE "+response);
                        ResponLogin responLogin = new Gson().fromJson(response, ResponLogin.class);
                        if (responLogin.getSuccess()){
                            loginView.onSuccess(responLogin);
                        }
                        else {
                            loginView.onFailed(responLogin.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Timber.e("ERROR "+error.getMessage());
                        loginView.onFailed("Eror silahkan coba beberapa saat lagi");
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", pass);
                params.put("as", as);

                Timber.e("PARAMS "+params);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(request);
    }

    public void sendGcm(String url, final String nis, final String gcm){
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Timber.e("RESPONSE "+response);
                        ResponAddGcmId responGcmId = new Gson().fromJson(response, ResponAddGcmId.class);
                        if (responGcmId.getSuccess()){
                            loginView.onSuccessGcm(responGcmId);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Timber.e("ERROR "+error.getMessage());
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("nis", nis);
                params.put("gcm_id", gcm);

                Timber.e("PARAMS "+params);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(request);
    }
}
