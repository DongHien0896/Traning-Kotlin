package com.framgia.kotlintraining.moviedb.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "movie")
data class Movie(

    @SerializedName("vote_count")
    var mVoteCount: Int? = null,

    @SerializedName("id")
    @ColumnInfo(name = "id")
    @PrimaryKey
    var mIdMovie: Int? = null,

    @SerializedName("original_title")
    var mOriginTitle: String? = null,

    @SerializedName("poster_path")
    var mPosterPath: String? = null,

    @SerializedName("backdrop_path")
    var mBackdropPath: String? = null,

    @SerializedName("overview")
    var mOverview: String? = null,

    @SerializedName("release_date")
    var mReleaseDate: String? = null,

    var isFavorite: Boolean? = false
) : Parcelable