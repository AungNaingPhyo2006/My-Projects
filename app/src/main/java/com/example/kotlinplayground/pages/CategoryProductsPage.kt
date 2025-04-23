package com.example.kotlinplayground.pages

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kotlinplayground.components.ProductItemView
import com.example.kotlinplayground.model.ProductModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

@Composable
fun CategoryProductsPage(modifier: Modifier = Modifier, categoryId :String) {

    val productList = remember {
        mutableStateOf<List<ProductModel>>(emptyList())
    }
    LaunchedEffect(Unit) {
        Firebase.firestore.collection("data").document("stock")
            .collection("products")
            .whereEqualTo("category", categoryId)
            .get().addOnCompleteListener(){
                if(it.isSuccessful){
                    val resultList = it.result.documents.mapNotNull { doc ->
                        doc.toObject(ProductModel::class.java)
                    }
                    productList.value = resultList.plus(resultList).plus(resultList)
                }
            }
    }
    Text(
        text = "Category Product Page is $categoryId",
        modifier = Modifier.padding(top = 46.dp)
    )
//    Spacer(modifier = Modifier.height(50.dp))
    LazyColumn (modifier = modifier.fillMaxSize()
        .padding(16.dp)){
        items(productList.value.chunked(2)){ rowItems ->
            Row(modifier = modifier.padding(top = 6.dp)) {
                rowItems.forEach {
                    ProductItemView(product = it,modifier = Modifier.weight(1f))
                }
                if(rowItems.size == 1){
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}