package com.example.kotlinplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinplayground.ui.theme.KotlinPlayGroundTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val todoViewModel = ViewModelProvider(this)[TodoViewModel::class.java]
        setContent {
            KotlinPlayGroundTheme {
                MainScreen(todoViewModel)
                }
            }
        }
    }


