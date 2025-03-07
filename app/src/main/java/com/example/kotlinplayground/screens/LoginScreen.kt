package com.example.kotlinplayground.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun LoginScreen(modifier: Modifier = Modifier, navController: NavHostController) {
    Text(text="Login Screen")
}