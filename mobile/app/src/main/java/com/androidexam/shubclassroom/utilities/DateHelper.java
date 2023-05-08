package com.androidexam.shubclassroom.utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DateHelper {
    public static String parseOsiToString(String str) {
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm dd-MM-yyyy");
            return formatter.format(df.parse(str));
        } catch (Exception e) {
            return "";
        }
    }
}
