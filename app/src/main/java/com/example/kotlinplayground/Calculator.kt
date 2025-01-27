package com.example.kotlinplayground

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

fun  buttonList = listOf(
    "C", ",", "/",
    "7", "8", "9","*",
    "4", "5", "6","+",
    "1", "2", "3","-",
    "AC", "0", ".","="
)

@Composable
fun Calculator(modifier: Modifier = Modifier) {
    Box(modifier = modifier){
       Column(
           modifier = Modifier.fillMaxSize(),
           horizontalAlignment = Alignment.End
       ) {
           Text(
               text="123+123",
                style = TextStyle(
                    color = Color.Red,
                    fontSize = 25.sp,
                    fontFamily = FontFamily.Serif,
                    textAlign = TextAlign.End
                ),
               maxLines = 5,
               overflow = TextOverflow.Ellipsis
           )
           Text(
               text="246",
               style = TextStyle(
                   color = Color.Red,
                   fontSize = 30.sp,
                   fontFamily = FontFamily.Serif,
                   textAlign = TextAlign.End
               ),
               maxLines = 5,
           )
           Spacer(modifier = Modifier.height(10.dp))

       }
    }
}

//<==============>
//https://www.youtube.com/watch?v=lBa6gnavFPw&t=7s
//3:55