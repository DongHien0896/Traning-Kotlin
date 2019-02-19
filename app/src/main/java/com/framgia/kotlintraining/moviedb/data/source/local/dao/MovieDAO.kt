package com.framgia.kotlintraining.moviedb.data.source.local.dao

import androidx.room.*
import com.framgia.kotlintraining.moviedb.data.model.Movie
import io.reactivex.Single

@Dao
interface MovieDAO {

    @Query("SELECT * FROM movie")
    fun getListMovie(): Single<List<Movie>>

    @Query("SELECT * FROM movie WHERE mIdMovie=:id")
    fun getMovieById(id: String): Single<Movie>

    @Delete
    fun deleteMovie(movie: Movie): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie): Int
}