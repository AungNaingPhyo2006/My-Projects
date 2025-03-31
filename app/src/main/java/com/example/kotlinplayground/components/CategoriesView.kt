package com.example.kotlinplayground.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.kotlinplayground.model.CategoryModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

@Composable
fun CategoriesView(modifier: Modifier = Modifier) {
    val categoryList = remember {
        mutableStateOf<List<CategoryModel>>(emptyList())
    }
    LaunchedEffect(Unit) {
        Firebase.firestore.collection("data").document("stock")
            .collection("categories")
            .get().addOnCompleteListener(){
                if(it.isSuccessful){
                    categoryList.value = it.result.documents.mapNotNull { doc ->
                        doc.toObject(CategoryModel::class.java)
                    }

                }
            }

    }
    Text(text = categoryList.value.toString())
}