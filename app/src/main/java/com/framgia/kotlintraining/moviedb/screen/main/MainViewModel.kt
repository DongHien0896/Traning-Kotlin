package com.framgia.kotlintraining.moviedb.screen.main

import androidx.lifecycle.MutableLiveData
import com.framgia.kotlintraining.moviedb.base.BaseViewModel

class MainViewModel : BaseViewModel() {

    val isNetworkConected = MutableLiveData<Boolean>()

    open fun checkNetworkConnection(flagConnectivity: String){

    }
}