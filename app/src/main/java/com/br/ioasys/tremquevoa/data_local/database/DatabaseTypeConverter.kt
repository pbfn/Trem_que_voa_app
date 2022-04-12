package com.br.ioasys.tremquevoa.data_local.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object DatabaseTypeConverter {

    @TypeConverter
    fun fromString(value: String?): List<String> {
        val listType = object :
            TypeToken<List<String?>?>() {}.type
        return Gson()
            .fromJson<List<String>>(value, listType)
    }

    @TypeConverter
    fun listToString(list: List<String?>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}