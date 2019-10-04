package com.dharyiswara.sehatqtest.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dharyiswara.sehatqtest.database.converter.HomepageConverter
import com.dharyiswara.sehatqtest.database.dao.HomepageDao
import com.dharyiswara.sehatqtest.model.HomepageData

@Database(
    entities = [HomepageData::class],
    version = SehatQDatabase.VERSION,
    exportSchema = false
)
@TypeConverters(HomepageConverter::class)
abstract class SehatQDatabase : RoomDatabase() {

    companion object {

        const val VERSION = 1

    }

    abstract val homepageDao: HomepageDao

}