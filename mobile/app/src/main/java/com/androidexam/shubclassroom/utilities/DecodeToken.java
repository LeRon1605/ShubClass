package com.androidexam.shubclassroom.utilities;

import android.util.Base64;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class DecodeToken {
    private static String[] split ;
    private static JSONObject jsonObject;
    public static void decoded(String JWTEncoded) throws Exception {
        try {
            split = JWTEncoded.split("\\.");
            jsonObject = new JSONObject(getJson(split[1]));
        } catch (UnsupportedEncodingException e) {
            //Error
            Log.d("JWT_DECODED", "decoded: " + e.getMessage());
        }
    }

    private static String getJson(String strEncoded) throws UnsupportedEncodingException{
        byte[] decodedBytes = Base64.decode(strEncoded, Base64.URL_SAFE);
        return new String(decodedBytes, "UTF-8");
    }
    public static String getStringValueObjectByKey(String key) throws JSONException {
        String value = (String) jsonObject.get(key);
        return value;
    }
}
