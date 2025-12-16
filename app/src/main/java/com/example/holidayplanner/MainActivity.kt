package com.example.holidayplanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.holidayplanner.ui.navigation.AppNavHost
import com.example.holidayplanner.ui.theme.HolidayPlannerTheme
import com.example.holidayplanner.viewmodel.HolidayViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val vm: HolidayViewModel = viewModel()

            HolidayPlannerTheme(darkTheme = vm.isDarkMode) {
                AppNavHost(
                    darkTheme = vm.isDarkMode,
                    onDarkThemeChange = vm::updateDarkMode
                )
            }
        }
    }
}
