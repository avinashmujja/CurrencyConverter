package com.avinash.currencyconverter.data

import com.avinash.currencyconverter.data.model.CurrencyConverterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyConverterAPI {
    @GET("live")
    suspend fun getCurrencyExchangeRates(@Query("access_key") since: String): CurrencyConverterResponse
}