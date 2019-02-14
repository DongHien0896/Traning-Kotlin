package com.framgia.kotlintraining.moviedb.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "movie")
data class Movie(

    @PrimaryKey(autoGenerate = false)
    var mId: String = "",

    @Ignore
    @SerializedName("vote_count")
    var mVoteCount: Int? = null,

    @SerializedName("id")
    var mIdMovie: Int? = null,

    @SerializedName("original_title")
    var mOriginTitle: String? = null,

    @SerializedName("poster_path")
    var mPosterPath: String? = null,

    @Ignore
    @SerializedName("backdrop_path")
    var mBackdropPath: String? = null,

    @Ignore
    @SerializedName("overview")
    var mOverview: String? = null,

    @Ignore
    @SerializedName("release_date")
    var mReleaseDate: String? = null
) : Parcelable