package com.framgia.kotlintraining.moviedb.data.source.repository

import com.framgia.kotlintraining.moviedb.data.model.MovieResponse
import io.reactivex.Single

interface MovieRepository {
    fun getMovieList(pageNumber: Int): Single<MovieResponse>
}