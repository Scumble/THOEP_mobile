package com.example.lucky.ooec_mobile.Services;

import com.example.lucky.ooec_mobile.Models.Tournament;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TournamentServices {
    @GET("tournament/getcreatedbyuser")
    Call<List<Tournament>> getTournaments();

    @POST("tournament/create")
    @Headers({
            "Content-Type: application/json;charset=utf-8",
            "Accept: application/json;charset=utf-8",
            "Cache-Control: max-age=640000"
    })
    Call<Tournament> addTournament(@Body Tournament tournament);

    @PUT("tournament/update")
    Call<Tournament> updateTournament(@Body Tournament tournament);

    @DELETE("tournament/delete/{id}")
    Call<Tournament> deleteTournament(@Path("id") int id);
}
