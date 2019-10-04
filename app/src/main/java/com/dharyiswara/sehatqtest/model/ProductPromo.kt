package com.dharyiswara.sehatqtest.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ProductPromo(
    @SerializedName("id") val id: String,
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("price") val price: String,
    @SerializedName("loved") var loved: Int
) : Serializable {

    fun isLoved(): Boolean {
        return loved == 1
    }

}