package com.example.lucky.ooec_mobile.Models;

import com.squareup.moshi.Json;

public class HealthInfoStatistics {
    @Json(name = "averageHeartRate")
    public float AverageHeartRate;
    @Json(name = "averageBloodPressure")
    public float AverageBloodPressure;
    @Json(name = "averageTemperature")
    public float AverageTemperature;
    @Json(name = "averageWeight")
    public float AverageWeight;

    public HealthInfoStatistics(float averageHeartRate, float averageBloodPressure, float averageTemperature, float averageWeight) {
        AverageHeartRate = averageHeartRate;
        AverageBloodPressure = averageBloodPressure;
        AverageTemperature = averageTemperature;
        AverageWeight = averageWeight;
    }

    public HealthInfoStatistics() {
    }

    public float getAverageHeartRate() {
        return AverageHeartRate;
    }

    public void setAverageHeartRate(float averageHeartRate) {
        AverageHeartRate = averageHeartRate;
    }

    public float getAverageBloodPressure() {
        return AverageBloodPressure;
    }

    public void setAverageBloodPressure(float averageBloodPressure) {
        AverageBloodPressure = averageBloodPressure;
    }

    public float getAverageTemperature() {
        return AverageTemperature;
    }

    public void setAverageTemperature(float averageTemperature) {
        AverageTemperature = averageTemperature;
    }

    public float getAverageWeight() {
        return AverageWeight;
    }

    public void setAverageWeight(float averageWeight) {
        AverageWeight = averageWeight;
    }
}
