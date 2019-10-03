package com.dharyiswara.sehatqtest.network.response

import com.dharyiswara.sehatqtest.model.Homepage
import com.google.gson.annotations.SerializedName

data class HomepageResponse(
    @SerializedName("data") val homepage: Homepage
)