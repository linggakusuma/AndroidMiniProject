package com.example.moviecatalogue.data.remote.response

import com.example.moviecatalogue.utils.ext.parseDateWithFormat
import com.squareup.moshi.Json

data class DetailResponse(
    val id: String?,
    @Json(name = "poster_path") val posterPath: String?,
    @Json(name = "backdrop_path") val backdropPath: String?,
    @Json(name = "vote_average") val voteAverage: String?,
    @Json(name = "release_date") val releaseDate: String?,
    @Json(name = "first_air_date") val firstAirDate: String?,
    val overview: String?,
    val title: String?,
    val name: String?,
    val genres: List<Genre>
) {
    fun getRatingBar(): Float = if (voteAverage != null) voteAverage.toFloat() / 2 else 0f

    fun getReleaseDateFormat(): String? =
        if (releaseDate.isNullOrEmpty()) firstAirDate?.parseDateWithFormat("MM/dd/yyyy")
        else releaseDate.parseDateWithFormat("MM/dd/yyyy")

    fun getGenre(): String? = genres.joinToString { it.name.toString() }
}