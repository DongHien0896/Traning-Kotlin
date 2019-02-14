package com.framgia.kotlintraining.moviedb.di

import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

val appModule = module {
    single { androidApplication().resources }
}

val modules = listOf(
    appModule,
    viewModelModule
)