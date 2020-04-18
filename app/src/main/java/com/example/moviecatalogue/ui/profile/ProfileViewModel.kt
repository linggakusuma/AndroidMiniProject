package com.example.moviecatalogue.ui.profile

import com.example.moviecatalogue.data.local.room.Database
import com.example.moviecatalogue.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProfileViewModel @Inject constructor(private val database: Database) : BaseViewModel() {

    private val coroutinScope = CoroutineScope(Dispatchers.Main)

    var user = database.userDao().getUser()

    private suspend fun clear() {
        withContext(Dispatchers.IO) {
            database.userDao().clear()
        }
    }

    fun onClear() {
        coroutinScope.launch {
            clear()
        }
    }
}