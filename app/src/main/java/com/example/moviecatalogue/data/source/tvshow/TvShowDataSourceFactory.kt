package com.example.moviecatalogue.data.source.tvshow

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.moviecatalogue.data.remote.response.Movie
import com.example.moviecatalogue.data.remote.services.MovieServices

class TvShowDataSourceFactory constructor(private val movieServices: MovieServices) :
    DataSource.Factory<Int, Movie>() {

    private val movie = MutableLiveData<TvShowDataSource>()

    override fun create(): DataSource<Int, Movie> {
        val source = TvShowDataSource(movieServices)
        movie.postValue(source)
        return source
    }
}