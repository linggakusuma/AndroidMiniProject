package com.example.moviecatalogue.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.auth.FirebaseAuth

val currentUser = FirebaseAuth.getInstance().currentUser

fun getUrl(): String {
    var url: String? = null
    for (userInfo in currentUser?.providerData.orEmpty()) {
        url = userInfo.photoUrl.toString()
    }
    return url.toString()
}

@Entity(tableName = "table_user")
data class User(
    @PrimaryKey
    val uid: String = currentUser?.uid.toString(),
    var name: String? = currentUser?.displayName.toString(),
    var email: String? = currentUser?.email.toString(),
    val phoneNumber: String? = currentUser?.phoneNumber ?: " - ",
    val image: String? = getUrl()
)