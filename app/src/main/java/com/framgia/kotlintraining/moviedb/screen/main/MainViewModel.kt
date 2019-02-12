package com.framgia.kotlintraining.moviedb.screen.main

import android.app.Application
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.lifecycle.MutableLiveData
import com.framgia.kotlintraining.moviedb.base.BaseViewModel

class MainViewModel : BaseViewModel() {

    val isNetworkConected = MutableLiveData<Boolean>()

    open fun checkNetworkConnection(flagConnectivity: String) {
        val connectivityManager = Application().getSystemService(flagConnectivity) as ConnectivityManager
        val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (networkInfo == null) {
            isNetworkConected.value = false
        } else {
            networkInfo.apply {
                isNetworkConected.value = isConnected
            }
        }
    }
}