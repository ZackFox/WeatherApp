package com.sergy.weather.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.OnConflictStrategy
import com.sergy.weather.data.local.CurrentEntity
import io.reactivex.Single

@Dao
interface WeatherDao {

    @Query("select * from current_weather where id = 0")
    fun getCurrentWeather(): Single<CurrentEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCurrentWeather (current: CurrentEntity)
}
