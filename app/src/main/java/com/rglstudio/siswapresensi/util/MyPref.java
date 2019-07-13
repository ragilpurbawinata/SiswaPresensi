package com.rglstudio.siswapresensi.util;

import android.content.Context;
import android.content.SharedPreferences;

public class MyPref {
    private SharedPreferences pref;

    private SharedPreferences.Editor editor;
    private Context _context;

    // Shared pref mode
    private int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "PrefName";

    private static final String KEY_IS_LOGIN = "login";
    private static final String KEY_LOGIN_AS = "user_as";
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_USER_KD_MAPEL = "user_kd_mapel";

    private static final String KEY_USER_NIS = "user_nis";
    private static final String KEY_USER_SISWA_NAME = "user_siswa_name";
    private static final String KEY_USER_SISWA_KELAS = "user_siswa_kelas";

    public MyPref(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void logout(){
        setKeyIsLogin(false);
        setKeyLoginAs("");
        setKeyUserId("");
        setKeyUserKdMapel("");
        setKeyUserNis("");
        setKeyUserSiswaName("");
        setKeyUserSiswaKelas("");
        pref.edit().remove(PREF_NAME).commit();
    }


    public Boolean getKeyIsLogin(){
        return pref.getBoolean(KEY_IS_LOGIN, false);
    }
    public void setKeyIsLogin(boolean login){
        editor.putBoolean(KEY_IS_LOGIN, login);
        editor.commit();
    }

    public String getKeyLoginAs(){
        return pref.getString(KEY_LOGIN_AS, "");
    }
    public void setKeyLoginAs(String loginAs){
        editor.putString(KEY_LOGIN_AS, loginAs);
        editor.commit();
    }

    public String getKeyUserId(){
        return pref.getString(KEY_USER_ID, "");
    }
    public void setKeyUserId(String id){
        editor.putString(KEY_USER_ID, id);
        editor.commit();
    }

    public String getKeyUserKdMapel(){
        return pref.getString(KEY_USER_KD_MAPEL, "");
    }
    public void setKeyUserKdMapel(String kdMapel){
        editor.putString(KEY_USER_KD_MAPEL, kdMapel);
        editor.commit();
    }

    public String getKeyUserNis(){
        return pref.getString(KEY_USER_NIS, "");
    }
    public void setKeyUserNis(String nis){
        editor.putString(KEY_USER_NIS, nis);
        editor.commit();
    }

    public String getKeyUserSiswaName(){
        return pref.getString(KEY_USER_SISWA_NAME, "");
    }
    public void setKeyUserSiswaName(String siswaName){
        editor.putString(KEY_USER_SISWA_NAME, siswaName);
        editor.commit();
    }

    public String getKeyUserSiswaKelas(){
        return pref.getString(KEY_USER_SISWA_KELAS, "");
    }
    public void setKeyUserSiswaKelas(String siswaKelas){
        editor.putString(KEY_USER_SISWA_KELAS, siswaKelas);
        editor.commit();
    }
}
