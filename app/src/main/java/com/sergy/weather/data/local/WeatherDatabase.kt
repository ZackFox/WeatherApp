package com.sergy.weather.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sergy.weather.data.local.dao.WeatherDao


@Database(entities = [CurrentEntity::class], version = 1)
abstract class WeatherDatabase : RoomDatabase() {

    abstract val weatherDao: WeatherDao
}