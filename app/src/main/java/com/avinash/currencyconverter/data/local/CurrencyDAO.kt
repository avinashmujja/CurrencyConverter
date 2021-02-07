package com.avinash.currencyconverter.data.local

import androidx.room.*
import com.avinash.currencyconverter.data.model.CurrencyConverterResponse
import com.avinash.currencyconverter.uistate.CurrencyDetails

@Dao
interface CurrencyDAO {

        @Query("SELECT * FROM currency_table")
        suspend fun invokeAllCountriesCurrencyExchangeDetails(): CurrencyConverterResponse?

        @Transaction
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun saveCurrencyConversionRates(currencyDetails: CurrencyConverterResponse)

        @Query("DELETE FROM currency_table")
        suspend fun delete()
}