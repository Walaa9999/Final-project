package com.example.countryweather.db

import android.util.Log
import android.widget.TextView
import com.example.countryweather.R
import com.example.countryweather.net.countries.CountryApiInterface
import com.example.countryweather.net.countries.CountryApiService
import com.example.countryweather.net.countries.CountryDataItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class CountriesRepository(private val countryApiService: CountryApiService) {
    private lateinit var disposable:Disposable
    private lateinit var countriesList:List<CountryDataItem>
    init {
        val apiService= CountryApiService.getInstance().create(CountryApiInterface::class.java)

        disposable=apiService.getCountriesList().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {

//                    val stringBuilder = StringBuilder()
//                    for(item in it){
//                        stringBuilder.append("alpha2Code: ${item.alpha2Code}")
//                        stringBuilder.append("\n")
//                    }
//                    findViewById<TextView>(R.id.txt).text = stringBuilder.toString()
                    countriesList=it
                },
                {
                    Log.i("Main Thread","${it.message}")
                })
    }


    fun getCountries()= countriesList
    fun getDis() = disposable

}