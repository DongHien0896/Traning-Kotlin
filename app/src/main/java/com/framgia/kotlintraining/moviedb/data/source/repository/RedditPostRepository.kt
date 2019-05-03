package com.framgia.kotlintraining.moviedb.data.source.repository

import com.framgia.kotlintraining.moviedb.data.source.network.RedditApi
import io.reactivex.Single

interface RedditPostRepository {

    fun getTop(subReddit: String, pageSize: Int): Single<RedditApi.ListingResponse>

    fun getTopAfter(
        subReddit: String,
        after: String,
        pageSize: Int
    ): Single<RedditApi.ListingResponse>
}