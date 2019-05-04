package com.framgia.kotlintraining.moviedb.pagingwithnetworksample.byItem

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.framgia.kotlintraining.moviedb.data.model.RedditPost
import com.framgia.kotlintraining.moviedb.data.source.repository.RedditPostRepository
import java.util.concurrent.Executor

class SubRedditDataSourceFactory(
    private val repository: RedditPostRepository,
    private val subRedditName: String,
    private val retryExecutor: Executor
) : DataSource.Factory<String, RedditPost>() {

    val sourceLiveData = MutableLiveData<ItemKeyedSubredditDataSource>()

    override fun create(): DataSource<String, RedditPost> {
        val source = ItemKeyedSubredditDataSource(repository, subRedditName, retryExecutor)
        sourceLiveData.postValue(source)
        source.clear()
        return source
    }
}