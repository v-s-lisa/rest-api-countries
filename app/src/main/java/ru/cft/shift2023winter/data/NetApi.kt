package ru.cft.shift2023winter.data

import retrofit2.http.GET
import retrofit2.http.Path

interface NetApi {

    @GET("AvailableCountries")
    suspend fun getAll(): List <Country>

    @GET("PublicHolidays/2023/{countryCode}")
    suspend fun getHolidayByCountryCode(@Path("countryCode") CountryCode: String): List <Holidays>
}