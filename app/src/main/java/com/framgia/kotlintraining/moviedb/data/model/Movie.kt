package com.framgia.kotlintraining.moviedb.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.lang.reflect.Type

@Parcelize
@Entity(tableName = "movie")
data class Movie(

    @PrimaryKey(autoGenerate = false)
    var mId: String = "",

    @Ignore
    @SerializedName("vote_count")
    var mVoteCount: Int,

    @SerializedName("id")
    var mIdMovie: Int,

    @SerializedName("original_title")
    var mOriginTitle: String,

    @SerializedName("poster_path")
    var mPosterPath: String,

    @Ignore
    @SerializedName("backdrop_path")
    var mBackdropPath: String,

    @Ignore
    @SerializedName("overview")
    var mOverview: String,

    @Ignore
    @SerializedName("release_date")
    var mReleaseDate: String
) : Parcelable {
    object DataStateDeserializer : JsonDeserializer<Movie> {
        override fun deserialize(
            json: JsonElement?,
            typeOfT: Type?,
            context: JsonDeserializationContext?
        ): Movie {
            val movie: Movie = Gson().fromJson(json, Movie::class.java)
            return movie
        }

    }
}