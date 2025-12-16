package com.example.holidayplanner.data.api

import com.example.holidayplanner.data.model.PublicHoliday
import retrofit2.http.GET
import retrofit2.http.Path

interface HolidayApi {

    @GET("api/v3/PublicHolidays/{year}/{countryCode}")
    suspend fun getPublicHolidays(
        @Path("year") year: Int,
        @Path("countryCode") countryCode: String
    ): List<PublicHoliday>
}
