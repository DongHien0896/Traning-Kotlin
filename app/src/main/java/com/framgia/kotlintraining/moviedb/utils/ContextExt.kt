package com.framgia.kotlintraining.moviedb.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

fun Context.checkNetworkConnection(flagConnectivity: String): Boolean {
    val connectivityManager = getSystemService(flagConnectivity) as ConnectivityManager
    val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
    if (networkInfo == null) {
        return false
    } else {
        networkInfo.apply {
            return isConnected
        }
    }
}
