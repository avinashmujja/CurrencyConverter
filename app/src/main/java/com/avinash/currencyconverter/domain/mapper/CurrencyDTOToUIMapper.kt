package com.avinash.currencyconverter.domain.mapper

import android.content.Context
import com.avinash.currencyconverter.R
import com.avinash.currencyconverter.uistate.CurrencyDetails
import javax.inject.Inject
import kotlin.math.ceil
import kotlin.math.round

class CurrencyDTOToUIMapper @Inject constructor (private val context: Context) :
    Function3<Map<String,Double>, String , String, List< CurrencyDetails>> {
    override fun invoke(quotes: Map<String,Double>, amountStr: String, fromCurrency: String): List<CurrencyDetails> {
        val currencyDetails : MutableList<CurrencyDetails> = mutableListOf()
        val countryList = context.resources.getStringArray(R.array.currency_codes)
        val country = "USD$fromCurrency"
        quotes[country]?.let {
            for (item in countryList) {
                val string = "USD$item"
                quotes[string]?.let {it1->
                    currencyDetails.add(CurrencyDetails(country = item,
                        currencyRate = String.format("%2f",amountStr.toFloat() * (it1/it))
                    ))
                }

            }
        }
        return currencyDetails
    }
}
