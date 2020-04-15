package com.example.moviecatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.moviecatalogue.data.remote.response.Movie
import com.example.moviecatalogue.data.remote.services.MovieServices
import com.example.moviecatalogue.data.source.movie.MovieDataSourceFactory
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MovieViewModel @Inject constructor(movieServices: MovieServices) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

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

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}