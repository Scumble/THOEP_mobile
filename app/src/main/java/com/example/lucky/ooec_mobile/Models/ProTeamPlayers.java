package com.example.lucky.ooec_mobile.Models;

import com.squareup.moshi.Json;

public class ProTeamPlayers {
    @Json(name="account_id")
    public String Account_id;
    @Json(name="name")
    public String Name;
    @Json(name="games_played")
    public int Games_played;
    @Json(name="wins")
    public int Wins;
    @Json(name="is_current_team_member")
    public boolean Is_current_team_member;

    public ProTeamPlayers() {
    }

    public ProTeamPlayers(String account_id, String name, int games_played, int wins, boolean is_current_team_member) {
        Account_id = account_id;
        Name = name;
        Games_played = games_played;
        Wins = wins;
        Is_current_team_member = is_current_team_member;
    }

    public String getAccount_id() {
        return Account_id;
    }

    public void setAccount_id(String account_id) {
        Account_id = account_id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getGames_played() {
        return Games_played;
    }

    public void setGames_played(int games_played) {
        Games_played = games_played;
    }

    public int getWins() {
        return Wins;
    }

    public void setWins(int wins) {
        Wins = wins;
    }

    public boolean isIs_current_team_member() {
        return Is_current_team_member;
    }

    public void setIs_current_team_member(boolean is_current_team_member) {
        Is_current_team_member = is_current_team_member;
    }
}
