package com.example.moviecatalogue.di.module

import com.example.moviecatalogue.di.scope.FragmentScoped
import com.example.moviecatalogue.ui.detail.DetailFragment
import com.example.moviecatalogue.ui.detailtvshow.DetailTvShowFragment
import com.example.moviecatalogue.ui.movie.MovieFragment
import com.example.moviecatalogue.ui.profile.BottomSheetDialogFragment
import com.example.moviecatalogue.ui.profile.ProfileFragment
import com.example.moviecatalogue.ui.search.SearchFragment
import com.example.moviecatalogue.ui.searchtv.SearchTvShowFragment
import com.example.moviecatalogue.ui.tvshow.TvShowFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributeMovieFragment(): MovieFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributeTvShowFragment(): TvShowFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributeDetailFragment(): DetailFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributeDetailTvFragment(): DetailTvShowFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributeSearchFragment(): SearchFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributeSearchTvShowFragment(): SearchTvShowFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributeProfileFragment(): ProfileFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributeBottomSheetDialogFragment(): BottomSheetDialogFragment
}