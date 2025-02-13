package com.example.kotlinplayground

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    Column(modifier= modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(modifier = modifier.size(200.dp),painter = painterResource(id= R.drawable.mom_image) , contentDescription = "Image")
        Text(
            text="Welcome Back",
            fontSize = 28.sp, fontWeight = FontWeight.Bold
        )
        Spacer(modifier= modifier.height(4.dp))
        Text(text="Login to your account!")
        Spacer(modifier= modifier.height(4.dp))
        OutlinedTextField(value="", onValueChange = {},
            label = {
                Text(text="Email Address")
            } )

        OutlinedTextField(value="", onValueChange = {},
            label = {
                Text(text="Password")
            } )

        Spacer(modifier= modifier.height(2.dp))

        Button(onClick = {}) {
            Text(text="Login")
        }

//        TextButton(onClick = {}) {
//            Text(text="Forgot Password!")
//        }
        Text(text="Forgot Password!", modifier = modifier.clickable {  })
        Text(text="Sign in With:", modifier = modifier.clickable {  })

    }


}