package com.example.lucky.ooec_mobile.Models;

import com.squareup.moshi.Json;

public class Player {
    @Json(name="id")
    public int Id;
    @Json(name="name")
    public String Name;
    @Json(name="position")
    public String Position;
    @Json(name="age")
    public int Age;
    @Json(name="teamId")
    public int TeamId;

    public Player() {
    }

    public Player(int id, String name, String position, int age, int teamId) {
        Id = id;
        Name = name;
        Position = position;
        Age = age;
        TeamId = teamId;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public int getTeamId() {
        return TeamId;
    }

    public void setTeamId(int teamId) {
        TeamId = teamId;
    }
}
