package com.framgia.kotlintraining.moviedb.screen.detail

import androidx.lifecycle.MutableLiveData
import com.framgia.kotlintraining.moviedb.base.BaseLoadMoreRefreshViewModel
import com.framgia.kotlintraining.moviedb.data.model.Movie
import com.framgia.kotlintraining.moviedb.data.source.repository.MovieRepository

class DetailMovieViewModel constructor(
    private val movieRepository: MovieRepository
) : BaseLoadMoreRefreshViewModel<Movie>() {

    val movie = MutableLiveData<Movie>()

    override fun loadData(page: Int) {

    }

}