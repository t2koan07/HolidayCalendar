package com.example.holidayplanner.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.holidayplanner.R
import com.example.holidayplanner.data.model.PublicHoliday
import com.example.holidayplanner.data.repository.HolidayRepository
import com.example.holidayplanner.ui.state.HolidayUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class HolidayViewModel(
    private val repository: HolidayRepository = HolidayRepository()
) : ViewModel() {

    var uiState: HolidayUiState by mutableStateOf(HolidayUiState.Idle)
        private set

    private var lastQuery: Pair<Int, String>? = null
    private var lastResult: List<PublicHoliday> = emptyList()

    fun fetchHolidays(yearInput: String, countryInput: String) {
        val year = yearInput.toIntOrNull()
        val countryCode = countryInput.trim().uppercase()

        if (year == null || year < 1900 || year > 2100) {
            uiState = HolidayUiState.Error(R.string.error_invalid_year)
            return
        }

        if (countryCode.length != 2) {
            uiState = HolidayUiState.Error(R.string.error_invalid_country)
            return
        }

        val query = year to countryCode
        if (lastQuery == query && lastResult.isNotEmpty()) {
            uiState = HolidayUiState.Success(lastResult)
            return
        }

        uiState = HolidayUiState.Loading

        viewModelScope.launch {
            try {
                val result = withContext(Dispatchers.IO) {
                    repository.getPublicHolidays(year, countryCode)
                }

                lastQuery = query
                lastResult = result

                uiState = HolidayUiState.Success(result)
            } catch (_: HttpException) {
                uiState = HolidayUiState.Error(R.string.error_http)
            } catch (_: Exception) {
                uiState = HolidayUiState.Error(R.string.error_unknown)
            }
        }
    }
}
