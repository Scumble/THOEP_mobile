package com.example.lucky.ooec_mobile.Services;

import com.example.lucky.ooec_mobile.Models.Diseases;
import com.example.lucky.ooec_mobile.Models.HealthInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DiseaseServices {
    @GET("diseases")
    Call<List<Diseases>> getDiseases();
}
