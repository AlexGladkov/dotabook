package com.agladkov.dotabook.activity

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.agladkov.dotabook.fragments.CarryScreen

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val tabs = listOf("Home", "Dashboard", "Notifications")
            var currentTab by remember { mutableStateOf(tabs.first()) }

            Column(modifier = Modifier.fillMaxSize()) {
                Box(modifier = Modifier.weight(1f)) {
                    when (currentTab) {
//                        "Home" -> CarryScreen(viewModel = , navController = )
                        else -> Text(currentTab)
                    }
                }

                BottomNavigation(
                    backgroundColor = Color.White
                ) {
                    tabs.forEach { value ->
                        val isSelected = value == currentTab
                        BottomNavigationItem(selected = isSelected, onClick = {
                            currentTab = value
                        }, icon = {

                        }, label = {
                            Text(value, color = if (isSelected) Color.Black else Color.LightGray)
                        })
                    }
                }
            }
        }
    }
}