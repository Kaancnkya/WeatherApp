package com.example.weatherapp.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

object NetworkUtil {
    fun isInternetAvailable(context: Context) : Boolean {

        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network = connectivityManager.activeNetwork ?: return false

        val networkCapatibilities = connectivityManager.getNetworkCapabilities(network) ?: return false

        return networkCapatibilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                networkCapatibilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) ||
                networkCapatibilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)

    }
}