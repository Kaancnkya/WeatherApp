package com.example.weatherapp.util

import com.example.weatherapp.R


object Consts{

    // Base Url tanımlandı
    const val BASE_URL = "https://api.open-meteo.com/"

    // ViewType layoutları için sabit değişkenler tanımlandı
    const val VIEW_TYPE_CURRENT_DAY = 0
    const val VIEW_TYPE_NEXTDAYS = 1

    // icons sabitleri olusturma
    const val GUNES = R.drawable.ic_gunes
    const val PARCA = R.drawable.ic_parca
    const val SIS = R.drawable.ic_sis
    const val YAGMUR = R.drawable.ic_yagmur
    const val FIRTINA = R.drawable.ic_firtina
    const val KAR = R.drawable.ic_kar
}

fun String.addCelcius(): String {
    return "$this °"
}

fun String.addSpeedText(): String{
    return "$this km/h"
}