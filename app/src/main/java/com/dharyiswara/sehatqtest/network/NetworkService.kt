package com.dharyiswara.sehatqtest.network

import androidx.lifecycle.LiveData
import com.dharyiswara.sehatqtest.helper.ApiResponse
import com.dharyiswara.sehatqtest.model.HomepageData
import retrofit2.http.GET

interface NetworkService {

    @GET("/home")
    fun getHomePage(): LiveData<ApiResponse<List<HomepageData>>>

}