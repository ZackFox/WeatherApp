package com.sergy.weather

import android.app.Application
import com.sergy.weather.data.WeatherRepository
import com.sergy.weather.di.DaggerAppComponent
import javax.inject.Inject

class WeatherApplication : Application() {

    @Inject
    lateinit var repository: WeatherRepository

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent
            .builder()
            .application(this)
            .build()
            .inject(this)
    }
}