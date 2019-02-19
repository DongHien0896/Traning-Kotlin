package com.framgia.kotlintraining.moviedb.data.source.local.dao

import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.framgia.kotlintraining.moviedb.data.model.Movie

@Dao
interface MovieDAO {

    @Query("SELECT * FROM movie")
    fun getListMovie(): MutableLiveData<List<Movie>>

    @Query("SELECT * FROM movie WHERE mIdMovie=:id")
    fun getMovieById(id: String): MutableLiveData<Movie>

    @Delete
    fun deleteMovie(movie: Movie): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie): Int
}