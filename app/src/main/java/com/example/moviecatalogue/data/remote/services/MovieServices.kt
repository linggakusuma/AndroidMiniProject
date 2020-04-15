package com.example.moviecatalogue.data.remote.services

import com.example.moviecatalogue.BuildConfig
import com.example.moviecatalogue.data.remote.response.DetailResponse
import com.example.moviecatalogue.data.remote.response.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieServices {
    @GET("movie/popular")
    fun getMovie(
        @Query("api_key") apiKey: String? = BuildConfig.TMDB_API_KEY,
        @Query("page") page: Int? = 1
    ): Single<MovieResponse>

    @GET("tv/popular")
    fun getTvShow(
        @Query("api_key") apiKey: String? = BuildConfig.TMDB_API_KEY,
        @Query("page") page: Int? = 1
    ): Single<MovieResponse>

    @GET("movie/{movie_id}")
    fun getDetailMovie(
        @Path("movie_id") id: Int?,
        @Query("api_key") apiKey: String? = BuildConfig.TMDB_API_KEY
    ): Single<DetailResponse>

    @GET("tv/{tv_id}")
    fun getDetailTv(
        @Path("tv_id") id: Int?,
        @Query("api_key") apiKey: String? = BuildConfig.TMDB_API_KEY
    ): Single<DetailResponse>

    @GET("search/movie")
    fun getSearch(
        @Query("api_key") apiKey: String? = BuildConfig.TMDB_API_KEY,
        @Query("query") query: String?,
        @Query("page") page: Int? = 1
    ): Single<MovieResponse>
}