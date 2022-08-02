package com.dungnd.mvvm.data.remote.service

import com.dungnd.mvvm.data.remote.model.People
import retrofit2.http.GET
import retrofit2.http.Path

interface AnimeService {

    @GET("abc/{id}")
    suspend fun getAnimeById(@Path("id") id: Int): People

    @GET("abc/dè/{id}")
    suspend fun getAnimeByGenreId(@Path("id") id: Int): People

}