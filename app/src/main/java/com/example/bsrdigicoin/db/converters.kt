package com.example.bsrdigicoin.db


import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.*


class converters {
    val df = SimpleDateFormat("HH:mm:ss")

    @TypeConverter
    fun timeConverter(value: String): Date? {
        return df.parse(value)
    }
}