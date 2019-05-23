package com.example.lucky.ooec_mobile.Services;
import com.example.lucky.ooec_mobile.Models.Patient;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PatientServices {
    @GET("patients")
    Call<List<Patient>> getPatients();

    @POST("patients")
    @Headers({
            "Content-Type: application/json;charset=utf-8",
            "Accept: application/json;charset=utf-8",
            "Cache-Control: max-age=640000"
    })
    Call<Patient> addPatient(@Body Patient patient);

    @PUT("patients")
    Call<Patient> updatePatient(@Body Patient patient);

    @DELETE("patients/{id}")
    Call<Patient> deletePatient(@Path("id") int id);
}
