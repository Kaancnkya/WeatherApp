package com.example.weatherapp.model.data


import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "weather_property")
@Parcelize
data class WeatherResponse(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 1,

    @SerializedName("current_weather")
    @Embedded
    val currentWeather: CurrentWeather?,

    @SerializedName("daily")
    @Embedded
    val daily: Daily?,

    var icons : List<Int>
) : Parcelable