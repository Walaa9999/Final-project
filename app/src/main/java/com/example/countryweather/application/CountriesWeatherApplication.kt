package com.example.countryweather.application

import android.app.Application
import com.example.countryweather.di.AppComponent
import com.example.countryweather.di.DaggerAppComponent

open class CountriesWeatherApplication:Application() {

    val appComponent: AppComponent by lazy {

        DaggerAppComponent.factory().create(applicationContext)
    }

}