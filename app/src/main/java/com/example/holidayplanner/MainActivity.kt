package com.example.holidayplanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.holidayplanner.ui.navigation.AppNavHost
import com.example.holidayplanner.ui.theme.HolidayPlannerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val darkTheme = rememberSaveable { mutableStateOf(false) }

            HolidayPlannerTheme(darkTheme = darkTheme.value) {
                AppNavHost(
                    darkTheme = darkTheme.value,
                    onDarkThemeChange = { darkTheme.value = it }
                )
            }
        }
    }
}
