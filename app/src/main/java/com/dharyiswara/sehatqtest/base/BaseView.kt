package com.dharyiswara.sehatqtest.base

interface BaseView {

    fun getLayoutResId(): Int

    fun initView()

    fun initEvent()

    fun loadingData(isFromSwipe: Boolean = false)

    fun observeData()

}