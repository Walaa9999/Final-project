package com.example.countryweather.ui.countries

import androidx.lifecycle.ViewModel
import com.example.countryweather.db.CountriesRepository
import com.example.countryweather.net.countries.CountryApiService

class CountriesViewModel(private val countriesRepository: CountriesRepository):ViewModel() {

    val countriesList = countriesRepository.getCountries()
    val disposable = countriesRepository.getDis()

}