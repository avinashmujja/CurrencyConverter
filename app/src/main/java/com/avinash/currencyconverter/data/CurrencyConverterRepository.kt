package com.avinash.currencyconverter.data

import com.avinash.currencyconverter.commons.Constants.CURRENCY_CACHE_TIME
import com.avinash.currencyconverter.commons.Constants.EMPTY_STRING
import com.avinash.currencyconverter.commons.CurrencyPreferences
import com.avinash.currencyconverter.commons.extensions.getCurrentDateAndTime
import com.avinash.currencyconverter.commons.extensions.isCacheTimeOut
import com.avinash.currencyconverter.data.model.CurrencyConverterResponse
import com.avinash.currencyconverter.data.repository.CurrencyConverterLocalRepository
import com.avinash.currencyconverter.data.repository.CurrencyConverterNetworkRepository
import javax.inject.Inject

interface CurrencyConverterRepository {
    suspend fun getCurrencyDetails(cacheTimePeriod: Long = 0L): CurrencyConverterResponse?
    suspend fun getCachedCurrencyDetailsDate(): CurrencyConverterResponse?
    suspend fun clearCache()
    suspend fun saveCurrencyConversionDetails(currencyConverterResponse: CurrencyConverterResponse)
}

class CurrencyConverterRepositoryImpl @Inject constructor(
    private val preferences: CurrencyPreferences,
    private val currencyConverterLocalRepository: CurrencyConverterLocalRepository,
    private val currencyConverterNetworkRepository: CurrencyConverterNetworkRepository) : CurrencyConverterRepository {
    override suspend fun getCurrencyDetails(cacheTimePeriod: Long): CurrencyConverterResponse? {
        return getCachedCurrencyDetailsDate().takeUnless {
            isCacheTimeOut(cacheTimePeriod, getCacheTime()) || it == null
        } ?: currencyConverterNetworkRepository.getCurrencyExchangeRates().apply {
            this?.let {
                saveCurrency(this)
            }
        }
    }

    private suspend fun saveCurrency(currencyConverterResponse: CurrencyConverterResponse) {
        clearCache()
        saveCurrencyConversionDetails(currencyConverterResponse)
    }

    override suspend fun getCachedCurrencyDetailsDate(): CurrencyConverterResponse? =
        currencyConverterLocalRepository.invokeAllCountriesCurrencyExchangeDetails()

    override suspend fun clearCache() {
        currencyConverterLocalRepository.delete()
    }

    override suspend fun saveCurrencyConversionDetails(currencyConverterResponse: CurrencyConverterResponse) {
        with(preferences) {
            putString(CURRENCY_CACHE_TIME, getCurrentDateAndTime())
        }
        currencyConverterLocalRepository.saveCurrencyConversionRates(currencyConverterResponse)
    }

    private fun getCacheTime(): String =
        preferences.getString(
            CURRENCY_CACHE_TIME,
            EMPTY_STRING
        ) ?: EMPTY_STRING

}