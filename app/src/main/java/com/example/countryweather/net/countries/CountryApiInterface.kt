package com.example.countryweather.net.countries

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface CountryApiInterface {
    @GET("v2/all")
    fun getCountriesList(): Single<List<CountryDataItem>>

    @GET("v2/name/{name}")
    fun getCountriesListByName(@Path("name") name: String): Single<List<CountryDataItem>>
}