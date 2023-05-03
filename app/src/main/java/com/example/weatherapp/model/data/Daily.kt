package com.example.weatherapp.model.data


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "daily_tablo")
data class Daily(
    @PrimaryKey
    val id3 : Int = 1,

    @SerializedName("apparent_temperature_max")
    val apparentTemperatureMax: List<Double?>?,

    @SerializedName("apparent_temperature_min")
    val apparentTemperatureMin: List<Double?>?,

    @SerializedName("time")
    val time: List<String?>?,

    @SerializedName("weathercode")
    val weathercode: List<Int?>?)