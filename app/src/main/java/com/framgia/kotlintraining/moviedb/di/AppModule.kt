package com.framgia.kotlintraining.moviedb.di

import android.content.Context
import androidx.room.Room
import com.framgia.kotlintraining.moviedb.data.source.local.db.MovieDatabase
import com.framgia.kotlintraining.moviedb.data.source.repository.MovieRepository
import com.framgia.kotlintraining.moviedb.data.source.repository.impl.MovieRepositoryImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

val appModule = module {
    single { androidApplication().resources }
    single<MovieRepository> { MovieRepositoryImpl(get(), get()) }
    single { createDatabaseName() }
    single { createAppDatabase(get(), get()) }
    single { createMovieDao(get()) }
}

val modules = listOf(appModule, viewModelModule, netWorkModule)

fun createDatabaseName() = "moviedbdatabase"

fun createAppDatabase(dbName: String, context: Context) =
    Room.databaseBuilder(context, MovieDatabase::class.java, dbName).build()

fun createMovieDao(appDatabase: MovieDatabase) = appDatabase.movieDAO()