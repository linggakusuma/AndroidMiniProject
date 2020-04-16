package com.example.moviecatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.moviecatalogue.data.remote.response.Movie
import com.example.moviecatalogue.data.remote.services.MovieServices
import com.example.moviecatalogue.data.source.movie.MovieDataSourceFactory
import com.example.moviecatalogue.ui.base.BaseViewModel
import javax.inject.Inject

class MovieViewModel @Inject constructor(movieServices: MovieServices) : BaseViewModel() {

    private val dataSourceFactory = MovieDataSourceFactory(movieServices)

    private val config = PagedList.Config.Builder()
        .setPageSize(10)
        .setInitialLoadSizeHint(10)
        .setEnablePlaceholders(false)
        .build()


    val movie: LiveData<PagedList<Movie>>

    init {
        movie = LivePagedListBuilder(dataSourceFactory, config).build()
    }
}