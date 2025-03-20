package com.example.kotlinplayground.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.kotlinplayground.AppUtil
import com.example.kotlinplayground.R
import com.example.kotlinplayground.viewmodel.AuthViewModel

@Composable
fun SignupScreen(modifier: Modifier = Modifier, navController: NavHostController, authViewModel: AuthViewModel = viewModel()) {

  var email by remember { mutableStateOf("") }
  var name by remember { mutableStateOf("") }
  var password by remember { mutableStateOf("") }
  var context = LocalContext.current
    Column (modifier = modifier.fillMaxSize().padding(32.dp),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally) {
      Text(text = "Hello Signup",
        modifier = Modifier.fillMaxWidth(),
        style = TextStyle(
          fontSize = 20.sp,
          fontFamily  = FontFamily.Monospace,
          fontWeight = FontWeight.Bold)
      )
      Spacer(modifier = Modifier.height(10.dp))
      Text(text = "Create an Account!",
        modifier = Modifier.fillMaxWidth(),
        style = TextStyle(
          fontSize = 20.sp)
      )
      Spacer(modifier = Modifier.height(20.dp))
      Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = "logo",
        modifier = Modifier.fillMaxWidth()
          .height(200.dp))
      Spacer(modifier = Modifier.height(20.dp))

      OutlinedTextField(
        value = email,
        onValueChange = { email = it },
        label = { Text(text = "Email Address") }
      )
      Spacer(modifier = Modifier.height(10.dp))
      OutlinedTextField(
        value = name,
        onValueChange = { name = it },
        label = { Text(text = "Name") }
      )
      Spacer(modifier = Modifier.height(10.dp))
      OutlinedTextField(
        value = password,
        onValueChange = { password = it },
        label = { Text(text = "Password") },
        visualTransformation = PasswordVisualTransformation()
      )
      Spacer(modifier = Modifier.height(20.dp))
      Button(onClick = {
        authViewModel.signup(email,name,password){success, errorMessage ->
          if(success){
             Log.i("Success", "Success Message")
          }else{
            AppUtil.showToast(context, errorMessage?:"Something went wrong!!")
          }
        }
      }, modifier = Modifier.fillMaxWidth().height(50.dp)) {
        Text(text = "Sign up", fontSize = 22.sp)
      }
    }
}