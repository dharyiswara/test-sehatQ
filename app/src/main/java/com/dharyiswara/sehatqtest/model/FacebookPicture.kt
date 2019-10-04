package com.dharyiswara.sehatqtest.model

import com.google.gson.annotations.SerializedName

data class FacebookPicture(@SerializedName("data") val data: Picture) {

    data class Picture(@SerializedName("url") val url: String)

}