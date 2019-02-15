package com.framgia.kotlintraining.moviedb.screen.home

import com.framgia.kotlintraining.moviedb.base.BaseLoadMoreRefreshViewModel
import com.framgia.kotlintraining.moviedb.data.model.Movie
import com.framgia.kotlintraining.moviedb.data.source.repository.MovieRepository

class HomeViewModel constructor(
    private val movieRepository: MovieRepository
) : BaseLoadMoreRefreshViewModel<Movie>() {

    override fun loadData(page: Int) {
        addDisposable(
            movieRepository.getMovieList(page)
                .doAfterTerminate {
                    isLoading.value = false
                }
                .subscribe(
                    {
                        onLoadSuccess(page, it.mListMovie)
                    },
                    { threo ->
                        onLoadFail(threo)
                    }
                )
        )
    }

    companion object {
        private const val TAG = "HomeViewModel"
    }


}