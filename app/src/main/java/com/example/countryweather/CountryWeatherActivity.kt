package com.example.countryweather

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.countryweather.ui.countries.CountriesViewModel
import javax.inject.Inject


class CountryWeatherActivity() : AppCompatActivity() {

    @Inject
    lateinit var countriesViewModel: CountriesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_weather)




    }




}
