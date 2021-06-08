package com.sergy.weather.data.remote

import com.sergy.weather.data.WeatherDatasource
import com.sergy.weather.data.remote.api.WeatherApi
import com.sergy.weather.data.remote.dto.CurrentResponse
import io.reactivex.Single
import javax.inject.Inject

class WeatherRemoteDatasourceImpl @Inject constructor(val weatherService: WeatherApi) :
    WeatherDatasource {
    val TAG = "RemoteDatasourceImpl"

    override fun getCurrentWeather(city: String, lang: String, units: String): Single<CurrentResponse> {
        return weatherService.getCurrentWeather(city, lang, units)
    }

//    .subscribeOn(Schedulers.io())
//            .subscribe({
//                // todo isFeatching livedata
//                _currentWeather.postValue(it.toEntity())
//            }, {
//                Log.d(TAG, it.message.toString() )
//            })


//    override fun getCurrentWeather(
//        lat: Double,
//        lon: Double,
//        lang: String,
//        units: String
//    ){
//        return weatherService.api().getCurrentWeather(lat, lon, lang, units)
//    }

    companion object{
        var INSTANCE: WeatherDatasource? = null
        val LOCK = Any();

        fun getInstance(weatherService: WeatherApi): WeatherDatasource {
            return INSTANCE ?: synchronized(LOCK) {
                INSTANCE ?: WeatherRemoteDatasourceImpl(weatherService)
                    .also { INSTANCE = it }
            }
        }
    }
}
