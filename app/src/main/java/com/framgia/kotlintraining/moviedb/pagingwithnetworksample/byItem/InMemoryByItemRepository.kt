package com.framgia.kotlintraining.moviedb.pagingwithnetworksample.byItem

import androidx.lifecycle.Transformations
import androidx.paging.Config
import androidx.paging.toLiveData
import com.framgia.kotlintraining.moviedb.data.model.Listing
import com.framgia.kotlintraining.moviedb.data.model.RedditPost
import com.framgia.kotlintraining.moviedb.data.source.repository.RedditPostRepository
import java.util.concurrent.Executor

class InMemoryByItemRepository(
    private val redditPostRepository: RedditPostRepository,
    private val networkExecutor: Executor
) {

    private lateinit var sourceFactory: SubRedditDataSourceFactory


    fun postsOfSubreddit(subReddit: String, pageSize: Int): Listing<RedditPost> {
        sourceFactory =
            SubRedditDataSourceFactory(redditPostRepository, subReddit, networkExecutor)

        val livePagedList = sourceFactory.toLiveData(
            config = Config(
                pageSize = pageSize,
                enablePlaceholders = false,
                initialLoadSizeHint = pageSize * 3
            ),
            fetchExecutor = networkExecutor
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