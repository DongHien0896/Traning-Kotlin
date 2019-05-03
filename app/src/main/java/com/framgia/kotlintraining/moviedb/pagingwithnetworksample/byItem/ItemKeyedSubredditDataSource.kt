package com.framgia.kotlintraining.moviedb.pagingwithnetworksample.byItem

import androidx.lifecycle.MutableLiveData
import androidx.paging.ItemKeyedDataSource
import com.framgia.kotlintraining.moviedb.data.model.RedditPost
import com.framgia.kotlintraining.moviedb.data.source.network.NetworkState
import com.framgia.kotlintraining.moviedb.data.source.repository.RedditPostRepository
import com.framgia.kotlintraining.moviedb.utils.applyAsyncSchedulersSingle
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.Executor


/**
 * A data source that uses the "name" field of posts as the key for next/prev pages.
 */
class ItemKeyedSubredditDataSource(
    private val repository: RedditPostRepository,
    private val subredditName: String,
    private val retryExecutor: Executor
) : ItemKeyedDataSource<String, RedditPost>() {

    private var compositeDisposable = CompositeDisposable()
    private var retry: (() -> Any)? = null
    val networkState = MutableLiveData<NetworkState>()
    val initialLoad = MutableLiveData<NetworkState>()

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
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<RedditPost>
    ) {
        val disposable = repository.getTop(
            subredditName,
            params.requestedLoadSize
        ).compose(applyAsyncSchedulersSingle())
            .subscribe({
                retry = null
                networkState.postValue(NetworkState.LOADED)
                initialLoad.postValue(NetworkState.LOADED)
                callback.onResult(it.data.children?.map { it.data })
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

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<RedditPost>) {
        networkState.postValue(NetworkState.LOADING)
        val disposable = repository.getTopAfter(
            subredditName,
            params.key,
            params.requestedLoadSize
        ).compose(applyAsyncSchedulersSingle())
            .subscribe({
                retry = null
                networkState.postValue(NetworkState.LOADED)
                callback.onResult(it.data.children?.map { it.data })
            }, {
                retry = {
                    loadAfter(params, callback)
                }
                networkState.postValue(
                    NetworkState.error("error: ${it.message}")
                )
            })
        compositeDisposable.add(disposable)
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<RedditPost>) {
        //ignore
    }

    override fun getKey(item: RedditPost): String = item.name

    fun clear() {
        compositeDisposable.clear()
    }
}