package com.andytrix.holidaycalendar.data.model

data class Country(
    val code: String,
    val name: String
)

val supportedCountries = listOf(
    Country("FI", "Finland"),
    Country("SE", "Sweden"),
    Country("NO", "Norway"),
    Country("DK", "Denmark"),
    Country("EE", "Estonia"),
    Country("DE", "Germany"),
    Country("FR", "France"),
    Country("GB", "United Kingdom"),
    Country("US", "United States")
)
