package com.sergy.weather.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import androidx.room.Room
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.sergy.weather.BuildConfig
import com.sergy.weather.data.WeatherRepository
import com.sergy.weather.data.WeatherRepositoryImpl
import com.sergy.weather.data.local.WeatherDatabase
import com.sergy.weather.data.local.WeatherLocalDatasource
import com.sergy.weather.data.local.WeatherLocalDatasourceImpl
import com.sergy.weather.data.remote.WeatherRemoteDatasource
import com.sergy.weather.data.remote.WeatherRemoteDatasourceImpl
import com.sergy.weather.data.remote.api.ApiKeyInterceptor
import com.sergy.weather.data.remote.api.WeatherApi
import com.sergy.weather.utils.NetworkChecker
import com.sergy.weather.utils.NetworkHelper
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class AppModule {

    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(ApiKeyInterceptor())
            .build()
    }

    @Singleton
    @Provides
    fun providerRetrofit(okHttpClient: OkHttpClient): Retrofit{
        return Retrofit
            .Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideWeatherService(retrofit: Retrofit): WeatherApi {
        return retrofit.create(WeatherApi::class.java)
    }



    @Singleton
    @Provides
    fun provideNetworkHelper(context: Context): NetworkChecker {
        return NetworkHelper(context)
    }

    @Singleton
    @Provides
    fun provideDatabase(context: Context): WeatherDatabase {
        return Room.databaseBuilder(
            context, WeatherDatabase::class.java, "InstantWeather.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideWeatherRemoteDatasource(weatherService: WeatherApi): WeatherRemoteDatasource {
        return WeatherRemoteDatasourceImpl(weatherService)
    }

    @Singleton
    @Provides
    fun provideWeatherLocalDatasource(database: WeatherDatabase): WeatherLocalDatasource {
        return WeatherLocalDatasourceImpl(database)
    }

    @Singleton
    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    @Singleton
    @Provides
    fun provideRepository(
        weatherRemoteDatasource: WeatherRemoteDatasource,
        weatherLocalDatasource:WeatherLocalDatasource,
        sharedPreferences: SharedPreferences,
        networkHelper: NetworkChecker
    ): WeatherRepository {
        return WeatherRepositoryImpl(weatherRemoteDatasource, weatherLocalDatasource, sharedPreferences, networkHelper)
    }
}
