package com.example.lucky.ooec_mobile.Models;

import com.squareup.moshi.Json;

public class ProTeamMatches {
    @Json(name="match_id")
    public long Match_id;
    @Json(name="radiant_win")
    public boolean Radiant_win;
    @Json(name="radiant")
    public boolean Radiant;
    @Json(name="duration")
    public long Duration;
    @Json(name="start_time")
    public long Start_time;
    @Json(name="leagueid")
    public long Leagueid;
    @Json(name="league_name")
    public String League_name;
    @Json(name="cluster")
    public long Cluster;
    @Json(name="opposing_team_id")
    public int Opposing_team_id;
    @Json(name="opposing_team_name")
    public String Opposing_team_name;
    @Json(name="opposing_team_logo")
    public String Opposing_team_logo;

    public ProTeamMatches() {
    }

    public ProTeamMatches(long match_id, boolean radiant_win, boolean radiant, long duration, long start_time, long leagueid, String league_name, long cluster, int opposing_team_id, String opposing_team_name, String opposing_team_logo) {
        Match_id = match_id;
        Radiant_win = radiant_win;
        Radiant = radiant;
        Duration = duration;
        Start_time = start_time;
        Leagueid = leagueid;
        League_name = league_name;
        Cluster = cluster;
        Opposing_team_id = opposing_team_id;
        Opposing_team_name = opposing_team_name;
        Opposing_team_logo = opposing_team_logo;
    }

    public long getMatch_id() {
        return Match_id;
    }

    public void setMatch_id(long match_id) {
        Match_id = match_id;
    }

    public boolean isRadiant_win() {
        return Radiant_win;
    }

    public void setRadiant_win(boolean radiant_win) {
        Radiant_win = radiant_win;
    }

    public boolean isRadiant() {
        return Radiant;
    }

    public void setRadiant(boolean radiant) {
        Radiant = radiant;
    }

    public long getDuration() {
        return Duration;
    }

    public void setDuration(long duration) {
        Duration = duration;
    }

    public long getStart_time() {
        return Start_time;
    }

    public void setStart_time(long start_time) {
        Start_time = start_time;
    }

    public long getLeagueid() {
        return Leagueid;
    }

    public void setLeagueid(long leagueid) {
        Leagueid = leagueid;
    }

    public String getLeague_name() {
        return League_name;
    }

    public void setLeague_name(String league_name) {
        League_name = league_name;
    }

    public long getCluster() {
        return Cluster;
    }

    public void setCluster(long cluster) {
        Cluster = cluster;
    }

    public int getOpposing_team_id() {
        return Opposing_team_id;
    }

    public void setOpposing_team_id(int opposing_team_id) {
        Opposing_team_id = opposing_team_id;
    }

    public String getOpposing_team_name() {
        return Opposing_team_name;
    }

    public void setOpposing_team_name(String opposing_team_name) {
        Opposing_team_name = opposing_team_name;
    }

    public String getOpposing_team_logo() {
        return Opposing_team_logo;
    }

    public void setOpposing_team_logo(String opposing_team_logo) {
        Opposing_team_logo = opposing_team_logo;
    }
}
