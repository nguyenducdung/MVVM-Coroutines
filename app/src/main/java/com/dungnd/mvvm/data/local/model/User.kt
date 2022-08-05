package com.dungnd.mvvm.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey
    val name: String = "",
    val address: String = "",
    val phone: String = ""
)
