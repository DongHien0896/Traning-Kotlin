package com.framgia.kotlintraining.moviedb.data.source.network

import com.framgia.kotlintraining.moviedb.data.model.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("https://api.themoviedb.org/3/movie/popular?api_key=3956f50a726a2f785334c24759b97dc6")
    fun getMoviePopular(@Query("page") pageNumber: Int): Single<MovieResponse>

    @GET("https://www.reddit.com//r/{subreddit}/hot.json")
    fun getTop(
        @Path("subreddit") subreddit: String,
        @Query("limit") limit: Int
    ): Single<RedditApi.ListingResponse>

    @GET("https://www.reddit.com//r/{subreddit}/hot.json")
    fun getTopAfter(
        @Path("subreddit") subreddit: String,
        @Query("after") after: String,
        @Query("limit") limit: Int
    ): Single<RedditApi.ListingResponse>
}