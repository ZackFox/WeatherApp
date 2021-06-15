package com.sergy.weather.data

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sergy.weather.data.local.CurrentEntity
import com.sergy.weather.data.local.WeatherLocalDatasource
import com.sergy.weather.data.remote.WeatherDatasource
import com.sergy.weather.data.remote.dto.CurrentResponse
import com.sergy.weather.data.remote.dto.toEntity
import com.sergy.weather.utils.NetworkChecker
import com.sergy.weather.utils.NetworkHelper
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    val remoteDatasource: WeatherDatasource,
    val localDatasource: WeatherLocalDatasource,
    val sharedPreferences: SharedPreferences,
    val networkHelper: NetworkChecker
) : WeatherRepository {
    val TAG = "WeatherRepository"

    private fun getLocation(): String {
        val location : String? = sharedPreferences.getString("CUSTOM_LOCATION",null)
        return location ?: "Moscow"
    }

    @SuppressLint("CheckResult")
    override fun getCurrentWeather(city: String, lang: String, units: String): Single<CurrentEntity> {
        return if(networkHelper.isNetworkConnected()){
           remoteDatasource.getCurrentWeather(getLocation(),lang,units)
               .map {
                   val entity = it.data[0].toEntity()
                   localDatasource.saveCurrentWeather(entity)
                   return@map entity
               }
        } else {
            localDatasource.getCurrentWeather()
        }
    }
}