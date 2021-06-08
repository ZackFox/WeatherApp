package com.sergy.weather.ui

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.savedstate.SavedStateRegistryOwner
import com.sergy.weather.data.WeatherRepository

class CurrentViewModelFactory (
    owner: SavedStateRegistryOwner,
    val repository: WeatherRepository,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {


    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ) = when {
        modelClass.isAssignableFrom(CurrentViewModel::class.java) ->
            CurrentViewModel(repository)
        else ->
            throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    } as T
}
