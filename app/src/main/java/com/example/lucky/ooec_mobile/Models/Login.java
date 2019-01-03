package com.example.lucky.ooec_mobile.Models;

import com.squareup.moshi.Json;

public class Login {
    @Json(name = "username")
    public String UserName;
    @Json(name = "password")
    public String Password;

    public Login() {

    }

    public Login(String userName, String password) {
        UserName = userName;
        Password = password;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
