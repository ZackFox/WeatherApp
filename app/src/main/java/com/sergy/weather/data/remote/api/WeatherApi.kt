package com.sergy.weather.data.remote.api

import com.sergy.weather.data.remote.dto.CurrentResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("current")
    fun getCurrentWeather(
        @Query("city") city: String,
        @Query("lang") lang: String = "ru",
        @Query("units") units: String = "M"
    ): Single<CurrentResponse>

    @GET("current")
    fun getCurrentWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("lang") lang: String = "ru",
        @Query("units") units: String = "M"
    ): Single<CurrentResponse>
}