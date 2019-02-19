package com.framgia.kotlintraining.moviedb.data.source.repository.impl

import com.framgia.kotlintraining.moviedb.data.model.Movie
import com.framgia.kotlintraining.moviedb.data.model.MovieResponse
import com.framgia.kotlintraining.moviedb.data.source.local.dao.MovieDAO
import com.framgia.kotlintraining.moviedb.data.source.network.Api
import com.framgia.kotlintraining.moviedb.data.source.repository.MovieRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MovieRepositoryImpl constructor(
    private val api: Api,
    private val movieDAO: MovieDAO
) : MovieRepository {

    //remote
    override fun getMovieList(pageNumber: Int): Single<MovieResponse> {
        return api.getMoviePopular(pageNumber)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    //local
    override fun getMovieById(id: String): Single<Movie> = movieDAO.getMovieById(id)

    override fun insertMovie(movie: Movie): Int = movieDAO.insertMovie(movie)

    override fun deleteMovie(movie: Movie): Int = movieDAO.deleteMovie(movie)

    override fun getListMovie(): Single<List<Movie>> = movieDAO.getListMovie()
}