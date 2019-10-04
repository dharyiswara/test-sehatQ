package com.dharyiswara.sehatqtest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object DaoUtil {

    fun <T : Any> createCall(data: T) = MutableLiveData<T>().apply {
        value = data
    } as LiveData<T>
}