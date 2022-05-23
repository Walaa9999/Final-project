package com.example.countryweather

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.countryweather.application.CountriesWeatherApplication
import com.example.countryweather.ui.countries.CountriesListAdapter
import com.example.countryweather.ui.countries.CountriesViewModel
import com.example.countryweather.ui.countries.Country
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class CountryWeatherActivity : AppCompatActivity() {


    private val countriesViewModel by viewModels<CountriesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_weather)

        countriesViewModel.loadCountries()

        val list = ArrayList<Country>()

        countriesViewModel.countriesLiveData.observe(this){
            for(item in it){
                list.add(Country(item.name, item.population, item.alpha2Code))
            }

            val recyclerView = findViewById<RecyclerView>(R.id.countriesRecyclerView)
            val adapter = CountriesListAdapter(list)
            val manager = LinearLayoutManager(this)
            recyclerView.layoutManager = manager
            recyclerView.adapter = adapter
        }

//        val observable = Observable.create<String> {
//            findViewById<EditText>(R.id.searchBox).doOnTextChanged { text, start, before, count ->
//                it.onNext(text.toString())
//            }
//        }.debounce(1,TimeUnit.SECONDS)
//
//
//        observable.subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                {
//                       val searchedList = list.forEach()
//                },
//                {
//                    Log.i(Thread.currentThread().toString(),it.message.toString())
//                }
//
//            )





    }


    override fun onDestroy() {
        super.onDestroy()
        countriesViewModel.dispose()
    }



}
