package com.framgia.kotlintraining.moviedb.screen.favorite

import com.framgia.kotlintraining.moviedb.base.BaseLoadMoreRefreshViewModel
import com.framgia.kotlintraining.moviedb.data.model.Movie

class FavoriteViewModel : BaseLoadMoreRefreshViewModel<Movie>() {

    companion object {
        private const val TAG = "FavoriteViewModel"
    }

    override fun loadData(page: Int) {

    }
}