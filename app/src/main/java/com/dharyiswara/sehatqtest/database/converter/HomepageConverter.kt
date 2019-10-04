package com.dharyiswara.sehatqtest.database.converter

import androidx.room.TypeConverter
import com.dharyiswara.sehatqtest.model.Homepage
import com.dharyiswara.sehatqtest.model.HomepageData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class HomepageConverter {

    @TypeConverter
    fun itemToString(homepage: Homepage): String {
        val gson = Gson()
        val type = object : TypeToken<Homepage>() {}.type
        return gson.toJson(homepage, type)
    }

    @TypeConverter
    fun stringToItem(data: String): Homepage {
        val gson = Gson()
        val type = object : TypeToken<Homepage>() {}.type
        return gson.fromJson(data, type)
    }
}