package com.sergy.weather.ui

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.sergy.weather.R

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.settings)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        (activity as? AppCompatActivity)?.supportActionBar?.title = "Settings"
//        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = null
    }
}