package com.framgia.kotlintraining.moviedb.utils.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.framgia.kotlintraining.moviedb.utils.constant.Constant

object BindingAdapter {
    @BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImage(imageView: ImageView, imageLink: String) {
        val path: String = Constant.END_POINT_IMAGE_URL.plus(imageLink)
        Glide.with(imageView.context).load(path).into(imageView)
    }
}
