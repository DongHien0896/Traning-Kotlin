package com.framgia.kotlintraining.moviedb.data.source.network

import com.framgia.kotlintraining.moviedb.data.model.Movie
import com.framgia.kotlintraining.moviedb.data.model.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("movie/popular")
    fun getMoviePopular(@Query("page") pageNumber: Int): Single<MovieResponse>

    @GET("movie/{movie_id}")
    fun getDetailMovie(@Path("movie_id") idMovie: Int): Single<Movie>
}