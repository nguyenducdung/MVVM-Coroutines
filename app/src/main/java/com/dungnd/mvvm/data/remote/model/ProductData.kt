package com.dungnd.mvvm.data.remote.model

import com.google.gson.annotations.SerializedName

data class ProductData(
    //Tên key
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("price")
    val price: Double? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("category")
    val category: String? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("rating")
    val rating: RatingData? = null
)

data class RatingData(
    @SerializedName("rate")
    val rate: Double? = null,
    @SerializedName("count")
    val count: Int? = null
)
