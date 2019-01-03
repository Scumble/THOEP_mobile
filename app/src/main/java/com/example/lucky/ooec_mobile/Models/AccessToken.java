package com.example.lucky.ooec_mobile.Models;

import com.google.gson.annotations.SerializedName;
import com.squareup.moshi.Json;

public class AccessToken {
   /* @Json(name = "token_type")
    String tokenType;*/
   @Json(name="id")
   String id;
   @Json(name="auth_token")
   String authToken;
   @Json(name="expires_in")
   int expiresIn;

    public int getExpiresIn() {
        return expiresIn;
    }

    public String getAccessToken() {
        return authToken;
    }
    public String getId() {
        return id;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public void setAccessToken(String authToken) {
        this.authToken = authToken;
    }

    public void setId(String id) {
        this.id = id;
    }
}
