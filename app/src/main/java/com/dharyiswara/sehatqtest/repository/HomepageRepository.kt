package com.dharyiswara.sehatqtest.repository

import androidx.lifecycle.LiveData
import com.dharyiswara.sehatqtest.base.BaseRepository
import com.dharyiswara.sehatqtest.database.dao.HomepageDao
import com.dharyiswara.sehatqtest.helper.ApiResponse
import com.dharyiswara.sehatqtest.helper.AppExecutors
import com.dharyiswara.sehatqtest.helper.Resource
import com.dharyiswara.sehatqtest.network.NetworkService
import com.dharyiswara.sehatqtest.model.HomepageData

class HomepageRepository(
    private val service: NetworkService,
    private val database: HomepageDao,
    private val appExecutors: AppExecutors
) {

    fun getHomepage(isReloadData: Boolean): LiveData<Resource<List<HomepageData>>> {
        return object : BaseRepository<List<HomepageData>>(appExecutors) {
            override fun saveFromNetwork(item: List<HomepageData>) {
                database.saveHomepage(item)
            }

            override fun shouldFetchFromNetwork(data: List<HomepageData>?): Boolean {
                if (isReloadData) return true
                return data == null || data.isEmpty()
            }

            override fun loadFromLocal(): LiveData<List<HomepageData>> {
                return database.getHomepage()
            }

            override fun loadFromNetwork(): LiveData<ApiResponse<List<HomepageData>>> {
                return service.getHomePage()
            }

        }.asLiveData()
    }

}