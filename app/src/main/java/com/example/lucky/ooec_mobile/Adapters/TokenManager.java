package com.example.lucky.ooec_mobile.Adapters;

import android.content.SharedPreferences;

import com.example.lucky.ooec_mobile.Models.AccessToken;

public class TokenManager {
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    private static TokenManager INSTANCE = null;

    private TokenManager(SharedPreferences prefs){
        this.prefs = prefs;
        this.editor = prefs.edit();
    }

    public static synchronized TokenManager getInstance(SharedPreferences prefs){
        if(INSTANCE == null){
            INSTANCE = new TokenManager(prefs);
        }
        return INSTANCE;
    }

    public void saveToken(AccessToken token){
        editor.putString("auth_token", token.getAccessToken()).commit();
    }

    public void deleteToken(){
        editor.remove("auth_token").commit();
    }

    public AccessToken getToken(){
        AccessToken token = new AccessToken();
        token.setAccessToken(prefs.getString("auth_token", null));
        return token;
    }
}
