package com.framgia.kotlintraining.moviedb.data.source.network

import android.util.Log
import com.framgia.kotlintraining.moviedb.data.model.RedditPost
import io.reactivex.Single
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RedditApi {

    @GET("/r/{subreddit}/hot.json")
    fun getTop(
        @Path("subreddit") subreddit: String,
        @Query("limit") limit: Int
    ): Single<ListingResponse>

    @GET("/r/{subreddit}/hot.json")
    fun getTopAfter(
        @Path("subreddit") subreddit: String,
        @Query("after") after: String,
        @Query("limit") limit: Int
    ): Single<ListingResponse>

    class ListingResponse(val data: ListingData)

    class ListingData(
        val children: List<RedditChildrenResponse>,
        val after: String?,
        val before: String?
    )

    data class RedditChildrenResponse(val data: RedditPost)

//    companion object {
//        private const val BASE_URL = "https://www.reddit.com/"
//        fun create(): RedditApi = create(HttpUrl.parse(BASE_URL)!!)
//        fun create(httpUrl: HttpUrl): RedditApi {
//            val logger = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
//                Log.d("API", it)
//            })
//            logger.level = HttpLoggingInterceptor.Level.BODY
//
//            val client = OkHttpClient.Builder()
//                .addInterceptor(logger)
//                .build()
//            return Retrofit.Builder()
//                .baseUrl(httpUrl)
//                .client(client)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//                .create(RedditApi::class.java)
//        }
//    }
}