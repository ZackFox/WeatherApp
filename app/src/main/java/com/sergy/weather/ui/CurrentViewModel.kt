package com.sergy.weather.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sergy.weather.data.WeatherRepository
import com.sergy.weather.data.local.CurrentEntity
import io.reactivex.schedulers.Schedulers

class CurrentViewModel(private val weatherRepository: WeatherRepository) : ViewModel()  {
    val TAG = "CurrentViewModel"

    private val _currentWeather = MutableLiveData<CurrentEntity>()
    val currentWeather : LiveData<CurrentEntity>
        get() = _currentWeather

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean>
        get() = _dataLoading

    fun getCurrentWeather() {
        weatherRepository.getCurrentWeather("Moscow","ru","M")
            .subscribeOn(Schedulers.io())
            .subscribe({
                Log.d(TAG,  it.data[0].toString())
            },{
                Log.d(TAG, "Error " + it.message.toString() )
            })
    }
}