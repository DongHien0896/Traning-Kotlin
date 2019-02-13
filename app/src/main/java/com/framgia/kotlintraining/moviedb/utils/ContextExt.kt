package com.framgia.kotlintraining.moviedb.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

fun Context.checkNetworkConnection(flagConnectivity: String): Boolean {
    val connectivityManager = getSystemService(flagConnectivity) as ConnectivityManager
    val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
    return if (networkInfo == null) {
        false
    } else networkInfo.isConnected
}
