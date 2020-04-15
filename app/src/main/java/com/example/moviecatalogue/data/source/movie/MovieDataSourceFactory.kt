package com.example.moviecatalogue.data.source.movie

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.moviecatalogue.data.remote.response.Movie
import com.example.moviecatalogue.data.remote.services.MovieServices

class MovieDataSourceFactory constructor(private val movieServices: MovieServices) :
    DataSource.Factory<Int, Movie>() {

    private val movie = MutableLiveData<MovieDataSource>()

    override fun create(): DataSource<Int, Movie> {
        val source = MovieDataSource(movieServices)
        movie.postValue(source)
        return source
    }
}