package com.example.countryweather.repos

import android.util.Log
import com.example.countryweather.net.countries.CountryApiInterface
import com.example.countryweather.net.countries.CountryDataItem
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CountriesRepository @Inject constructor(private val countriesDataSource:CountryApiInterface) {

   fun getCountriesList(): Single<List<CountryDataItem>>{
       return countriesDataSource.getCountriesList()
   }

}