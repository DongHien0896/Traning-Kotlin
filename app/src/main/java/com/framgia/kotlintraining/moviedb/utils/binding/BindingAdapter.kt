package com.framgia.kotlintraining.moviedb.utils.binding

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.framgia.kotlintraining.moviedb.utils.constant.Constants

@BindingAdapter("imageUrl")
fun loadImage(imageView: ImageView, imageLink: String?) {
    var path: String = Constants.END_POINT_IMAGE_URL
    path += imageLink ?: "bOGkgRGdhrBYJSLpXaxhXVstddV.jpg"
    Glide.with(imageView.context).load(path).into(imageView)
}

@BindingAdapter("onRefreshListener")
fun SwipeRefreshLayout.refreshListener(listener: SwipeRefreshLayout.OnRefreshListener?) {
    if (listener != null) setOnRefreshListener(listener)
}

@BindingAdapter("isRefreshing")
fun SwipeRefreshLayout.refreshing(refreshing: Boolean?) {
    isRefreshing = refreshing == true
}

@BindingAdapter("onScrollListener")
fun RecyclerView.scrollListener(listener: RecyclerView.OnScrollListener?) {
    if (listener != null) addOnScrollListener(listener)
}

@BindingAdapter("onClick")
fun onClick(view: View, listener: View.OnClickListener) {
    view.setOnClickListener(listener)
}
