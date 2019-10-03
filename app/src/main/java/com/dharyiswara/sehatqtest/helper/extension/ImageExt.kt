package com.dharyiswara.sehatqtest.helper.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.dharyiswara.sehatqtest.R

fun ImageView.loadFromUrl(imageUrl: String?) {
    val options = RequestOptions()
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        .priority(Priority.IMMEDIATE)
    Glide.with(this.context)
        .load(imageUrl)
        .placeholder(R.drawable.ic_no_image)
        .error(R.drawable.ic_no_image)
        .apply(options)
        .into(this)
}