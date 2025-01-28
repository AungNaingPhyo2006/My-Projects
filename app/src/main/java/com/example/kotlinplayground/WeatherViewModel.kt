package com.example.kotlinplayground

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinplayground.api.Constant
import com.example.kotlinplayground.api.NetworkResponse
import com.example.kotlinplayground.api.RetrofitInstance
import com.example.kotlinplayground.api.WeatherModel
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    private val weatherApi = RetrofitInstance.weatherApi
    private val _weatherResult = MutableLiveData<NetworkResponse<WeatherModel>>()
    private val weatherResult : LiveData<NetworkResponse<WeatherModel>> = _weatherResult

    fun getData(city : String){
     viewModelScope.launch {
         val response = weatherApi.getWeather(Constant.apiKey,city)
         if(response.isSuccessful){
             response.body()?.let {
                 _weatherResult.value = NetworkResponse.Success(it)
             }
//             Log.i("Response", response.body().toString())
         }else{
             _weatherResult.value = NetworkResponse.Error("Failed to load data.")
//             Log.i("Errors", response.message())
         }
     }
 }
}