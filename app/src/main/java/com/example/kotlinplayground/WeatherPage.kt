package com.example.kotlinplayground

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.kotlinplayground.api.NetworkResponse
import com.example.kotlinplayground.api.WeatherModel

@Composable
fun WeatherPage(modifier: Modifier = Modifier, viewModel : WeatherViewModel) {
    val city = remember { mutableStateOf("") }
    val weatherResult = viewModel.weatherResult.observeAsState()
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OutlinedTextField(
                value = city.value,
                onValueChange = { city.value = it },
                label = { Text("Search for any location") },
                modifier = Modifier.weight(1f) // Makes the text field take up available space
            )
            Box(
                modifier = Modifier
                    .padding(8.dp)
            ){
                IconButton(
                    onClick = {
                        viewModel.getData(city.toString())
                        keyboardController?.hide()
                              },
                    modifier = Modifier.padding(4.dp),
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search for any location",
                        modifier = Modifier.size(40.dp)
                    )
                }
            }

        }

        when(val result = weatherResult.value){
            is NetworkResponse.Error -> {
                Text(text = result.message)
            }
            NetworkResponse.Loading -> {
                CircularProgressIndicator()
            }
            is NetworkResponse.Success -> {
                WeatherDetails(modifier = Modifier,data = result.data)
            }
            null -> {
                Text(text = "No Data Found!")

            }
        }
    }
}

@Composable
fun WeatherDetails(modifier : Modifier,data : WeatherModel) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Bottom
        ){
           Icon(
               imageVector = Icons.Default.LocationOn,
               contentDescription = "Location Icon",
               modifier = modifier.size(40.dp)
           )
            Text(text = data.location.name, fontSize = 30.sp)
            Spacer(modifier = modifier.width(8.dp))
            Text(text = data.location.country, fontSize = 18.sp, color = Color.Gray)
        }
        Spacer(modifier = modifier.height(16.dp))
        Text(text = "${data.current.temp_c} ° c", fontSize = 56.sp, color = Color.Gray,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        AsyncImage(
            model = "https:${data.current.condition.icon}".replace("64x64", "128x128"),
            contentDescription = "Current condition Icon",
            modifier = modifier.size(160.dp)
        )
        Text(text = data.current.condition.text,
            fontSize = 20.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = modifier.height(16.dp))

        Card {
            Column (modifier = modifier.fillMaxWidth()) {
                Row(modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround){
                    WeatherKeyVal( modifier,"Humidity",data.current.humidity.toString())
                    WeatherKeyVal( modifier,"Wind Speed",data.current.wind_kph + " km/h")
                }
                Row(modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround){
                    WeatherKeyVal( modifier,"UV",data.current.uv)
                    WeatherKeyVal( modifier,"Participation",data.current.precip_mm + " mm")
                }
                Row(modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround){
                    WeatherKeyVal( modifier,"Local Time",data.location.localtime.split(" ")[1])
                    WeatherKeyVal( modifier,"Local Date",data.location.localtime.split(" ")[0])
                }
            }
        }
    }
}

@Composable
fun WeatherKeyVal( modifier: Modifier,key : String , value : String) {
    Column(modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally){
        Text(text = value,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Text(text = key,
            fontSize = 20.sp,
            color = Color.Gray,
            fontWeight = FontWeight.SemiBold

        )
    }
}
