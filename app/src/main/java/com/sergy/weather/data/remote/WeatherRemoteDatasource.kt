package com.sergy.weather.data.remote

import com.sergy.weather.data.local.CurrentEntity
import com.sergy.weather.data.remote.dto.CurrentResponse
import io.reactivex.Observable
import io.reactivex.Single

interface WeatherRemoteDatasource {

    fun getCurrentWeather (city: String, lang: String, units: String): Single<CurrentResponse>

//    fun getCurrentWeather (lat: Double, lon: Double, lang: String, units: String)
}
