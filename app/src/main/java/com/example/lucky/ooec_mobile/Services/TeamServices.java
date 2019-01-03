package com.example.lucky.ooec_mobile.Services;

import com.example.lucky.ooec_mobile.Models.Lobby;
import com.example.lucky.ooec_mobile.Models.Team;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TeamServices {
        @GET("team/getTeams/{tournamentId}")
        Call<List<Team>> getTeams(@Path("tournamentId") int tournamentId);

        @POST("team/create")
        Call<Team> addTeam(@Body Team team);

        @PUT("team/update")
        Call<Team> updateTeam(@Body Team team);

        @DELETE("team/delete/{id}")
        Call<Team> deleteTeam(@Path("id") int id);
}
