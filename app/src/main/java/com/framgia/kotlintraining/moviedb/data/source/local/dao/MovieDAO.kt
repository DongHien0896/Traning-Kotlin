package com.framgia.kotlintraining.moviedb.data.source.local.dao

import androidx.room.*
import com.framgia.kotlintraining.moviedb.data.model.Movie
import io.reactivex.Single

@Dao
interface MovieDAO {

    @Query("SELECT * FROM movie")
    fun getListMovie(): Single<List<Movie>>

    @Query("SELECT * FROM movie WHERE id=:id")
    fun getMovieById(id: Int): Single<Movie>

    @Delete
    fun deleteMovie(movie: Movie): Int

    @Insert
    fun insertMovie(movie: Movie): Long
}