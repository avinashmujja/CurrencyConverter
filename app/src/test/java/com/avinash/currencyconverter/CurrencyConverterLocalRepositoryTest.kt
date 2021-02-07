package com.avinash.currencyconverter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.avinash.currencyconverter.data.local.CurrencyDAO
import com.avinash.currencyconverter.data.model.CurrencyConverterResponse
import com.avinash.currencyconverter.data.repository.CurrencyConverterLocalRepository
import com.avinash.currencyconverter.data.repository.CurrencyConverterLocalRepositoryImpl
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.verify

@RunWith(JUnit4::class)
class CurrencyConverterLocalRepositoryTest {

    private val dao : CurrencyDAO = mock()

    private lateinit var repository : CurrencyConverterLocalRepository

    companion object {
        private val response: CurrencyConverterResponse =
            parseResource("currency.json")
    }

    @Before
    fun setUp() {
        repository = CurrencyConverterLocalRepositoryImpl(dao)
    }

    @Test
    fun `invoke countries currency Details`() = runBlockingTest {
        Mockito.`when`(
            dao.invokeAllCountriesCurrencyExchangeDetails()
        ).thenReturn(response)
        Assert.assertEquals(response,repository.invokeAllCountriesCurrencyExchangeDetails())
    }

    @Test
    fun `save currency conversion Rates`() = runBlockingTest {
        repository.saveCurrencyConversionRates(response)
        verify(dao).saveCurrencyConversionRates(response)
    }

    @Test
    fun `delete currency data`() = runBlockingTest {
        repository.delete()
        verify(dao).delete()
    }
}