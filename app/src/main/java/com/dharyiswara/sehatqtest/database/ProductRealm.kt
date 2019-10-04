package com.dharyiswara.sehatqtest.database

import com.dharyiswara.sehatqtest.helper.extension.clazz
import com.dharyiswara.sehatqtest.model.ProductPromo
import io.realm.Realm

class ProductRealm(
    private val realm: Realm,
    private val onSuccess: () -> Unit = {},
    private val onError: (Throwable) -> Unit = {}
) {

    fun buyProduct(product: ProductPromo) {
        realm.executeTransactionAsync(
            {
                val productRealm = it.createObject(clazz<ProductPromo>())
                productRealm.id = product.id
                productRealm.imageUrl = product.imageUrl
                productRealm.title = product.title
                productRealm.description = product.description
                productRealm.price = product.price
                productRealm.loved = product.loved
            },
            {
                onSuccess.invoke()
            },
            {
                onError.invoke(it)
            }
        )
    }

    fun getProductList(): List<ProductPromo> {
        return realm.where(clazz<ProductPromo>()).findAll()
    }

    fun reset() {
        realm.beginTransaction()
        realm.deleteAll()
        realm.commitTransaction()
    }

}