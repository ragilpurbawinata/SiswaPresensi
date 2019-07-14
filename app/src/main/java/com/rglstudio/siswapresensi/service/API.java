package com.rglstudio.siswapresensi.service;

import com.rglstudio.siswapresensi.BuildConfig;

public class API {
    private static String BASE_URL = BuildConfig.BASEURL;

    public static String LOGIN = BASE_URL+"api/login";
    public static String REGIS_WALI = BASE_URL+"api/register-wali";
    public static String GET_ALL_KELAS = BASE_URL+"api/get-all-kelas";
    public static String GET_ALL_GURU = BASE_URL+"api/get-all-guru";
    public static String GET_SISWA_BY_KELAS = BASE_URL+"api/get-siswa-by-kelas";
    public static String GET_NILAI = BASE_URL+"api/get-nilai-siswa";
    public static String GET_PRESENSI = BASE_URL+"api/get-presensi-siswa";
    public static String ADD_NILAI = BASE_URL+"api/add-nilai";
    public static String ADD_PRESENSI = BASE_URL+"api/add-presensi";
    public static String ADD_GCM_ID = BASE_URL+"api/add-gcm-id";
    public static String GET_GCM_ID = BASE_URL+"api/get-gcm-by-nis";
}
