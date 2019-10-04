package com.dharyiswara.sehatqtest

import com.dharyiswara.sehatqtest.model.Category
import com.dharyiswara.sehatqtest.model.Homepage
import com.dharyiswara.sehatqtest.model.HomepageData
import com.dharyiswara.sehatqtest.model.ProductPromo
import java.util.*

object TestDataFactory {

    fun makeHomepageData(): List<HomepageData> {
        val list = mutableListOf<HomepageData>()
        list.add(HomepageData(makeHomepage()))
        return list
    }

    private fun makeHomepage(): Homepage {
        return Homepage(makeCategoryList(), makeProductList())
    }

    private fun makeProductList(total: Int = 10): List<ProductPromo> {
        val list = mutableListOf<ProductPromo>()
        for (i in 0 until total) {
            list.add(makeProduct())
        }
        return list
    }

    private fun makeProduct(): ProductPromo {
        return ProductPromo(
            UUID.randomUUID().toString(),
            UUID.randomUUID().toString(),
            UUID.randomUUID().toString(),
            UUID.randomUUID().toString(),
            "$50",
            0
        )
    }

    private fun makeCategoryList(total: Int = 10): List<Category> {
        val list = mutableListOf<Category>()
        for (i in 0 until total) {
            list.add(makeCategory())
        }
        return list
    }

    private fun makeCategory(): Category {
        return Category(
            UUID.randomUUID().toString(),
            UUID.randomUUID().toString(),
            UUID.randomUUID().toString()
        )
    }

}