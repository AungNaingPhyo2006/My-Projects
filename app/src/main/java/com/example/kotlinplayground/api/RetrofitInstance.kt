//package com.example.kotlinplayground.api
//
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import retrofit2.create
//
////http://api.weatherapi.com/v1/current.json?key=ff67d0e497a24c5599782935252801&q=London&aqi=no
//object RetrofitInstance {
//
//    private const val baseUrl = "https://api.weatherapi.com";
//
//    private fun getInstance() : Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(baseUrl)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
//
//    val weatherApi : WeatherApi = getInstance().create(WeatherApi::class.java)
//
//}