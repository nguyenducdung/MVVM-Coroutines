package com.dungnd.mvvm.data.remote.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("images")
    val images: List<Image>? = null
)