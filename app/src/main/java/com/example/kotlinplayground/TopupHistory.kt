package com.example.kotlinplayground

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TopupHistory(navController: NavController , modifier: Modifier = Modifier, viewModel : TodoViewModel) {
    val todoList by viewModel.todoList.observeAsState()
    Column (modifier = Modifier){
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
        Column (modifier = modifier.fillMaxWidth()) {
            Spacer(modifier =Modifier.padding(8.dp))

            todoList?.let {
                LazyColumn(
                    content = {
                        itemsIndexed(it){ _:Int, item : Todo ->
                            TodoItem(item = item ,onDelete = {viewModel.deleteTodo(item.id)})
                        }
                    }
                )
            }?: Text(modifier = Modifier.fillMaxWidth().padding(8.dp),
                fontSize = 20.sp,
                textAlign = TextAlign.Center,text = "No Items yet!")

        }
    }

}

@Composable
fun TodoItem(item :Todo , onDelete : ()->Unit) {
    Row(modifier = Modifier.fillMaxWidth()
        .padding(8.dp)
        .clip(RoundedCornerShape(16.dp))
        .background(Color.Cyan)
        .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = SimpleDateFormat("hh:mm a , dd/MM/yy", Locale.ENGLISH).format(item.createdAt)
                ,fontSize=10.sp,color = Color.Gray
            )
//            Text(text = item.title, fontSize=20.sp, color = Color.White)

            Text(text = item.operatorName, fontSize=20.sp, color = Color.White)
            Text(text = item.phoneNumber, fontSize=20.sp, color = Color.White)
            Text(text = item.packageName, fontSize=20.sp, color = Color.White)
            Text(text = item.price, fontSize=20.sp, color = Color.White)
        }
        IconButton(onClick = onDelete) {
            Icon(painter = painterResource(id = R.drawable.baseline_delete_24), contentDescription = "Delete",
                tint = Color.White)
        }
    }
}