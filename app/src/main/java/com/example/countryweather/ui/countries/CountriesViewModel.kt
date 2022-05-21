package com.example.countryweather.ui.countries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countryweather.net.countries.CountryDataItem
import com.example.countryweather.repos.CountriesRepository
import javax.inject.Inject

class CountriesViewModel @Inject constructor
    (private val countriesRepository: CountriesRepository):ViewModel() {

    private val _countriesLiveData = MutableLiveData<List<CountryDataItem>>()
    val countriesLiveData: LiveData<List<CountryDataItem>>
        get() = _countriesLiveData

    val disposable = countriesRepository.getDis()


    init {

        _countriesLiveData.value = countriesRepository.getCountries()

    }

}