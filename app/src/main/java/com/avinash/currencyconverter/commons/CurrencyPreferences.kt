package com.avinash.currencyconverter.commons

import android.content.SharedPreferences
import javax.inject.Inject

interface CurrencyPreferences {
    fun getString(key: String, defaultValue: String?): String?
    fun putString(key: String, value: String)
}

class CurrencyPreferencesImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences) : CurrencyPreferences {
    override fun getString(key: String, defaultValue: String?): String? =
        sharedPreferences.getString(key, defaultValue)


    override fun putString(key: String, value: String) {
        sharedPreferences.edit().run {
            this.putString(key, value)
            this.apply()
        }
    }

}