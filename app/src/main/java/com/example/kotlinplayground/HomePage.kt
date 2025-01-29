package com.example.kotlinplayground

import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.kwabenaberko.newsapilib.models.Article

@Composable
fun HomePage(newsViewModel: NewsViewModel, navController: NavController) {
    val articles by newsViewModel.articles.observeAsState(emptyList())

    Column (modifier = Modifier.fillMaxWidth()){
        CategoriesBar(newsViewModel)
        LazyColumn  (modifier = Modifier.fillMaxWidth()) {
           items(articles){article->
               ArticleItem(article, navController)
           }
        }
    }
}

@Composable
fun ArticleItem(article: Article, navController: NavController) {
    Card (modifier = Modifier.padding(8.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
        onClick = {
            navController.navigate(NewsArticleScreen(article.url))
        }){
        Row ( modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model= article.urlToImage?:"https://dzinejs.lv/wp-content/plugins/lightbox/images/No-image-found.jpg",
                contentDescription = "Article Image",
                modifier= Modifier
                    .size(80.dp)
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp)){
                Text(text=article.title, fontWeight = FontWeight.Bold, maxLines = 3)
                Text(text=article.source.name, fontSize= 14.sp , maxLines = 1)

            }
        }
    }
}

@Composable
fun CategoriesBar(newsViewModel: NewsViewModel) {
    var searchQuery by remember{mutableStateOf("")}
    var isSearchExtended by remember { mutableStateOf(false) }

    val categoriesList = listOf(
        "GENERAL",
        "BUSINESS",
        "ENTERTAINMENT",
        "HEALTH",
        "SCIENCE",
        "SPORTS",
        "TECHNOLOGY"
    )
    Row(modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
        verticalAlignment = Alignment.CenterVertically){
        if(isSearchExtended){
            OutlinedTextField(
                modifier = Modifier.padding(8.dp)
                .height(50.dp).border(1.dp, Color.Gray, CircleShape).clip(CircleShape),
                value= searchQuery,
                onValueChange = {
                    searchQuery = it
                },
                trailingIcon = {
                    IconButton(onClick = {isSearchExtended = false
                    if(searchQuery.isNotEmpty()){
                        newsViewModel.fetchEverythingWithQuery(searchQuery)
                    }
                    }) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
                    }
                }
            )
        }else{
            IconButton(onClick = {isSearchExtended = true}) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
            }
        }
        categoriesList.forEach{category->
            Button(onClick ={newsViewModel.fetchNewsTopHeadLines(category)} ,modifier = Modifier.padding(4.dp)) {
                Text(text=category)
            }
        }
    }
}