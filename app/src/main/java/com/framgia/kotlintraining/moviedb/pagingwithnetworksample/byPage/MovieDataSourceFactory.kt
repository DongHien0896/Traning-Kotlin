package com.framgia.kotlintraining.moviedb.pagingwithnetworksample.byPage

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.framgia.kotlintraining.moviedb.data.model.Movie
import com.framgia.kotlintraining.moviedb.data.source.repository.MovieRepository
import java.util.concurrent.Executor

class MovieDataSourceFactory(
    private val repository: MovieRepository,
    private val retryExecutor: Executor
) : DataSource.Factory<Int, Movie>() {

    val sourceLiveData = MutableLiveData<PageKeyedDataSource>()

    override fun create(): DataSource<Int, Movie> {
        val source = PageKeyedDataSource(repository, retryExecutor)
        sourceLiveData.postValue(source)
        source.clear()
        return source
    }
}