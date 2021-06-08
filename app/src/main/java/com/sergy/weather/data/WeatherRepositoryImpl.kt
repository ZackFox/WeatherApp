package com.sergy.weather.data

import com.sergy.weather.data.remote.dto.CurrentResponse
import io.reactivex.Single
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    val remoteDatasource: WeatherDatasource,
) : WeatherRepository {
    val TAG = "WeatherRepository"

    override fun getCurrentWeather(city: String, lang: String, units: String): Single<CurrentResponse> {
        // fetch data from network if it available
        return remoteDatasource.getCurrentWeather(city,lang,units)
    }

}