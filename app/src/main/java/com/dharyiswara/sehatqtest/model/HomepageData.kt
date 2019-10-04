package com.dharyiswara.sehatqtest.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.dharyiswara.sehatqtest.database.converter.HomepageConverter
import com.google.gson.annotations.SerializedName

@Entity(tableName = "table_homepage")
data class HomepageData(
    @PrimaryKey
    @TypeConverters(HomepageConverter::class)
    @SerializedName("data") val homepage: Homepage
)