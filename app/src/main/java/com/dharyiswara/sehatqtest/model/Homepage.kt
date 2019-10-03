package com.dharyiswara.sehatqtest.model

import com.google.gson.annotations.SerializedName

data class Homepage(
    @SerializedName("category") val category: List<Category>,
    @SerializedName("productPromo") val productPromo: List<ProductPromo>
)