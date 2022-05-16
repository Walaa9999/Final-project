package com.example.countryweather

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.countryweather.net.countries.CountryApiService
import com.example.countryweather.net.countries.CountryDataItem
import com.example.countryweather.net.countries.CountryEndpointInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder


class CountryWeatherApplication : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getCountriesData()
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
