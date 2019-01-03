package com.example.lucky.ooec_mobile.Services;

import com.example.lucky.ooec_mobile.Models.Lobby;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface LobbyServices {
    @GET("lobby/getLobbies/{tournamentId}")
    Call<List<Lobby>> getLobbies(@Path("tournamentId") int tournamentId);

    @POST("lobby/create")
    Call<Lobby> addLobby(@Body Lobby lobby);

    @POST("lobby/update")
    Call<Lobby> updateLobby(@Body Lobby lobby);

    @DELETE("lobby/delete/{id}")
    Call<Lobby> deleteLobby(@Path("id") int id);
}
