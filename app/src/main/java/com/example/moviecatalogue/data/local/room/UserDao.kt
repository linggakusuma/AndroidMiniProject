package com.example.moviecatalogue.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.moviecatalogue.data.local.entities.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Query("select * from table_user")
    fun getUser():LiveData<User>

    @Query("delete from table_user")
    fun clear()

    @Update
    fun updateUser(user: User)
}