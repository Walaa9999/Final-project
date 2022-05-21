package com.example.countryweather.repos

import android.util.Log
import com.example.countryweather.net.countries.CountryApiInterface
import com.example.countryweather.net.countries.CountryDataItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CountriesRepository @Inject constructor(private val countriesDataSource:CountryApiInterface) {

    private var disposable:Disposable
    private lateinit var countriesList:List<CountryDataItem>
    init {

        disposable= countriesDataSource
            .getCountriesList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    countriesList=it
                },
                {
                    Log.i(Thread.currentThread().name,"${it.message}")
                })
    }

    fun getCountries() = countriesList
    fun getDis() = disposable

}