package com.avinash.currencyconverter.domain

import com.avinash.currencyconverter.commons.Constants.DEFAULT_CACHED_PERIOD
import com.avinash.currencyconverter.data.CurrencyConverterRepository
import com.avinash.currencyconverter.uistate.CurrencyDetails
import com.avinash.currencyconverter.uistate.CurrencyUIState
import javax.inject.Inject

interface CurrencyConverterUseCase {
    suspend operator fun invoke(amountStr : String,fromCurrency: String) : CurrencyUIState
}


class CurrencyConverterUseCaseImpl @Inject constructor(
    private val repository: CurrencyConverterRepository,
    private val mapper : @JvmSuppressWildcards Function3<Map<String,Double>,String,String,List<CurrencyDetails>>
) : CurrencyConverterUseCase{
    override suspend fun invoke(amountStr: String,fromCurrency: String) : CurrencyUIState =
        try {
            repository.getCurrencyDetails(DEFAULT_CACHED_PERIOD)?.let {
                CurrencyUIState.Success(
                    mapper.invoke(
                        it.quotes,
                        amountStr,
                        fromCurrency
                    )
                )
            }?:CurrencyUIState.Failure("response empty")
        }
        catch (exception : Exception) {
            CurrencyUIState.Failure(exception.message?:"")
        }

}