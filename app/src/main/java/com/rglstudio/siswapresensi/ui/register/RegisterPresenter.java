package com.rglstudio.siswapresensi.ui.register;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.rglstudio.siswapresensi.model.ResponRegister;
import com.rglstudio.siswapresensi.service.AppController;

import java.util.HashMap;
import java.util.Map;

import timber.log.Timber;

public class RegisterPresenter {
    private RegisterView registerView;

    public RegisterPresenter(RegisterView registerView) {
        this.registerView = registerView;
    }

    public void register(String url, final String nama, final String nis, final String alamat,
                         final String telepon, final String gender,
                         final String username, final String pass){
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Timber.e("RESPONSE "+response);
                        ResponRegister responRegister = new Gson().fromJson(response, ResponRegister.class);
                        if (responRegister.getSuccess()){
                            registerView.onSuccess(responRegister);
                        }
                        else {
                            registerView.onFailed(responRegister.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Timber.e("ERROR "+error.getMessage());
                        registerView.onFailed("Eror silahkan coba beberapa saat lagi");
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("nama", nama);
                params.put("nis", nis);
                params.put("alamat", alamat);
                params.put("telepon", telepon);
                params.put("jenis_kelamin", gender);
                params.put("username", username);
                params.put("password", pass);

                Timber.e("PARAMS "+params);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(request);
    }
}
