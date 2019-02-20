package com.framgia.kotlintraining.moviedb.data.source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.framgia.kotlintraining.moviedb.data.model.Movie
import com.framgia.kotlintraining.moviedb.data.source.local.dao.MovieDAO

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDAO(): MovieDAO
}