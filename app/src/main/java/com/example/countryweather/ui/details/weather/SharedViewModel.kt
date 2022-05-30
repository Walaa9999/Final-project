package com.example.countryweather.ui.details.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {

    var name = MutableLiveData<String>()
    fun sendName(x: String) {
        name.value = x
    }

}