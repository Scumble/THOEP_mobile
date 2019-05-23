package com.example.lucky.ooec_mobile.Services;

import com.example.lucky.ooec_mobile.Models.HealthInfo;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface HealthInfoServices {
    @GET("healthInfos/patient/{patientId}")
    Call<List<HealthInfo>> getHealthInfo(@Path("patientId") int patientId);

    @GET("healthInfo/{healthInfoId}/checkpatient/{patientId}")
    Call<List<String>> checkHealthInfo(@Path("healthInfoId") int healthInfoId, @Path("patientId") int patientId);

    @GET("patient-encoded/{patientId}")
    Call<String> HL7EncodePatient(@Path("patientId") int patientId);

    @POST("healthInfo")
    Call<HealthInfo> addHealthInfo(@Body HealthInfo healthInfo);

    @POST("healthInfo")
    Call<HealthInfo> updateHealthInfo(@Body HealthInfo healthInfo);

    @DELETE("healthInfo/{id}")
    Call<HealthInfo> deleteHealthInfo(@Path("id") int id);


}
