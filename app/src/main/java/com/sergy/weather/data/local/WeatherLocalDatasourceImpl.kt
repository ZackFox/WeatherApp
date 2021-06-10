package com.sergy.weather.data.local

import io.reactivex.Single
import javax.inject.Inject

class WeatherLocalDatasourceImpl @Inject constructor (private val database: WeatherDatabase) : WeatherLocalDatasource {
    override fun getCurrentWeather(): Single<CurrentEntity> {
        return database.weatherDao.getCurrentWeather()
    }

    override fun saveCurrentWeather(current: CurrentEntity) {
        database.weatherDao.saveCurrentWeather(current)
    }
}