package com.sergy.weather

import android.app.Application
import androidx.preference.PreferenceManager
import com.sergy.weather.data.WeatherRepository
import com.sergy.weather.di.DaggerAppComponent
import javax.inject.Inject

class WeatherApplication : Application() {

    @Inject
    lateinit var repository: WeatherRepository

    override fun onCreate() {
        super.onCreate()

        PreferenceManager.setDefaultValues(this,R.xml.settings,false)

        DaggerAppComponent
            .builder()
            .application(this)
            .build()
            .inject(this)
    }
}