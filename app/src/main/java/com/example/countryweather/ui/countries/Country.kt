package com.example.countryweather.ui.countries

data class Country(
    val id:Int,
    val capital: String?,
    val code: String,
    val name: String,
    val population: Int,
    val region: String,
)