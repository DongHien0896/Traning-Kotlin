package com.framgia.kotlintraining.moviedb.data.source.repository

import androidx.lifecycle.LiveData
import com.framgia.kotlintraining.moviedb.data.model.Movie
import com.framgia.kotlintraining.moviedb.data.model.MovieResponse
import io.reactivex.Single

interface MovieRepository {
    //remote
    fun getMovieList(pageNumber: Int): Single<MovieResponse>

    //local
    fun getListMovie(): Single<List<Movie>>
    fun getMovieById(id: String): Single<Movie>
    fun insertMovie(movie: Movie): Int
    fun deleteMovie(movie: Movie): Int
}