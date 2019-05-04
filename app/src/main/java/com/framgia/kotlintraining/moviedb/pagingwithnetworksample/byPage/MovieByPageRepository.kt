package com.framgia.kotlintraining.moviedb.pagingwithnetworksample.byPage

import androidx.lifecycle.Transformations
import androidx.paging.Config
import androidx.paging.toLiveData
import com.framgia.kotlintraining.moviedb.data.model.Listing
import com.framgia.kotlintraining.moviedb.data.model.Movie
import com.framgia.kotlintraining.moviedb.data.source.repository.MovieRepository
import java.util.concurrent.Executor

class MovieByPageRepository(
    private val repository: MovieRepository,
    private val executor: Executor
) {
    private lateinit var sourceFactory: MovieDataSourceFactory

    fun getMovie(pageSize: Int): Listing<Movie> {
        sourceFactory = MovieDataSourceFactory(repository, executor)

        val livePagedList = sourceFactory.toLiveData(
            config = Config(
                pageSize = pageSize,
                enablePlaceholders = false
            ),
            fetchExecutor = executor
        )

        val refreshState = Transformations.switchMap(sourceFactory.sourceLiveData) {
            it.initialLoad
        }

        return Listing(
            pagedList = livePagedList,
            networkState = Transformations.switchMap(sourceFactory.sourceLiveData) {
                it.initialLoad
            },
            retry = {
                sourceFactory.sourceLiveData.value?.retryAllFailed()
            },
            refresh = {
                sourceFactory.sourceLiveData.value?.invalidate()
            },
            refreshState = refreshState
        )
    }

    fun refreshData() {
        sourceFactory.sourceLiveData.value?.invalidate()
    }
}