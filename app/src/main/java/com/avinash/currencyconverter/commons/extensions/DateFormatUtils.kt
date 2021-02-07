package com.avinash.currencyconverter.commons.extensions

import com.avinash.currencyconverter.commons.Constants.YYYY_MM_dd_HH_mm_ss
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.concurrent.TimeUnit

fun isCacheTimeOut(
    timeOutInSeconds: Long,
    cacheSavedTime: String?,
    currentDateTime: String = getCurrentDateAndTime()
): Boolean {
    if (cacheSavedTime.isNullOrBlank()) {
        return true
    }
    val timeDifferenceInSeconds = getTimeDifferenceInSeconds(cacheSavedTime, currentDateTime)
    return timeDifferenceInSeconds > timeOutInSeconds
}

fun getCurrentDateAndTime(): String =
    getDefaultDateFormat().format(Calendar.getInstance().time)

fun getDefaultDateFormat(
    format: String = YYYY_MM_dd_HH_mm_ss,
    locale: Locale = Locale.getDefault()
) = SimpleDateFormat(format, locale)

fun getTimeDifferenceInSeconds(dateTime1: String, dateTime2: String): Long {
    val format = getDefaultDateFormat()
    val date1 = format.parse(dateTime1)
    val date2 = format.parse(dateTime2)
    val difference = date2.time - date1.time
    return TimeUnit.MILLISECONDS.toSeconds(difference)
}