package com.example.weatherapp.model.data


import com.google.gson.annotations.SerializedName

data class DailyUnits(
    @SerializedName("apparent_temperature_max")
    val apparentTemperatureMax: String?,
    @SerializedName("apparent_temperature_min")
    val apparentTemperatureMin: String?,
    @SerializedName("time")
    val time: String?,
    @SerializedName("weathercode")
    val weathercode: String?
)