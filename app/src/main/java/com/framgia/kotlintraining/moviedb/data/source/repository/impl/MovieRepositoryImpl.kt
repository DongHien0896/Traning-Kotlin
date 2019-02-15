package com.framgia.kotlintraining.moviedb.data.source.repository.impl

import com.framgia.kotlintraining.moviedb.data.model.MovieResponse
import com.framgia.kotlintraining.moviedb.data.source.network.Api
import com.framgia.kotlintraining.moviedb.data.source.repository.MovieRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MovieRepositoryImpl constructor(
    private val api: Api
) : MovieRepository {
    override fun getMovieList(pageNumber: Int): Single<MovieResponse> {
        return api.getMoviePopular(pageNumber)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}