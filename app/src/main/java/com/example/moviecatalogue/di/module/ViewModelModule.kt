package com.example.moviecatalogue.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviecatalogue.di.scope.ViewModelKey
import com.example.moviecatalogue.ui.detail.DetailViewModel
import com.example.moviecatalogue.ui.detailtvshow.DetailTvShowViewModel
import com.example.moviecatalogue.ui.movie.MovieViewModel
import com.example.moviecatalogue.ui.search.SearchViewModel
import com.example.moviecatalogue.ui.tvshow.TvShowViewModel
import com.example.moviecatalogue.utils.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MovieViewModel::class)
    internal abstract fun movieViewModel(viewModel: MovieViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TvShowViewModel::class)
    internal abstract fun tvShowViewModel(viewModel: TvShowViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    internal abstract fun detailViewModel(viewModel: DetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailTvShowViewModel::class)
    internal abstract fun detailTvShowViewModel(viewModel: DetailTvShowViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    internal abstract fun searchViewMode(viewModel: SearchViewModel): ViewModel
}