package com.framgia.kotlintraining.moviedb.utils.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.framgia.kotlintraining.moviedb.utils.constant.Constant

object BindingAdapter {

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImage(imageView: ImageView, imageLink: String?) {
        var path: String = Constant.END_POINT_IMAGE_URL
        path += imageLink ?: "bOGkgRGdhrBYJSLpXaxhXVstddV.jpg"
        Glide.with(imageView.context).load(path).into(imageView)
    }

    @BindingAdapter("onRefreshListener")
    @JvmStatic
    fun SwipeRefreshLayout.refreshListener(listener: SwipeRefreshLayout.OnRefreshListener?) {
        if (listener != null) setOnRefreshListener(listener)
    }

    @BindingAdapter("isRefreshing")
    @JvmStatic
    fun SwipeRefreshLayout.refreshing(refreshing: Boolean?) {
        isRefreshing = refreshing == true
    }

    @BindingAdapter("onScrollListener")
    @JvmStatic
    fun RecyclerView.scrollListener(listener: RecyclerView.OnScrollListener?) {
        if (listener != null) addOnScrollListener(listener)
    }
}
