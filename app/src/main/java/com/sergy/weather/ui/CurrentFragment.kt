package com.sergy.weather.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.sergy.weather.R
import com.sergy.weather.WeatherApplication
import com.sergy.weather.data.local.CurrentEntity
import kotlinx.android.synthetic.main.fragment_current.*
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DailyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CurrentFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var viewModelFactory: CurrentViewModelFactory

    private lateinit var viewModel: CurrentViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_current, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModelFactory = CurrentViewModelFactory(this,(requireContext().applicationContext as WeatherApplication).repository)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CurrentViewModel::class.java)


        viewModel.getCurrentWeather()


        viewModel.dataLoading.observe(viewLifecycleOwner, {
            if(!it) {
                group_loading.visibility =  View.GONE
                group_current.visibility = View.VISIBLE
            }
        })

        viewModel.currentWeather.observe(viewLifecycleOwner, { setupUi(it) })
    }

    private fun setupUi(it: CurrentEntity) {
        topToolBar1.title = it.cityName
        topToolBar1.subtitle = it.timezone

        view_temp.text = setTemperature(it.temperature.toInt().toString(), "M")
        view_weather_desc.text = it.weatherDescription
        feelsLikeValue.text = setTemperature(it.feelsLike.toString(),"M")
        pressureValue.text = it.pressure.toString()
        humidityValue.text = it.humidity.toString()
        cloudsValue.text = it.clouds.toString()
        visValue.text = it.visibility.toString()
        windSpeedValue.text = it.windSpeed.toString()
        windDirValue.text = it.windDirectionFull

        GlideApp.with(this)
            .load("https://www.weatherbit.io/static/img/icons/${it.weatherIcon}.png")
            .into(weather_icon)
    }

    private fun setTemperature(temperature: String, units: String): String {
        val _units = if (units == "M") "°C" else "°F"
        return "${temperature}${_units}"
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CurrentFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}