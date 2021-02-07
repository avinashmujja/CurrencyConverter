package com.avinash.currencyconverter.data.repository

import com.avinash.currencyconverter.commons.CurrencyPreferences
import com.avinash.currencyconverter.data.local.CurrencyDAO
import com.avinash.currencyconverter.data.model.CurrencyConverterResponse
import com.avinash.currencyconverter.uistate.CurrencyDetails
import javax.inject.Inject

interface CurrencyConverterLocalRepository {
    suspend fun invokeAllCountriesCurrencyExchangeDetails():CurrencyConverterResponse?
    suspend fun saveCurrencyConversionRates(currencyConverterResponse: CurrencyConverterResponse)
    suspend fun delete()
}

class CurrencyConverterLocalRepositoryImpl @Inject
constructor(private val dao: CurrencyDAO
) : CurrencyConverterLocalRepository {
    override suspend fun invokeAllCountriesCurrencyExchangeDetails(): CurrencyConverterResponse? {
        return dao.invokeAllCountriesCurrencyExchangeDetails()
    }



    override suspend fun saveCurrencyConversionRates(currencyConverterResponse: CurrencyConverterResponse) {
        dao.saveCurrencyConversionRates(currencyConverterResponse)
    }

    override suspend fun delete() {
        dao.delete()
    }

}