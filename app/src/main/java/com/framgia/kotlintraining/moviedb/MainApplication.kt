package com.framgia.kotlintraining.moviedb

import android.app.Application
import com.framgia.kotlintraining.moviedb.di.modules
import org.koin.android.ext.android.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, modules)
    }
}