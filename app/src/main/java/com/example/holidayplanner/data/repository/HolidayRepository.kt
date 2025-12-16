package com.example.holidayplanner.data.repository

import com.example.holidayplanner.data.api.RetrofitClient
import com.example.holidayplanner.data.model.PublicHoliday

class HolidayRepository {

    suspend fun getPublicHolidays(year: Int, countryCode: String): List<PublicHoliday> {
        return RetrofitClient.holidayApi.getPublicHolidays(
            year = year,
            countryCode = countryCode
        )
    }
}
