package com.example.kotlinplayground

import android.util.Log
import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {
    fun onButtonClick(btn:String){
        Log.i("Clicked", btn)
    }
}