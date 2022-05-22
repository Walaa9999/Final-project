package com.example.countryweather.ui.countries

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countryweather.net.countries.CountryDataItem
import com.example.countryweather.repos.CountriesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CountriesViewModel @Inject constructor
    (private val countriesRepository: CountriesRepository):ViewModel() {

    private val _countriesLiveData = MutableLiveData<List<CountryDataItem>>()
    val countriesLiveData: LiveData<List<CountryDataItem>>
        get() = _countriesLiveData

    private lateinit var _disposable: Disposable
    val disposable :Disposable
        get() = _disposable


    fun loadCountries(){

        _disposable= countriesRepository
            .getCountriesList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _countriesLiveData.value=it
                },
                {
                    Log.i(Thread.currentThread().name,"${it.message}")
                })

    }

}