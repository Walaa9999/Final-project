package com.example.countryweather

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.countryweather.application.CountriesWeatherApplication
import com.example.countryweather.ui.countries.CountriesViewModel
import javax.inject.Inject


class CountryWeatherActivity() : AppCompatActivity() {


    private val countriesViewModel by viewModels<CountriesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_weather)

        CountriesWeatherApplication.appComponent.inject(this)
        countriesViewModel.loadCountries()

        countriesViewModel.countriesLiveData.observe(this){
            findViewById<TextView>(R.id.txt).text=it.toString()

        }


    }




}
