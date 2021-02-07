package com.avinash.currencyconverter.commons

import java.util.concurrent.TimeUnit

object Constants {
    /* Try to create your own key
    below api key can access only 250 requests in a month */
    const val apiKey = "35be00cf29ad78e76550b46ba2389a65"
    const val API_TIMEOUT : Long = 30
    const val baseURL = "http://api.currencylayer.com/"
    const val PREFERENCES = "CurrencyPreferences"
    const val CURRENCY_CACHE_TIME = "cacheTime"
    const val EMPTY_STRING = ""
    const val YYYY_MM_dd_HH_mm_ss = "yyyy/MM/dd HH:mm:ss"
    val DEFAULT_CACHED_PERIOD = TimeUnit.MINUTES.toSeconds(30)
}