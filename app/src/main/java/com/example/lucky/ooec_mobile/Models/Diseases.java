package com.example.lucky.ooec_mobile.Models;

import com.squareup.moshi.Json;

public class Diseases {
    @Json(name = "id")
    public int Id;
    @Json(name = "diseaseCode")
    public String DiseaseCode;
    @Json(name = "diseaseName")
    public String DiseaseName;
    @Json(name = "dangerLevel")
    public int DangerLevel;
    @Json(name = "recommendation")
    public String Recommendation;
}
