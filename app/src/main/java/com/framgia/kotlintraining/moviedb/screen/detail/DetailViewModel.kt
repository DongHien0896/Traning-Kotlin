package com.framgia.kotlintraining.moviedb.screen.detail

import androidx.lifecycle.MutableLiveData
import com.framgia.kotlintraining.moviedb.base.BaseViewModel
import com.framgia.kotlintraining.moviedb.data.model.Movie
import com.framgia.kotlintraining.moviedb.data.source.repository.MovieRepository
import com.framgia.kotlintraining.moviedb.utils.constant.EvenDatabase
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailMovieViewModel constructor(
    private val movieRepository: MovieRepository
) : BaseViewModel() {

    val movie = MutableLiveData<Movie>()

    val favoriteChanged = MutableLiveData<Boolean>().apply { value = false }

    private fun updateMovie(currentMovie: Movie, even: EvenDatabase) {
        val disposable = Observable.just(currentMovie)
            .subscribeOn(Schedulers.io())
            .subscribe {
                when (even) {
                    EvenDatabase.DELETE -> {
                        currentMovie.isFavorite = false
                        favoriteChanged.postValue(false)
                        movieRepository.deleteMovie(currentMovie)
                    }
                    EvenDatabase.ADD -> {
                        currentMovie.isFavorite = true
                        favoriteChanged.postValue(true)
                        movieRepository.insertMovie(currentMovie)
                    }
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

    fun getMovieById(id: Int) {
        addDisposable(
            movieRepository.getMovieById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    movie.value = it
                    favoriteChanged.value = it.isFavorite
                }, {
                    movie.value?.isFavorite = false
                    favoriteChanged.value = false
                })
        )
    }
}
