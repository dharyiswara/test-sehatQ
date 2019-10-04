package com.dharyiswara.sehatqtest.model

import android.os.Parcelable
import com.dharyiswara.sehatqtest.helper.TextUtils
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import kotlinx.android.parcel.Parcelize

@Parcelize
open class ProductPromo(
    @SerializedName("id") var id: String = TextUtils.BLANK,
    @SerializedName("imageUrl") var imageUrl: String = TextUtils.BLANK,
    @SerializedName("title") var title: String = TextUtils.BLANK,
    @SerializedName("description") var description: String = TextUtils.BLANK,
    @SerializedName("price") var price: String = TextUtils.BLANK,
    @SerializedName("loved") var loved: Int = 0
) : RealmObject(), Parcelable {

    fun isLoved(): Boolean {
        return loved == 1
    }

}