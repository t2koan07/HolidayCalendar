package com.example.holidayplanner.ui.state

import com.example.holidayplanner.data.model.PublicHoliday

sealed interface HolidayUiState {
    data object Idle : HolidayUiState
    data object Loading : HolidayUiState
    data class Success(val holidays: List<PublicHoliday>) : HolidayUiState
    data class Error(val messageResId: Int) : HolidayUiState
}
