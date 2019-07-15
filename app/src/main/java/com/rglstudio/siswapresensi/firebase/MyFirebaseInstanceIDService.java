package com.rglstudio.siswapresensi.firebase;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.rglstudio.siswapresensi.util.MyPref;

import timber.log.Timber;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private MyPref pref;

    @Override
    public void onCreate() {
        super.onCreate();
        pref = new MyPref(this);
    }

    @Override
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();
        Timber.e( "onTokenRefresh completed with token: " + token);
        pref.setKeyUserGcm(token);
    }
}
