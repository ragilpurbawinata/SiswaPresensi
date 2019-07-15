package com.rglstudio.siswapresensi.ui.sendnotif;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.rglstudio.siswapresensi.service.API;
import com.rglstudio.siswapresensi.service.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import timber.log.Timber;

public class SendNotifPresenter {
    private SendNotifView sendNotifView;

    public SendNotifPresenter(SendNotifView sendNotifView) {
        this.sendNotifView = sendNotifView;
    }

    public void sendNotification(String fcmId, String title, String msg) {
        JSONObject notification = new JSONObject();
        JSONObject notifcationBody = new JSONObject();
        try {
            notifcationBody.put("title", title);
            notifcationBody.put("message", msg);

            notification.put("to", fcmId);
            notification.put("data", notifcationBody);
        } catch (JSONException e) {
            Timber.e("onCreate: " + e.getMessage() );
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(API.FCM_API, notification,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Timber.e("RESPON FCM "+response.toString());
                        sendNotifView.onSuccessSendNotif("Notifikasi terkirim");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Timber.e("RESPON FCM EROR "+error.getMessage());
                        sendNotifView.onFailedSendNotif("Request error");
                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", "key=" + API.FCM_SERVER_KEY);
                params.put("Content-Type", "application/json");
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }
}
