package com.avinash.currencyconverter

import com.avinash.currencyconverter.commons.Constants
import com.avinash.currencyconverter.data.CurrencyConverterRepository
import com.avinash.currencyconverter.data.model.CurrencyConverterResponse
import com.avinash.currencyconverter.domain.CurrencyConverterUseCase
import com.avinash.currencyconverter.domain.CurrencyConverterUseCaseImpl
import com.avinash.currencyconverter.uistate.CurrencyDetails
import com.avinash.currencyconverter.uistate.CurrencyUIState
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito

@RunWith(JUnit4::class)
class CurrencyConverterUseCaseTest {

    private lateinit var useCase: CurrencyConverterUseCase

    private val repository: CurrencyConverterRepository = mock()

    private val mapper : @JvmSuppressWildcards Function3<Map<String,Double>,String,String,List<CurrencyDetails>> = mock()

    companion object {
        private val response: CurrencyConverterResponse =
            parseResource("currency.json")
    }

    @Before
    fun setUp() {
        useCase = CurrencyConverterUseCaseImpl(repository,mapper)
    }

    @Test
    fun `should correctly return Success UI state`()  = runBlockingTest {
        Mockito.`when`(
            repository.getCurrencyDetails(Constants.DEFAULT_CACHED_PERIOD)
        ).thenReturn(response)
        val value = useCase.invoke(any(), any())
        assertNotNull(value)
    }

}