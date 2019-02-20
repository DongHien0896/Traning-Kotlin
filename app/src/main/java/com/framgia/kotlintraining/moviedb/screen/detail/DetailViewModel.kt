package com.framgia.kotlintraining.moviedb.screen.detail

import androidx.lifecycle.MutableLiveData
import com.framgia.kotlintraining.moviedb.base.BaseViewModel
import com.framgia.kotlintraining.moviedb.data.model.Movie
import com.framgia.kotlintraining.moviedb.data.source.local.dao.MovieDAO
import com.framgia.kotlintraining.moviedb.data.source.repository.MovieRepository
import com.framgia.kotlintraining.moviedb.utils.constant.EvenDatabase
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class DetailMovieViewModel constructor(
    private val movieRepository: MovieRepository,
    private val movieDao: MovieDAO
) : BaseViewModel() {

    val movie = MutableLiveData<Movie>()

    val favoriteChanged = MutableLiveData<Boolean>().apply { value = false }

    fun updateMovie(currentMovie: Movie, even: EvenDatabase) {
        val disposable = Observable.just(currentMovie)
            .subscribeOn(Schedulers.io())
            .subscribe {
                currentMovie.isFavorite = currentMovie.isFavorite?.not()
                favoriteChanged.postValue(currentMovie.isFavorite)
                when (even) {
                    EvenDatabase.DELETE -> movieRepository.deleteMovie(currentMovie)
                    EvenDatabase.ADD -> movieRepository.insertMovie(currentMovie)
                }
            }
        addDisposable(disposable)
    }

    fun addMovie(currentMovie: Movie) {
        updateMovie(currentMovie, EvenDatabase.ADD)
    }

    fun deleteMovie(currentMovie: Movie) {
        updateMovie(currentMovie, EvenDatabase.DELETE)
    }
}
