package com.example.countryweather.repos

import com.example.countryweather.net.weather.WeatherApiInterface
import com.example.countryweather.net.weather.WeatherDataItem
import io.reactivex.Single
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val weatherDataSource: WeatherApiInterface) {

    fun getWeather(lat:Double,lng:Double): Single<List<WeatherDataItem>> {
        return weatherDataSource.getWeatherData(lat,lng,2,"1867722b6af87e1d0388e10c5a94be34")
    }



}