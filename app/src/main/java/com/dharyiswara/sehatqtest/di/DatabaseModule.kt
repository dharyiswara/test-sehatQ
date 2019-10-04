package com.dharyiswara.sehatqtest.di

import android.content.Context
import androidx.room.Room
import com.dharyiswara.sehatqtest.database.SehatQDatabase
import com.dharyiswara.sehatqtest.database.dao.HomepageDao
import com.dharyiswara.sehatqtest.helper.extension.clazz

fun makeDatabase(context: Context): SehatQDatabase {
    return Room.databaseBuilder(context, clazz<SehatQDatabase>(), "SehatQ.db")
        .fallbackToDestructiveMigration()
        .build()
}

fun makeHomepageDao(database: SehatQDatabase): HomepageDao {
    return database.homepageDao
}