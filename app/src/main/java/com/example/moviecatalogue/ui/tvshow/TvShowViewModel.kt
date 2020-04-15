package com.example.moviecatalogue.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.moviecatalogue.data.remote.response.Movie
import com.example.moviecatalogue.data.remote.services.MovieServices
import com.example.moviecatalogue.data.source.movie.MovieDataSourceFactory
import com.example.moviecatalogue.data.source.tvshow.TvShowDataSourceFactory
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class TvShowViewModel @Inject constructor(private val movieServices: MovieServices) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val dataSourceFactory = TvShowDataSourceFactory(movieServices)

    private val config = PagedList.Config.Builder()
        .setPageSize(10)
        .setInitialLoadSizeHint(10)
        .setEnablePlaceholders(false)
        .build()

    val tvShow: LiveData<PagedList<Movie>>

    init {
        tvShow = LivePagedListBuilder(dataSourceFactory, config).build()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}