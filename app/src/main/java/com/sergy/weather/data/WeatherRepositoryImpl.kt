package com.sergy.weather.data

import android.annotation.SuppressLint
import android.content.SharedPreferences
import com.sergy.weather.data.local.CurrentEntity
import com.sergy.weather.data.local.WeatherLocalDatasource
import com.sergy.weather.data.remote.WeatherRemoteDatasource

import com.sergy.weather.data.remote.dto.toEntity
import com.sergy.weather.utils.NetworkChecker
import io.reactivex.Single

import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    val remoteDatasource: WeatherRemoteDatasource,
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