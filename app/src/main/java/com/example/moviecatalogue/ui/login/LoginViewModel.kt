package com.example.moviecatalogue.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.data.local.entities.User
import com.example.moviecatalogue.data.local.room.Database
import com.example.moviecatalogue.ui.base.BaseViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val database: Database) : BaseViewModel() {

    private val coroutinScope = CoroutineScope(Dispatchers.Main)

    private val auth = FirebaseAuth.getInstance().currentUser

    private var _user = MutableLiveData<User>()
    private val user: LiveData<User>
        get() = _user


    init {
        _user.value = User(
            uid = auth?.uid ?: "",
            name = auth?.displayName,
            email = auth?.email,
            phoneNumber = auth?.phoneNumber,
            image = getUrl()
        )
    }

    private fun getUrl(): String {
        var url: String? = null
        for (userInfo in auth?.providerData.orEmpty()) {
            url = userInfo.photoUrl.toString()
        }
        return url.toString()
    }

    fun onSave() {
        coroutinScope.launch {

            user.value?.let { insert(it) }
        }
    }

    private suspend fun insert(user: User) {
        withContext(Dispatchers.IO) {
            database.userDao().insertUser(user)
        }
    }
}