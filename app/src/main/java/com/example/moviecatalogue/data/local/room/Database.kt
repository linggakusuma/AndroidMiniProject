package com.example.moviecatalogue.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviecatalogue.data.local.entities.User

@Database(
    entities = [
        User::class
    ], version = 1
)

abstract class Database : RoomDatabase() {
    abstract fun userDao(): UserDao
}