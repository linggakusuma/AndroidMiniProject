package com.example.moviecatalogue.data.remote.services

import com.example.moviecatalogue.BuildConfig
import com.example.moviecatalogue.data.remote.response.DetailResponse
import com.example.moviecatalogue.data.remote.response.MovieResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieServices {
    @GET("movie/popular")
    fun getMovieAsync(
        @Query("api_key") apiKey: String? = BuildConfig.TMDB_API_KEY,
        @Query("page") page: Int? = 1
    ): Deferred<MovieResponse>

    @GET("tv/popular")
    fun getTvShowAsync(
        @Query("api_key") apiKey: String? = BuildConfig.TMDB_API_KEY,
        @Query("page") page: Int? = 1
    ): Deferred<MovieResponse>

    @GET("movie/{movie_id}")
    fun getDetailMovieAsync(
        @Path("movie_id") id: Int?,
        @Query("api_key") apiKey: String? = BuildConfig.TMDB_API_KEY
    ): Deferred<DetailResponse>

    @GET("tv/{tv_id}")
    fun getDetailTvAsync(
        @Path("tv_id") id: Int?,
        @Query("api_key") apiKey: String? = BuildConfig.TMDB_API_KEY
    ): Deferred<DetailResponse>

    @GET("search/movie")
    fun getSearchAsync(
        @Query("api_key") apiKey: String? = BuildConfig.TMDB_API_KEY,
        @Query("query") query: String?,
        @Query("page") page: Int? = 1
    ): Deferred<MovieResponse>
}