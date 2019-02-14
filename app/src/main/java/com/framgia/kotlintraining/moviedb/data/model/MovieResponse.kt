package com.framgia.kotlintraining.moviedb.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieResponse(
    @SerializedName("total_pages")
    var mTotalPages: Int,

    @SerializedName("results")
    var mListMovie: List<Movie>
)