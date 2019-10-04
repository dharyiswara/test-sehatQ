package com.dharyiswara.sehatqtest.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dharyiswara.sehatqtest.model.HomepageData

@Dao
interface HomepageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveHomepage(list: List<HomepageData>)

    @Query("SELECT * FROM table_homepage")
    fun getHomepage(): LiveData<List<HomepageData>>

}