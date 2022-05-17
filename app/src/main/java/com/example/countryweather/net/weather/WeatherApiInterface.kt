package com.example.countryweather.net.weather

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApiInterface {

    @GET("data/2.5/forecast/daily?")
    fun getWeatherData
                (@Query("lat") lat:Double,@Query("lon") lon:Double,
                 @Query("cnt") cnt:Int, @Query("appid") appid:String):Call<List<WeatherDataItem>>
}