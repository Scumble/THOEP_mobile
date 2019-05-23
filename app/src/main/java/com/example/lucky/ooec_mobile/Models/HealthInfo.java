package com.example.lucky.ooec_mobile.Models;

import com.squareup.moshi.Json;

public class HealthInfo {

    @Json(name = "id")
    public int Id;
    @Json(name = "patientId")
    public int PatientId;
    @Json(name = "diseaseCode")
    public String DiseaseCode;
    @Json(name = "heartRate")
    public float HeartRate;
    @Json(name = "bloodPressure")
    public float BloodPressure;
    @Json(name = "temperature")
    public float Temperature;
    @Json(name = "weight")
    public float Weight;
    @Json(name="time")
    public String Time;

    public HealthInfo(int id, int patientId, String diseaseCode, float heartRate, float bloodPressure, float temperature, float weight, String time) {
        Id = id;
        PatientId = patientId;
        DiseaseCode = diseaseCode;
        HeartRate = heartRate;
        BloodPressure = bloodPressure;
        Temperature = temperature;
        Weight = weight;
        Time = time;
    }

    public HealthInfo() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getPatientId() {
        return PatientId;
    }

    public void setPatientId(int patientId) {
        PatientId = patientId;
    }

    public String getDiseaseCode() {
        return DiseaseCode;
    }

    public void setDiseaseCode(String diseaseCode) {
        DiseaseCode = diseaseCode;
    }

    public float getHeartRate() {
        return HeartRate;
    }

    public void setHeartRate(float heartRate) {
        HeartRate = heartRate;
    }

    public float getBloodPressure() {
        return BloodPressure;
    }

    public void setBloodPressure(float bloodPressure) {
        BloodPressure = bloodPressure;
    }

    public float getTemperature() {
        return Temperature;
    }

    public void setTemperature(float temperature) {
        Temperature = temperature;
    }

    public float getWeight() {
        return Weight;
    }

    public void setWeight(float weight) {
        Weight = weight;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}
