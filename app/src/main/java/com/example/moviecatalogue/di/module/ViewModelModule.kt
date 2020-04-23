package com.example.moviecatalogue.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviecatalogue.di.scope.ViewModelKey
import com.example.moviecatalogue.ui.detail.DetailViewModel
import com.example.moviecatalogue.ui.detailtvshow.DetailTvShowViewModel
import com.example.moviecatalogue.ui.login.LoginViewModel
import com.example.moviecatalogue.ui.movie.MovieViewModel
import com.example.moviecatalogue.ui.notification.MovieNotificationViewModel
import com.example.moviecatalogue.ui.profile.ProfileViewModel
import com.example.moviecatalogue.ui.search.SearchViewModel
import com.example.moviecatalogue.ui.searchtv.SearchTvShowViewModel
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

    @Binds
    @IntoMap
    @ViewModelKey(SearchTvShowViewModel::class)
    internal abstract fun searchTvViewModel(viewModel: SearchTvShowViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    internal abstract fun profileViewModel(viewModel: ProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    internal abstract fun loginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieNotificationViewModel::class)
    internal abstract fun movieNotificationViewModel(viewModel: MovieNotificationViewModel): ViewModel
}