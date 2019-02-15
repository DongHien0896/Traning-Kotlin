package com.framgia.kotlintraining.moviedb.di

import com.framgia.kotlintraining.moviedb.data.source.repository.MovieRepository
import com.framgia.kotlintraining.moviedb.data.source.repository.impl.MovieRepositoryImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

val appModule = module {
    single { androidApplication().resources }
    single<MovieRepository> { MovieRepositoryImpl(get()) }
}

val modules = listOf(appModule, viewModelModule, netWorkModule)