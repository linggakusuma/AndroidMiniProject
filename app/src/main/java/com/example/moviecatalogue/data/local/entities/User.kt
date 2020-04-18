package com.example.moviecatalogue.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_user")
data class User(
    @PrimaryKey
    val uid: String,
    var name: String? = "",
    var email: String? = "",
    val phoneNumber: String? = "",
    val image: String? = ""
)