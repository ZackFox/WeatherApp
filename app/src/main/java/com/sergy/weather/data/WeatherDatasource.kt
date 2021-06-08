package com.sergy.weather.data

import com.sergy.weather.data.local.CurrentEntity
import com.sergy.weather.data.remote.dto.CurrentResponse
import io.reactivex.Observable
import io.reactivex.Single

interface WeatherDatasource {

    interface LoadCurrentWeatherCallback {

        fun onCurrentLoaded(currentEntity: CurrentEntity)

        fun onDataNotAvailable()
    }


    fun getCurrentWeather (city: String, lang: String, units: String): Single<CurrentResponse>

//    fun getCurrentWeather (lat: Double, lon: Double, lang: String, units: String)
}
