package com.avinash.currencyconverter.presentation.vm

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avinash.currencyconverter.uistate.CurrencyUIState
import com.avinash.currencyconverter.uistate.CurrencyUIState.Loading
import com.avinash.currencyconverter.domain.CurrencyConverterUseCase
import com.avinash.currencyconverter.util.DispatcherProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.math.round

class CurrencyConverterViewModel @ViewModelInject constructor(
    private val currencyConverterUseCase: CurrencyConverterUseCase,
    private val dispatchers: DispatcherProvider

) : ViewModel() {

    private val _conversion = MutableStateFlow<CurrencyUIState>(CurrencyUIState.Empty)
    val conversion : StateFlow<CurrencyUIState> = _conversion

    fun convert(amountStr: String, fromCurrency: String) {
        val fromAmount = amountStr.toFloatOrNull()
        if(fromAmount == null) {
            _conversion.value = CurrencyUIState.Failure("Not a valid amount")
            return
        }
        viewModelScope.launch(dispatchers.io) {
            _conversion.value = Loading
            _conversion.value= currencyConverterUseCase.invoke(amountStr,fromCurrency)
        }
    }


}