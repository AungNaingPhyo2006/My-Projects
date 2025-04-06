package com.example.kotlinplayground.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import com.example.kotlinplayground.model.ProductModel

@Composable
fun ProductItemView(modifier: Modifier = Modifier,product : ProductModel) {
    Column {
        AsyncImage(
            model = product.images.firstOrNull(),
            contentDescription = product.title
        )
    }
}