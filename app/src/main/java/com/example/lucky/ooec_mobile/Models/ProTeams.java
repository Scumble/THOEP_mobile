package com.example.lucky.ooec_mobile.Models;

import com.squareup.moshi.Json;

public class ProTeams {
    @Json(name="team_Id")
    public int Team_Id;
    @Json(name="rating")
    public double Rating;
    @Json(name="wins")
    public int Wins;
    @Json(name="losses")
    public int losses;
    @Json(name = "last_Match_Time")
    public int Last_Match_Time;
    @Json(name="name")
    public String Name;
    @Json(name="tag")
    public String Tag;
    @Json(name="logo_url")
    public String logo_url;

    public ProTeams() {
    }

    public ProTeams(int team_Id, double rating, int wins, int losses, int last_Match_Time, String name, String tag, String logo_url) {
        Team_Id = team_Id;
        Rating = rating;
        Wins = wins;
        this.losses = losses;
        Last_Match_Time = last_Match_Time;
        Name = name;
        Tag = tag;
        this.logo_url = logo_url;
    }

    public int getTeam_Id() {
        return Team_Id;
    }

    public void setTeam_Id(int team_Id) {
        Team_Id = team_Id;
    }

    public double getRating() {
        return Rating;
    }

    public void setRating(double rating) {
        Rating = rating;
    }

    public int getWins() {
        return Wins;
    }

    public void setWins(int wins) {
        Wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getLast_Match_Time() {
        return Last_Match_Time;
    }

    public void setLast_Match_Time(int last_Match_Time) {
        Last_Match_Time = last_Match_Time;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTag() {
        return Tag;
    }

    public void setTag(String tag) {
        Tag = tag;
    }

    public String getLogo_url() {
        return logo_url;
    }

    public void setLogo_url(String logo_url) {
        this.logo_url = logo_url;
    }
}
