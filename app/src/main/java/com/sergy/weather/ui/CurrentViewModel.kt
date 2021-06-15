package com.sergy.weather.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sergy.weather.data.WeatherRepository
import com.sergy.weather.data.local.CurrentEntity
import io.reactivex.schedulers.Schedulers

class CurrentViewModel(private val weatherRepository: WeatherRepository) : ViewModel()  {
    val TAG = "CurrentViewModel"

    private var _currentWeather = MutableLiveData<CurrentEntity>()
    val currentWeather: LiveData<CurrentEntity>
        get() = _currentWeather

    private val _dataLoading = MutableLiveData<Boolean>(true)
    val dataLoading: LiveData<Boolean>
        get() = _dataLoading

    @SuppressLint("CheckResult")
    fun getCurrentWeather() {
        weatherRepository.getCurrentWeather("Magnitogorsk","ru","M")
            .subscribeOn(Schedulers.io())
                .subscribe({
                    _currentWeather.postValue(it)
                    _dataLoading.postValue(false)
            },{
                    Log.d(TAG, "getCurrentWeather: ")
            })
    }
}