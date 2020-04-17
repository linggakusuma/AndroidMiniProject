package com.example.moviecatalogue.di.module

import android.app.Application
import androidx.room.Room
import com.example.moviecatalogue.data.local.room.Database
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Provides
    @Singleton
    fun providesDatabase(application: Application): Database =
        Room.databaseBuilder(application, Database::class.java, "MovieCatalogue.db").build()
}