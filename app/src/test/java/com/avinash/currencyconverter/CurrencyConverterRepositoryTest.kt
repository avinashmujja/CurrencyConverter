package com.avinash.currencyconverter

import com.avinash.currencyconverter.commons.Constants
import com.avinash.currencyconverter.commons.CurrencyPreferences
import com.avinash.currencyconverter.commons.extensions.getCurrentDateAndTime
import com.avinash.currencyconverter.data.CurrencyConverterRepository
import com.avinash.currencyconverter.data.CurrencyConverterRepositoryImpl
import com.avinash.currencyconverter.data.model.CurrencyConverterResponse
import com.avinash.currencyconverter.data.repository.CurrencyConverterLocalRepository
import com.avinash.currencyconverter.data.repository.CurrencyConverterNetworkRepository
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import java.util.concurrent.TimeUnit

@RunWith(JUnit4::class)
class CurrencyConverterRepositoryTest {
    private val preferences: CurrencyPreferences = mock()
    private val localRepository: CurrencyConverterLocalRepository = mock()
    private val remoteRepository: CurrencyConverterNetworkRepository = mock()

    private lateinit var repository: CurrencyConverterRepository

    companion object {
        private val response: CurrencyConverterResponse =
            parseResource("currency.json")
    }

    @Before
    fun setUp() {
        repository = CurrencyConverterRepositoryImpl(preferences,localRepository,remoteRepository)
    }

    @Test
    fun `get currency cache empty`() = runBlockingTest {
        Mockito.`when`(
            localRepository.invokeAllCountriesCurrencyExchangeDetails()
        ).thenReturn(null)
        Mockito.`when`(
            preferences.getString(
                Constants.CURRENCY_CACHE_TIME,
                Constants.EMPTY_STRING
            )
        ).thenReturn(null)
        Mockito.`when`(
            remoteRepository.getCurrencyExchangeRates()
        ).thenReturn(response)
        val result = repository.getCurrencyDetails(cacheTimePeriod = TimeUnit.DAYS.toSeconds(1))
        Mockito.verify(remoteRepository).getCurrencyExchangeRates()
        Assert.assertNotNull(result)
    }

    @Test
    fun `get currency cache available`() = runBlockingTest {
        Mockito.`when`(
            localRepository.invokeAllCountriesCurrencyExchangeDetails()
        ).thenReturn(response)
        Mockito.`when`(
            preferences.getString(
                Constants.CURRENCY_CACHE_TIME,
                Constants.EMPTY_STRING
            )
        ).thenReturn(getCurrentDateAndTime())
        Mockito.`when`(
            remoteRepository.getCurrencyExchangeRates()
        ).thenReturn(response)
        val result = repository.getCurrencyDetails(cacheTimePeriod = TimeUnit.DAYS.toSeconds(1))
        Assert.assertNotNull(result)
        Assert.assertEquals(response, result)
    }

    @Test
    fun `get currencyCache_NOT_empty_But_expired`() = runBlockingTest {
        Mockito.`when`(
            localRepository.invokeAllCountriesCurrencyExchangeDetails()
        ).thenReturn(response)
        Mockito.`when`(
            preferences.getString(
                Constants.CURRENCY_CACHE_TIME,
                Constants.EMPTY_STRING
            )
        ).thenReturn("2020/02/06 15:34:09")
        Mockito.`when`(
            remoteRepository.getCurrencyExchangeRates()
        ).thenReturn(response)
        val result = repository.getCurrencyDetails(cacheTimePeriod = TimeUnit.DAYS.toSeconds(1))
        Mockito.verify(remoteRepository).getCurrencyExchangeRates()
        Assert.assertNotNull(result)
        Assert.assertEquals(response, result)
    }

}