package com.example.lucky.ooec_mobile.Services;

import com.example.lucky.ooec_mobile.Models.HealthInfo;
import com.example.lucky.ooec_mobile.Models.HealthInfoStatistics;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface HealthInfoStatisticsServices {
    @GET("healthInfo/average/{patientId}")
    Call<HealthInfoStatistics> getHealthInfoStatistics(@Path("patientId") int patientId);
}
