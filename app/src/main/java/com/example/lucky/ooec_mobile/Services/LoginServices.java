package com.example.lucky.ooec_mobile.Services;

import com.example.lucky.ooec_mobile.Models.AccessToken;
import com.example.lucky.ooec_mobile.Models.Login;
import com.example.lucky.ooec_mobile.Models.Register;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface LoginServices {
    @POST("account")
    Call<AccessToken> register(@Body Register register);

    @POST("auth/login")
    //@FormUrlEncoded
    Call<AccessToken> login(@Body Login login);
}
