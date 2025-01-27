package com.example.kotlinplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kotlinplayground.ui.theme.KotlinPlayGroundTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KotlinPlayGroundTheme {
                Surface (modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                    ){
                    Greeting(name = "Aung Naing Phyo")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Box(
        modifier = Modifier.size(400.dp).background(Color.Cyan)
    ) {

        Text(
            text = "Hello $name!",
            fontSize = 16.sp,
            color = Color.White,
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = "Android Developer",
            fontSize = 16.sp,
            color = Color.White,
//            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }

}
@Composable
fun Testing(name: String){
    val offset = Offset(5.0f, 10.0f)
    Text(
        text = name,
        style = TextStyle(
            fontSize = 24.sp,
            shadow = Shadow(
                color = Color.Blue, offset = offset, blurRadius = 3f
            )
        )
    )
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KotlinPlayGroundTheme {
        Column {
            Testing("Aung Naing Phyo")
            Greeting("Android Developer!")
        }
    }
}