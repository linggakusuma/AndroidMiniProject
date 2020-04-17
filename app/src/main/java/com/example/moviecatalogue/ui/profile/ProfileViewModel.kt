package com.example.moviecatalogue.ui.profile

import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.data.local.entities.User
import com.example.moviecatalogue.data.local.room.Database
import com.example.moviecatalogue.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProfileViewModel @Inject constructor(private val database: Database) : BaseViewModel() {

    private val coroutinScope = CoroutineScope(Dispatchers.Main)

    private var currentUser = MutableLiveData<User>()

    val user = database.userDao().getUser()

    private suspend fun getUserFromDatabase(): User? {
        return withContext(Dispatchers.IO) {
            val user = database.userDao().getCurrentUser()
            user
        }
    }

    fun onSave() {
        coroutinScope.launch {
            val newNight = User()

            insert(newNight)

            currentUser.value = getUserFromDatabase()
        }
    }

    private suspend fun insert(user: User) {
        withContext(Dispatchers.IO) {
            database.userDao().insertUser(user)
        }
    }

    private suspend fun clear() {
        withContext(Dispatchers.IO) {
            database.userDao().clear()
        }
    }

    fun onClear() {
        coroutinScope.launch {
            clear()
            currentUser.value = null
        }
    }
}