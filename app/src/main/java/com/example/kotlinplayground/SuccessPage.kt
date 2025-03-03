package com.example.kotlinplayground

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuccessPage(navController: NavController, modifier: Modifier = Modifier,viewModel: TodoViewModel) {
    val packageName = navController.currentBackStackEntry?.arguments?.getString("packageName") ?: "Unknown"
    val price = navController.currentBackStackEntry?.arguments?.getString("price") ?: "0"
    val operatorName = navController.currentBackStackEntry?.arguments?.getString("operatorName") ?: "Unknown"
    val phoneNumber = navController.currentBackStackEntry?.arguments?.getString("phoneNumber") ?: "Unknown"

    Column(modifier = Modifier) {
        // TopAppBar with back arrow and title
        TopAppBar(
            title = { Text("Success") },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = androidx.compose.material3.MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = androidx.compose.material3.MaterialTheme.colorScheme.primary,
            )
        )

        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Success")
            Text(text = "Operator Name: $operatorName")
            Text(text = "Phone Number: $phoneNumber")

            Text(text = "Selected Package: $packageName")
            Text(text = "Price: $price MMK")
            Button(onClick = {
                navController.navigate(Routes.mobileTopup)
            }) {
                Text(text = "To Mobile Topup")
            }
        }
    }
}