package com.framgia.kotlintraining.moviedb.data.model

import com.google.gson.annotations.SerializedName

class MovieResponse(
    @SerializedName("total_pages")
    var mTotalPages: Int? = null,

    @SerializedName("results")
    var mListMovie: ArrayList<Movie>? = null
)