package com.avinash.currencyconverter.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.avinash.currencyconverter.data.local.MapTypeConverter
import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "currency_table")
data class CurrencyConverterResponse constructor(
    @SerializedName("success")
    val success : String?,

    @SerializedName("quotes")
    @TypeConverters(MapTypeConverter::class)
    val quotes : Map<String, Double>,

    @PrimaryKey(autoGenerate = true)
    val id : Int
)
