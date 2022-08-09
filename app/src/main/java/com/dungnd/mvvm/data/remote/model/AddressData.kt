package com.dungnd.mvvm.data.remote.model

import com.google.gson.annotations.SerializedName

data class AddressData(
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("code")
    val code: Int? = null,
    @SerializedName("total")
    val total: Int? = null,
    @SerializedName("data")
    val data: List<Address>? = null,
)

data class Address(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("street")
    val street: String? = null,
    @SerializedName("streetName")
    val streetName: String? = null,
    @SerializedName("buildingNumber")
    val buildingNumber: String? = null,
    @SerializedName("city")
    val city: String? = null
)
