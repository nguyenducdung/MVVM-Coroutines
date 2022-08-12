package com.dungnd.mvvm.data.remote

import com.dungnd.mvvm.data.remote.model.ProductResponse
import com.dungnd.mvvm.data.remote.model.AddressData
import com.dungnd.mvvm.data.remote.model.CreateProductRequest
import com.dungnd.mvvm.data.remote.model.Photo
import com.dungnd.mvvm.data.remote.model.ProductData
import com.dungnd.mvvm.data.remote.service.AnimeService
import com.dungnd.mvvm.data.remote.service.MangaService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteRepository @Inject constructor(
    private val animeService: AnimeService,
    private val mangaService: MangaService
) {

    suspend fun getAllPhotos(): List<Photo> {
        return animeService.getAllPhoto()
    }

    suspend fun getAllProducts(): List<ProductData> {
        //Gọi api bằng retrofit
        return animeService.getAllProducts()
    }

    suspend fun getAllAddress(): AddressData {
        return animeService.getAllAddress("https://fakerapi.it/api/v1/addresses", 80, "en_US")
    }

    suspend fun createProduct(): Any? {
        val request = CreateProductRequest(
            title = "HIHI",
            price = 12.3,
            description = "đây là hihi",
            image = "https: //i.pravatar.cc",
            category = "electronic"
        )
        return animeService.createProduct(request)
    }

    suspend fun getAllProductV2(): ProductResponse {
        return animeService.getAllProductsV2(10)
    }
}
