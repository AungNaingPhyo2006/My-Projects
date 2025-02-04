package com.example.kotlinplayground

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(todoViewModel: TodoViewModel) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),

    ) { innerPadding ->
        TodoListPage( modifier = Modifier.padding(innerPadding), todoViewModel)
    }
}

