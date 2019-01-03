package com.example.lucky.ooec_mobile.Models;

import com.squareup.moshi.Json;

public class Register {
    @Json(name = "email")
    public String Email;
    @Json(name = "password")
    public String Password;
    @Json(name = "firstname")
    public String FirstName;
    @Json(name = "Lastname")
    public String LastName;
    @Json(name = "location")
    public String Location;

    public Register() {
    }

    public Register(String email, String password, String firstName, String lastName, String location) {
        Email = email;
        Password = password;
        FirstName = firstName;
        LastName = lastName;
        Location = location;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }
}
