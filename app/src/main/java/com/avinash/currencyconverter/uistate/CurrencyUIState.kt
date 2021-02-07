package com.avinash.currencyconverter.uistate

import androidx.room.Entity
import androidx.room.PrimaryKey

sealed class CurrencyUIState {
    object Empty : CurrencyUIState()
    object Loading : CurrencyUIState()
    data class Success(val response : List<CurrencyDetails>) : CurrencyUIState()
    data class Failure(val errorMessage : String) : CurrencyUIState()
}

data class CurrencyDetails constructor(val country : String,
                                       val currencyRate: String?)