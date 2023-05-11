package com.example.weatherapp.model.data


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "current_weather_tablo")
@Parcelize
data class CurrentWeather(
    @PrimaryKey
    val id2 : Int = 1,

    @SerializedName("is_day")
    val isDay: Int?,

    @SerializedName("temperature")
    val temperature: Double?,

    @SerializedName("winddirection")
    val winddirection: Double?,

    @SerializedName("windspeed")
    val windspeed: Double?
) : Parcelable