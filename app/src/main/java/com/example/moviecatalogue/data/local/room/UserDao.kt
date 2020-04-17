package com.example.moviecatalogue.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviecatalogue.data.local.entities.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Query("select * from table_user")
    fun getCurrentUser(): User

    @Query("select * from table_user")
    fun getUser():LiveData<User>

    @Query("delete from table_user")
    fun clear()
}