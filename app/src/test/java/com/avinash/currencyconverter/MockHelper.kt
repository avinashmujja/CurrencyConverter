package com.avinash.currencyconverter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader

inline fun <reified T> parseResource(path: String, gson: Gson = Gson()): T {
    val obj = object : TypeToken<T>() {}.type

    return gson.fromJson(
        InputStreamReader(T::class.java.classLoader!!.getResourceAsStream(path)),
        obj
    )
}
