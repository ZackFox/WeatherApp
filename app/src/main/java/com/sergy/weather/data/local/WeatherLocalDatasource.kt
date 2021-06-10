package com.sergy.weather.data.local

import io.reactivex.Single

interface WeatherLocalDatasource {
    fun getCurrentWeather (): Single<CurrentEntity>

    fun saveCurrentWeather (current: CurrentEntity)
}
