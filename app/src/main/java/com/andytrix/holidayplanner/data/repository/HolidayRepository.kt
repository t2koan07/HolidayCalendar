package com.andytrix.holidayplanner.data.repository

import com.andytrix.holidayplanner.data.api.RetrofitClient
import com.andytrix.holidayplanner.data.model.PublicHoliday

class HolidayRepository {

    suspend fun getPublicHolidays(year: Int, countryCode: String): List<PublicHoliday> {
        return RetrofitClient.holidayApi.getPublicHolidays(
            year = year,
            countryCode = countryCode
        )
    }
}
