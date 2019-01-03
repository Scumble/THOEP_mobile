package com.example.lucky.ooec_mobile.Services;

import com.example.lucky.ooec_mobile.Models.ProTeamMatches;
import com.example.lucky.ooec_mobile.Models.ProTeamPlayers;
import com.example.lucky.ooec_mobile.Models.ProTeams;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProTeamsServices {
    @GET("proteams/getTeams")
    Call<List<ProTeams>> getTeams();

    @GET("proteams/getTeamPlayers/{id}")
    Call<List<ProTeamPlayers>> getTeamPlayers(@Path("id") int id);

    @GET("proteams/getTeamMatches/{id}")
    Call<List<ProTeamMatches>> getTeamMatches(@Path("id") int id);
}
