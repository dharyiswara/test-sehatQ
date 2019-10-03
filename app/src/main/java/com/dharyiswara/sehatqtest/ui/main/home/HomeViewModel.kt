package com.dharyiswara.sehatqtest.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.dharyiswara.sehatqtest.helper.Resource
import com.dharyiswara.sehatqtest.network.response.HomepageResponse
import com.dharyiswara.sehatqtest.repository.HomepageRepository

class HomeViewModel(private val repository: HomepageRepository) : ViewModel() {

    private val homepageRequest = MutableLiveData<Boolean>()

    val homepageData: LiveData<Resource<List<HomepageResponse>>> = Transformations
        .switchMap(homepageRequest) {
            repository.getHomepage()
        }

    fun getHomepage(isReloadData: Boolean) {
        homepageRequest.value = isReloadData
    }

}