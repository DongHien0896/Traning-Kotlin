package com.framgia.kotlintraining.moviedb.data.source.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.framgia.kotlintraining.moviedb.data.model.Movie
import com.framgia.kotlintraining.moviedb.data.source.local.dao.MovieDAO

@Database(entities = [Movie::class], version = 1)
abstract class MovieDatabase constructor(movieDAO: MovieDAO) : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "moviedbdatabase"
        private var instance: MovieDatabase? = null
        fun newInstance(context: Context): MovieDatabase = instance
            ?: Room.databaseBuilder(context, MovieDatabase::class.java, DATABASE_NAME).build()
    }
}