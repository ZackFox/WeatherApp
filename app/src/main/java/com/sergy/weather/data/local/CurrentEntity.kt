package com.sergy.weather.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

class CurrentEntity(
    lat: Double,
    lon: Double,
    cityName: String,
    countryСode: String,
    timezone: String,
    temperature: Double,
    feelsLike: Double,
    pressure: Double,
    humidity: Double,
    partOfDay: String,
    clouds: Int,
    visibility: Double,
    windSpeed: Double,
    windDirectionFull: String,
    icon: String,
    code: Int,
    description: String
) {
    @Entity(tableName = "current_weather")
    data class CurrentEntity(
        @ColumnInfo(name = "temperature") val temperature: Double,
        @ColumnInfo(name = "feelsLike") val feelsLike: Double,
        @ColumnInfo(name = "pressure") val pressure: Double,
        @ColumnInfo(name = "humidity") val humidity: Double,
        @ColumnInfo(name = "pod") val partOfDay: String,
        @ColumnInfo(name = "clouds") val clouds: Int,
        @ColumnInfo(name = "visibility") val visibility: Double,
        @ColumnInfo(name = "wind_speed") val windSpeed: Double,
        @ColumnInfo(name = "wind_dir_full") val windDirectionFull: String,
        @ColumnInfo(name = "weather_icon") val weatherIcon: String,
        @ColumnInfo(name = "weather_code") val weatherCode: Int,
        @ColumnInfo(name = "weather_description") val weatherDescription: String
    ) {
        @PrimaryKey(autoGenerate = false)
        var id: Int = 0
    }
}
