package com.framgia.kotlintraining.moviedb.pagingwithnetworksample.byItem.ui

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.framgia.kotlintraining.moviedb.base.BaseViewModel
import com.framgia.kotlintraining.moviedb.data.model.RedditPost
import com.framgia.kotlintraining.moviedb.data.source.repository.RedditPostRepository
import com.framgia.kotlintraining.moviedb.pagingwithnetworksample.byItem.InMemoryByItemRepository
import java.util.concurrent.Executor

class RedditVewModel(repository: RedditPostRepository) : BaseViewModel() {

    private val networkExecutor = Executor { command -> command.run() }
    private val byItemRepository = InMemoryByItemRepository(repository, networkExecutor)

    fun getReddit(): LiveData<PagedList<RedditPost>> =
        byItemRepository.postsOfSubreddit("androiddev", 10).pagedList

    fun refreshData() {
        byItemRepository.refreshData()
    }
}