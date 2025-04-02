package com.example.kotlinplayground.pages

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CategoryProductsPage(modifier: Modifier = Modifier,categoryId :String) {
    Text(text= "Category Product Page$categoryId")
}