package com.example.kotlinplayground

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(todoViewModel: TodoViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.mobileTopup, builder = {
        composable(Routes.mobileTopup) {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
            ) { innerPadding ->
                MobileTopup(navController,modifier = Modifier.padding(innerPadding),todoViewModel)
            }
        }
        composable(Routes.successScreen + "/{packageName}/{price}/{operatorName}/{phoneNumber}") {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
            ) { innerPadding ->
                SuccessPage( navController ,modifier = Modifier.padding(innerPadding),todoViewModel)
            }
        }
        composable(Routes.todoListPage) {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
            ) { innerPadding ->
                TodoListPage(modifier = Modifier.padding(innerPadding), todoViewModel)
            }
        }
    }
    )

}

