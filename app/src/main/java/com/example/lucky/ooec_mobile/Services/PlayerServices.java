package com.example.lucky.ooec_mobile.Services;

import com.example.lucky.ooec_mobile.Models.Player;
import com.example.lucky.ooec_mobile.Models.Team;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PlayerServices {
    @GET("player/getPlayers/{teamId}")
    Call<List<Player>> getPlayers(@Path("teamId") int teamId);

    @POST("player/create")
    Call<Player> addPlayer(@Body Player player);

    @PUT("player/update")
    Call<Player> updatePlayer(@Body Player player);

    @DELETE("player/delete/{id}")
    Call<Player> deletePlayer(@Path("id") int id);
}
