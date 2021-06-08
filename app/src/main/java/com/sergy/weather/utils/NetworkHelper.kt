package com.sergy.weather.utils

import android.content.Context
import android.net.ConnectivityManager
import javax.inject.Inject

interface NetworkChecker {
    fun isNetworkConnected(): Boolean
}

class NetworkHelper @Inject constructor(private val context: Context) : NetworkChecker {

    override fun isNetworkConnected(): Boolean {
        val manager = context.applicationContext.getSystemService(
            Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkInfo = manager.activeNetworkInfo
        return networkInfo?.isConnected == true
    }
}