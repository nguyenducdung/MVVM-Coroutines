package com.dungnd.mvvm.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavouriteQuote(
    @PrimaryKey val id: String,
    val anime: String = "",
    val character: String = "",
    val quote: String = ""
)