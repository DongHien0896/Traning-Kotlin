package com.framgia.kotlintraining.moviedb.data.source.repository.impl

import com.framgia.kotlintraining.moviedb.data.source.network.Api
import com.framgia.kotlintraining.moviedb.data.source.network.RedditApi
import com.framgia.kotlintraining.moviedb.data.source.repository.RedditPostRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RedditPostRepositoryImpl constructor(
    private val api: Api
) : RedditPostRepository {

    override fun getTop(subReddit: String, pageSize: Int): Single<RedditApi.ListingResponse> {
        return api.getTop(subReddit, pageSize)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getTopAfter(
        subReddit: String,
        after: String,
        pageSize: Int
    ): Single<RedditApi.ListingResponse> {
        return api.getTopAfter(subReddit, after, pageSize)
    }
}