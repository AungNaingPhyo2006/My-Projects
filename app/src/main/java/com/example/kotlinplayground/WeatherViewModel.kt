package com.example.kotlinplayground

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinplayground.api.Constant
import com.example.kotlinplayground.api.RetrofitInstance
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    private val weatherApi = RetrofitInstance.weatherApi
    fun getData(city : String){
     viewModelScope.launch {
         val response = weatherApi.getWeather(Constant.apiKey,city)
         if(response.isSuccessful){
             Log.i("Response", response.body().toString())
         }else{
             Log.i("Errors", response.message())

         }
     }
 }
}