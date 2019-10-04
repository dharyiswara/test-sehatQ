package com.dharyiswara.sehatqtest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object LiveDataUtil {

    fun <T : Any> create(data: T) = MutableLiveData<T>().apply {
        value = data
    } as LiveData<T>
}