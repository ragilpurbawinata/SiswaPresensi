package com.rglstudio.siswapresensi.util;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

public class DialogUtil {
    private static ProgressDialog m_Dialog;
    private static AlertDialog ad;

    public static void showProgressDialog(Context context, String message){
        m_Dialog = new ProgressDialog(context);
        m_Dialog.setMessage(message);
        m_Dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        m_Dialog.setCancelable(false);
        m_Dialog.show();
    }

    public static void dialogDismiss(){
        m_Dialog.dismiss();
    }

    public static void showAlert(Context context, String msg){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setMessage(msg);
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                ad.dismiss();
            }
        });
        ad = alertDialog.create();
        ad.show();
    }
}
