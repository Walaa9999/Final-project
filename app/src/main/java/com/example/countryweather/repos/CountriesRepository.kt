package com.example.countryweather.repos

import com.example.countryweather.net.countries.CountryApiInterface
import com.example.countryweather.net.countries.CountryDataItem
import io.reactivex.Single
import javax.inject.Inject

class CountriesRepository @Inject constructor(private val countriesDataSource: CountryApiInterface) {

    fun getCountriesList(): Single<List<CountryDataItem>> {
        return countriesDataSource.getCountriesList()
    }

    fun getCountriesListByName(name: String): Single<List<CountryDataItem>> {
        return countriesDataSource.getCountriesListByName(name)
    }

}