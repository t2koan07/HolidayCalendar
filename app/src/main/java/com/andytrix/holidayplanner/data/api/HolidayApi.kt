package com.andytrix.holidayplanner.data.api

import com.andytrix.holidayplanner.data.model.PublicHoliday
import retrofit2.http.GET
import retrofit2.http.Path

interface HolidayApi {

    @GET("api/v3/PublicHolidays/{year}/{countryCode}")
    suspend fun getPublicHolidays(
        @Path("year") year: Int,
        @Path("countryCode") countryCode: String
    ): List<PublicHoliday>
}
