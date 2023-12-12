package ru.cft.shift2023winter.data

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetRepository {

    companion object {
        const val BASE_URL = "https://date.nager.at/api/v3/"
        const val CONNECT_TIME = 5L
        const val WRITE_TIME = 5L
        const val READ_TIME = 5L
    }

    private val gson = GsonBuilder()
        .create()

    private val retrofit = Retrofit.Builder()
        .client(provideOkHttpClient())
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    private fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIME, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIME, TimeUnit.SECONDS)
            .readTimeout(READ_TIME, TimeUnit.SECONDS)
            .build()

    private val NetApi by lazy {
        retrofit.create(NetApi::class.java)
    }

    suspend fun getAll(): List <Country> =
        NetApi.getAll()

    suspend fun getHolidayByCountryCode(countryCode: String): List <Holidays> =
        NetApi.getHolidayByCountryCode(countryCode)
}