package com.example.countryweather

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.countryweather.net.countries.CountryApiService
import com.example.countryweather.net.countries.CountryDataItem
import com.example.countryweather.net.countries.CountryEndpointInterface
import com.example.countryweather.net.weather.WeatherApiInterface
import com.example.countryweather.net.weather.WeatherApiService
import com.example.countryweather.net.weather.WeatherDataItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.text.StringBuilder


class CountryWeatherApplication : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getWeatherData()
    }

    private fun getWeatherData() {
        val apiService= WeatherApiService.getInstance().create(WeatherApiInterface::class.java)
        apiService.getWeatherData(20.0,60.25,2,"1867722b6af87e1d0388e10c5a94be34").enqueue(object : Callback<List<WeatherDataItem>?> {
            override fun onResponse(
                call: Call<List<WeatherDataItem>?>,
                response: Response<List<WeatherDataItem>?>
            ) {
                val responseBody = response.body()!!
                val stringBuilder = StringBuilder()
                for(data in responseBody){
                    stringBuilder.append("hum : ${data.humidity}")
                    stringBuilder.append("Tmp : ${data.temp.max}")
                    stringBuilder.append("Tmp : ${data.temp.min}")
                }

                findViewById<TextView>(R.id.txt).text = response.toString()

            }

            override fun onFailure(call: Call<List<WeatherDataItem>?>, t: Throwable) {
                Log.i("Main", "${t.message}")
            }
        })
    }

    private fun getCountriesData() {
        val apiService= CountryApiService.getInstance().create(CountryEndpointInterface::class.java)
        apiService.getCountriesList().enqueue(object : Callback<List<CountryDataItem>?> {
            override fun onResponse(
                call: Call<List<CountryDataItem>?>,
                response: Response<List<CountryDataItem>?>
            ) {
                val stringBuilder = StringBuilder()
                val responseBody = response.body()!!

                for(data in responseBody){
                    stringBuilder.append("alpha2Code: ${data.alpha2Code}")
                    stringBuilder.append("\n")
                }


                findViewById<TextView>(R.id.txt).text = stringBuilder.toString()
            }

            override fun onFailure(call: Call<List<CountryDataItem>?>, t: Throwable) {
                Log.i("Main", "${t.message}")

            }
        })
    }
}
