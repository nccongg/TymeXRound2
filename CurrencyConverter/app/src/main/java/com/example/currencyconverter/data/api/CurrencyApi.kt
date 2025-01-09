package com.example.currencyconverter.data.api

import retrofit2.http.GET
import retrofit2.http.Query

data class CurrencyRatesResponse(
    val success: Boolean,
    val timestamp: Long,
    val base: String,
    val date: String,
    val rates: Map<String, Double>,
    val error: ErrorResponse? = null
)

data class ErrorResponse(
    val code: Int,
    val type: String,
    val info: String
)

interface CurrencyApi {
    @GET("v1/latest")
    suspend fun getCurrencyRates(
        @Query("access_key") accessKey: String
    ): CurrencyRatesResponse
}
