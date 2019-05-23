package com.example.lucky.ooec_mobile.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.squareup.moshi.Json;

import java.util.Date;

public class Patient {
    @Json(name = "id")
    public int Id;
    @Json(name = "userId")
    public String UserId;
   @Json(name = "firstName")
    public String FirstName;
    @Json(name = "lastName")
    public String LastName;
    @Json(name = "gender")
    public String Gender;
    @Json(name = "age")
    public int Age;
    @Json(name = "address")
    public String Address;
    @Json(name = "birthDate")
    public String BirthDate;

    public Patient(int id, String userId, String firstName, String lastName, String gender, int age, String address, String birthDate) {
        Id = id;
        UserId = userId;
        FirstName = firstName;
        LastName = lastName;
        Gender = gender;
        Age = age;
        Address = address;
        BirthDate = birthDate;
    }

    public Patient() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
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

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(String birthDate) {
        BirthDate = birthDate;
    }
}
