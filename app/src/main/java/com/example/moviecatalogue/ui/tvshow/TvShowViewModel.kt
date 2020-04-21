package com.example.moviecatalogue.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.moviecatalogue.data.remote.response.Movie
import com.example.moviecatalogue.data.remote.services.MovieServices
import com.example.moviecatalogue.data.source.tvshow.TvShowDataSourceFactory
import com.example.moviecatalogue.ui.base.BaseViewModel
import javax.inject.Inject

class TvShowViewModel @Inject constructor(movieServices: MovieServices) : BaseViewModel() {

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
}