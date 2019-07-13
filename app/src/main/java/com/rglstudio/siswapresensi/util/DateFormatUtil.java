package com.rglstudio.siswapresensi.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {
    public static String formatDisplay(String date, String oldFormat, String newFormat){
        String display = "";
        Date d = new Date();
        SimpleDateFormat df = new SimpleDateFormat(oldFormat);
        try {
            d = df.parse(date);
            df = new SimpleDateFormat(newFormat);
            display = df.format(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return display;
    }
}


