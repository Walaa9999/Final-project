package com.example.countryweather.net.weather

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiInterface {
    @GET("data/2.5/forecast/daily")
    fun getWeatherData(
        @Query("appid") appid: String, @Query("q") q: String, @Query("cnt") cnt: Int,
    ): Single<WeatherDataItem>
}