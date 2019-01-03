package com.example.lucky.ooec_mobile.Models;

import com.squareup.moshi.Json;

public class Team {
    @Json(name="id")
    public int Id;
    @Json(name="teamName")
    public String TeamName;
    @Json(name="tag")
    public String Tag;
    @Json(name="numberOfPlayers")
    public int NumberOfPlayers;

    public Team() {
    }

    public Team(int id, String teamName, String tag, int numberOfPlayers) {
        Id = id;
        TeamName = teamName;
        Tag = tag;
        NumberOfPlayers = numberOfPlayers;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTeamName() {
        return TeamName;
    }

    public void setTeamName(String teamName) {
        TeamName = teamName;
    }

    public String getTag() {
        return Tag;
    }

    public void setTag(String tag) {
        Tag = tag;
    }

    public int getNumberOfPlayers() {
        return NumberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        NumberOfPlayers = numberOfPlayers;
    }
}
