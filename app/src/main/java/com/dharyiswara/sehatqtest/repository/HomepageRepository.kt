package com.dharyiswara.sehatqtest.repository

import androidx.lifecycle.LiveData
import com.dharyiswara.sehatqtest.base.BaseRepository
import com.dharyiswara.sehatqtest.helper.AbsentLiveData
import com.dharyiswara.sehatqtest.helper.ApiResponse
import com.dharyiswara.sehatqtest.helper.AppExecutors
import com.dharyiswara.sehatqtest.helper.Resource
import com.dharyiswara.sehatqtest.network.NetworkService
import com.dharyiswara.sehatqtest.network.response.HomepageResponse

class HomepageRepository(
    private val service: NetworkService,
    private val appExecutors: AppExecutors
) {

    fun getHomepage(): LiveData<Resource<List<HomepageResponse>>> {
        return object : BaseRepository<List<HomepageResponse>>(appExecutors) {
            override fun saveFromNetwork(item: List<HomepageResponse>) {

            }

            override fun shouldFetchFromNetwork(data: List<HomepageResponse>?): Boolean {
                return true
            }

            override fun loadFromLocal(): LiveData<List<HomepageResponse>> {
                return AbsentLiveData.create()
            }

            override fun loadFromNetwork(): LiveData<ApiResponse<List<HomepageResponse>>> {
                return service.getHomePage()
            }

        }.asLiveData()
    }

}