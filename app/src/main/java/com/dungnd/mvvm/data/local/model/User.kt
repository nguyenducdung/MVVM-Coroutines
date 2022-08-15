package com.dungnd.mvvm.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey
    val id: String = "",
    val name: String,
    val email: String,
    val image: String,
    val passWord: String
)