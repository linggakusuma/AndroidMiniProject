package com.example.moviecatalogue.di.component

import android.app.Application
import com.example.moviecatalogue.di.module.ActivityModule
import com.example.moviecatalogue.di.module.NetworkModule
import com.example.moviecatalogue.di.module.ViewModelModule
import com.example.moviecatalogue.ui.base.BaseApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        NetworkModule::class,
        ViewModelModule::class,
        ActivityModule::class
    ]
)

interface AppComponent : AndroidInjector<DaggerApplication> {

    fun inject(application: BaseApplication)

    override fun inject(instance: DaggerApplication)

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}