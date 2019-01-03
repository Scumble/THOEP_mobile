package com.example.lucky.ooec_mobile.Services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ArduinoServices {
    @GET("arduino/temperature/{degree}")
    Call setTemperature(@Path("degree") int degree);
}
