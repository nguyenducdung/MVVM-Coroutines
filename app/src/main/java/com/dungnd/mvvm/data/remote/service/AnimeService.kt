package com.dungnd.mvvm.data.remote.service

import com.dungnd.mvvm.data.remote.model.ProductResponse
import com.dungnd.mvvm.data.remote.model.AddressData
import com.dungnd.mvvm.data.remote.model.CreateProductRequest
import com.dungnd.mvvm.data.remote.model.Photo
import com.dungnd.mvvm.data.remote.model.ProductData
import retrofit2.http.*

interface AnimeService {

    @GET("photos")
    suspend fun getAllPhoto(): List<Photo>

    @GET("products")
    suspend fun getAllProducts(): List<ProductData>

    @GET
    suspend fun getAllAddress(
        @Url url: String,
        @Query("_quantity") quantity: Int,
        @Query("_locale") locale: String
    ): AddressData

    @POST("products")
    suspend fun createProduct(@Body request: CreateProductRequest): Any?

    @GET("products")
    suspend fun getAllProductsV2(@Query("_quantity") quantity: Int): ProductResponse
}