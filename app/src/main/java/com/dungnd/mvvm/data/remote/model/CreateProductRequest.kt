package com.dungnd.mvvm.data.remote.model

import com.google.gson.annotations.SerializedName

//Phần body đẩy lên backend
data class CreateProductRequest (
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("price")
    val price: Double? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("category")
    val category: String? = null
)