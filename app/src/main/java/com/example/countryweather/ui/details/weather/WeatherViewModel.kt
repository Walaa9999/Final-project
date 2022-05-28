package com.example.countryweather.ui.details.weather

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countryweather.application.CountriesWeatherApplication
import com.example.countryweather.net.weather.WeatherDataItem
import com.example.countryweather.repos.WeatherRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WeatherViewModel : ViewModel() {

   var name:String = ""

    @Inject
    lateinit var weatherRepository: WeatherRepository

    init {
        CountriesWeatherApplication.appComponent.inject(this)
    }

    private val _countriesLiveData = MutableLiveData<List<WeatherDataItem>>()
    val weatherLiveData: LiveData<List<WeatherDataItem>>
    get() = _countriesLiveData

    private val compositeDisposable = CompositeDisposable()


    fun getWeatherForTwoDays() {

        compositeDisposable.add(
            weatherRepository
                .getWeather(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        _countriesLiveData.value = it

                    },
                    {
                        Log.i("Error", "${it.message}")
                    })
        )
    }




    fun dispose() {
        compositeDisposable.dispose()
    }


}