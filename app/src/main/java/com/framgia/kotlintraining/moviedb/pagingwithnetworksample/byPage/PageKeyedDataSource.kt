package com.framgia.kotlintraining.moviedb.pagingwithnetworksample.byPage

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.framgia.kotlintraining.moviedb.data.model.Movie
import com.framgia.kotlintraining.moviedb.data.source.network.NetworkState
import com.framgia.kotlintraining.moviedb.data.source.repository.MovieRepository
import com.framgia.kotlintraining.moviedb.utils.applyAsyncSchedulersSingle
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.Executor

const val STARTED_PAGE = 1

class PageKeyedDataSource(
    private val repository: MovieRepository,
    private val retryExecutor: Executor
) : PageKeyedDataSource<Int, Movie>() {

    private val compositeDisposable = CompositeDisposable()

    val initialLoad = MutableLiveData<NetworkState>()
    val networkState = MutableLiveData<NetworkState>()
    private var retry: (() -> Any)? = null

    fun retryAllFailed() {
        val prevRetry = retry
        retry = null
        prevRetry?.let {
            retryExecutor.execute {
                it.invoke()
            }
        }
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>
    ) {
        networkState.postValue(NetworkState.LOADING)
        initialLoad.postValue(NetworkState.LOADING)

        val disposable = repository.getMovieList(STARTED_PAGE)
            .compose(applyAsyncSchedulersSingle())
            .subscribe({
                retry = null
                networkState.postValue(NetworkState.LOADED)
                initialLoad.postValue(NetworkState.LOADED)
                callback.onResult(it.mListMovie ?: emptyList(), null, STARTED_PAGE + 1)
            }, {
                retry = {
                    loadInitial(params, callback)
                }
                val error = NetworkState.error(it.message ?: "unknown error")
                networkState.postValue(error)
                initialLoad.postValue(error)
            })
        compositeDisposable.add(disposable)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        networkState.postValue(NetworkState.LOADING)
        val disposable = repository.getMovieList(params.key)
            .compose(applyAsyncSchedulersSingle())
            .subscribe({
                retry = null
                networkState.postValue(NetworkState.LOADED)
                val nextKey = if (params.key == it.mTotalPages) null else (params.key + 1)
                callback.onResult(it.mListMovie ?: emptyList(), nextKey)
            }, {
                retry = {
                    loadAfter(params, callback)
                }
                networkState.postValue(NetworkState.error(it.message ?: "unknown err"))
            })
        compositeDisposable.add(disposable)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        //ignore
    }

    fun clear() {
        compositeDisposable.clear()
    }
}