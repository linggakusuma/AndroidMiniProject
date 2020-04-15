package com.example.moviecatalogue.di.module

import com.example.moviecatalogue.di.scope.ActivityScoped
import com.example.moviecatalogue.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeMainActivity(): MainActivity

}