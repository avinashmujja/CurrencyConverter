package com.avinash.currencyconverter.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.avinash.currencyconverter.data.model.CurrencyConverterResponse
import com.avinash.currencyconverter.uistate.CurrencyDetails

@Database(entities = [CurrencyConverterResponse::class], version = 1, exportSchema = false)
@TypeConverters(MapTypeConverter::class)
abstract class CurrencyDataBase : RoomDatabase(){
    abstract val currencyDAO : CurrencyDAO
}