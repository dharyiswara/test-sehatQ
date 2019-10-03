package com.dharyiswara.sehatqtest.network

import androidx.lifecycle.LiveData
import com.dharyiswara.sehatqtest.helper.ApiResponse
import com.dharyiswara.sehatqtest.network.response.HomepageResponse
import retrofit2.http.GET

interface NetworkService {

    @GET("/home")
    fun getHomePage(): LiveData<ApiResponse<List<HomepageResponse>>>

}