package com.sergy.weather.data

import androidx.lifecycle.LiveData
import com.sergy.weather.data.local.CurrentEntity
import com.sergy.weather.data.remote.dto.CurrentResponse
import io.reactivex.Single

interface WeatherRepository {
    fun getCurrentWeather(city: String, lang: String, units: String) : Single<CurrentResponse>
}