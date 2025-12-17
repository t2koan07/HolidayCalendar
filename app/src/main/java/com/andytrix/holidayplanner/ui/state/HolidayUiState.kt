package com.andytrix.holidayplanner.ui.state

import com.andytrix.holidayplanner.data.model.PublicHoliday

sealed interface HolidayUiState {
    data object Idle : HolidayUiState
    data object Loading : HolidayUiState
    data class Success(val holidays: List<PublicHoliday>) : HolidayUiState
    data class Error(val messageResId: Int) : HolidayUiState
}
