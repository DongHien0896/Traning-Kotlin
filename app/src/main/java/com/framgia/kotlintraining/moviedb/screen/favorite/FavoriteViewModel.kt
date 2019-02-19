package com.framgia.kotlintraining.moviedb.screen.favorite

import com.framgia.kotlintraining.moviedb.base.BaseLoadMoreRefreshViewModel
import com.framgia.kotlintraining.moviedb.data.model.Movie
import com.framgia.kotlintraining.moviedb.data.source.local.dao.MovieDAO
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FavoriteViewModel constructor(
    private val movieDAO: MovieDAO
) : BaseLoadMoreRefreshViewModel<Movie>() {

    companion object {
        private const val TAG = "FavoriteViewModel"
    }

    override fun loadData(page: Int) {
        addDisposable(movieDAO.getListMovie()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onLoadSuccess(page, it)
            }, {
                onLoadFail(it)
            })
        )
    }
}