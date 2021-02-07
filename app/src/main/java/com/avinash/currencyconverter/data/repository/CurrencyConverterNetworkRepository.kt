package com.avinash.currencyconverter.data.repository

import com.avinash.currencyconverter.data.CurrencyConverterAPI
import com.avinash.currencyconverter.data.model.CurrencyConverterResponse
import javax.inject.Inject

interface CurrencyConverterNetworkRepository {
    suspend fun getCurrencyExchangeRates(): CurrencyConverterResponse?
}

class CurrencyConverterNetworkRepositoryImpl @Inject constructor(
    private val currencyConverterAPI: CurrencyConverterAPI
) : CurrencyConverterNetworkRepository {
    override suspend fun getCurrencyExchangeRates() =
        currencyConverterAPI.getCurrencyExchangeRates("35be00cf29ad78e76550b46ba2389a65")
}