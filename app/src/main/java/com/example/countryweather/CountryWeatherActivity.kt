package com.example.countryweather

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.countryweather.net.countries.CountryApiService
import com.example.countryweather.net.countries.CountryApiInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class CountryWeatherActivity : AppCompatActivity() {

    private lateinit var disposable: Disposable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_weather)

        getCountriesData()

    }

    private fun getCountriesData() {

    }


    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}
