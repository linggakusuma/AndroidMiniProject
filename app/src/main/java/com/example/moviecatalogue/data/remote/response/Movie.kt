package com.example.moviecatalogue.data.remote.response

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: String?,
    @Json(name = "backdrop_path") val backdropPath: String?,
    @Json(name = "vote_average") val voteAverage: String?,
    @Json(name = "genre_ids") val genreId: List<Int>?,
    val title: String?,
    val name: String?
) : Parcelable {
    fun getConvertTitle(): String = if (title.isNullOrEmpty()) name ?: "" else title ?: ""
}
