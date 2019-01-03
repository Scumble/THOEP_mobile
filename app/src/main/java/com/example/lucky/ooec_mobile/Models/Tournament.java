package com.example.lucky.ooec_mobile.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.squareup.moshi.Json;

public class Tournament {
    @Json(name = "id")
    public int Id;
    @Json(name = "identityId")
    public String IdentityId;
   @Json(name = "tournamentName")
    public String TournamentName;
    @Json(name = "place")
    public String Place;
    @Json(name = "type")
    public String Type;
    @Json(name = "prizePool")
    public long PrizePool;
    @Json(name = "dateStart")
    public String DateStart;
    @Json(name = "dateEnd")
    public String DateEnd;
    @Json(name = "description")
    public String Description;
    @Json(name = "game")
    public String Game;

    public Tournament(){}

    public Tournament(int id, String identityId, String tournamentName, String place, String type, long prizePool, String dateStart, String dateEnd, String description, String game) {
        Id = id;
     //   IdentityId = identityId;
        TournamentName = tournamentName;
        Place = place;
        Type = type;
        PrizePool = prizePool;
        DateStart = dateStart;
        DateEnd = dateEnd;
        Description = description;
        Game = game;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getIdentityId() {
        return IdentityId;
    }

    public void setIdentityId(String identityId) {
        IdentityId = identityId;
    }

    public String getTournamentName() {
        return TournamentName;
    }

    public void setTournamentName(String tournamentName) {
        TournamentName = tournamentName;
    }

    public String getPlace() {
        return Place;
    }

    public void setPlace(String place) {
        Place = place;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public long getPrizePool() {
        return PrizePool;
    }

    public void setPrizePool(long prizePool) {
        PrizePool = prizePool;
    }

    public String getDateStart() {
        return DateStart;
    }

    public void setDateStart(String dateStart) {
        DateStart = dateStart;
    }

    public String getDateEnd() {
        return DateEnd;
    }

    public void setDateEnd(String dateEnd) {
        DateEnd = dateEnd;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getGame() {
        return Game;
    }

    public void setGame(String game) {
        Game = game;
    }
}
