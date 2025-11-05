package com.myfirstandroidjava.salesapp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;
import org.json.JSONObject;
import java.io.UnsupportedEncodingException;

public class TokenManager {
    private static final String PREF_NAME = "app_prefs";
    private static final String KEY_TOKEN = "token";

    private final SharedPreferences sharedPreferences;

    public TokenManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void saveToken(String token) {
        sharedPreferences.edit().putString(KEY_TOKEN, token).apply();
    }

    public String getToken() {
        return sharedPreferences.getString(KEY_TOKEN, null);
    }

    public void clearToken() {
        sharedPreferences.edit().remove(KEY_TOKEN).apply();
    }

    public String getUsername() {
        String token = getToken();
        if (token == null) {
            return null;
        }
        try {
            String[] split = token.split("\\.");
            String body = getJson(split[1]);
            JSONObject jsonObject = new JSONObject(body);
            return jsonObject.getString("http://schemas.xmlsoap.org/ws/2005/05/identity/claims/name");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getJson(String strEncoded) throws UnsupportedEncodingException {
        byte[] decodedBytes = Base64.decode(strEncoded, Base64.URL_SAFE);
        return new String(decodedBytes, "UTF-8");
    }
}
