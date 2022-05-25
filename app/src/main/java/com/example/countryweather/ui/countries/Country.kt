package com.example.countryweather.ui.countries

data class Country( val capital: String?,
                    val code: String,
                    val name: String,
                    val population: Int,
                    val region: String,
                    val latlng: List<Double>?)