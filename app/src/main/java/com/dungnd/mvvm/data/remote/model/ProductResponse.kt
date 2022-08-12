package com.dungnd.mvvm.data.remote.model

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("code")
    val code: Int? = null,
    @SerializedName("total")
    val total: Int? = null,
    @SerializedName("data")
    val data: List<Product>? = null,
)

data class Image(
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("url")
    val url: String? = null
)
