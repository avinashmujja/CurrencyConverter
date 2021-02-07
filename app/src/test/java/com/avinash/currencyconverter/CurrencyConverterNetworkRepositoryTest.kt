package com.avinash.currencyconverter

import com.avinash.currencyconverter.data.CurrencyConverterAPI
import com.avinash.currencyconverter.data.model.CurrencyConverterResponse
import com.avinash.currencyconverter.data.repository.CurrencyConverterNetworkRepository
import com.avinash.currencyconverter.data.repository.CurrencyConverterNetworkRepositoryImpl
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito

@RunWith(JUnit4::class)
class CurrencyConverterNetworkRepositoryTest {
    private val currencyConverterAPI: CurrencyConverterAPI = mock()

    private lateinit var repository : CurrencyConverterNetworkRepository

    companion object {
        private val response: CurrencyConverterResponse =
            parseResource("currency.json")
    }

    @Before
    fun setUp() {
        repository = CurrencyConverterNetworkRepositoryImpl(currencyConverterAPI = currencyConverterAPI)
    }

    @Test
    fun `get currency exchange rates`() = runBlockingTest {
        Mockito.`when`(
            currencyConverterAPI.getCurrencyExchangeRates(any())
        ).thenReturn(response)
        Assert.assertEquals(response,repository.getCurrencyExchangeRates())
    }
}