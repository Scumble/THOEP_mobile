package com.example.lucky.ooec_mobile.Models;

import com.squareup.moshi.Json;

public class Lobby {
    @Json(name = "id")
    public int Id;
    @Json(name = "scoreWinner")
    public int ScoreWinner;
    @Json(name = "scoreLoser")
    public int ScoreLoser;
    @Json(name = "winner")
    public String Winner;
    @Json(name = "dateStart")
    public String DateStart;
    @Json(name = "tournamentId")
    public int TournamentId;
    @Json(name = "radiant_team_name")
    public String Radiant_team_name;
    @Json(name = "dire_team_name")
    public String Dire_team_name;


    public Lobby() {
    }

    public Lobby(int id, int scoreWinner, int scoreLoser, String winner, String dateStart, int tournamentId, String radiant_team_name, String dire_team_name) {
        Id = id;
        ScoreWinner = scoreWinner;
        ScoreLoser = scoreLoser;
        Winner = winner;
        DateStart = dateStart;
        TournamentId = tournamentId;
        Radiant_team_name = radiant_team_name;
        Dire_team_name = dire_team_name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getScoreWinner() {
        return ScoreWinner;
    }

    public void setScoreWinner(int scoreWinner) {
        ScoreWinner = scoreWinner;
    }

    public int getScoreLoser() {
        return ScoreLoser;
    }

    public void setScoreLoser(int scoreLoser) {
        ScoreLoser = scoreLoser;
    }

    public String getWinner() {
        return Winner;
    }

    public void setWinner(String winner) {
        Winner = winner;
    }

    public String getDateStart() {
        return DateStart;
    }

    public void setDateStart(String dateStart) {
        DateStart = dateStart;
    }

    public int getTournamentId() {
        return TournamentId;
    }

    public void setTournamentId(int tournamentId) {
        TournamentId = tournamentId;
    }

    public String getRadiant_team_name() {
        return Radiant_team_name;
    }

    public void setRadiant_team_name(String radiant_team_name) {
        Radiant_team_name = radiant_team_name;
    }

    public String getDire_team_name() {
        return Dire_team_name;
    }

    public void setDire_team_name(String dire_team_name) {
        Dire_team_name = dire_team_name;
    }
}
