package com.framgia.kotlintraining.moviedb.pagingwithnetworksample.byPage.ui

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.framgia.kotlintraining.moviedb.base.BaseViewModel
import com.framgia.kotlintraining.moviedb.data.model.Movie
import com.framgia.kotlintraining.moviedb.data.source.repository.MovieRepository
import com.framgia.kotlintraining.moviedb.pagingwithnetworksample.byPage.MovieByPageRepository
import java.util.concurrent.Executor

class MovieViewModel(repository: MovieRepository) : BaseViewModel() {

    private val networkExecutor = Executor { command -> command.run() }
    private val pageMovieRepository = MovieByPageRepository(repository, networkExecutor)

    fun getMovie(): LiveData<PagedList<Movie>> = pageMovieRepository.getMovie(10).pagedList

    fun refreshData() {
        pageMovieRepository.refreshData()
    }
}