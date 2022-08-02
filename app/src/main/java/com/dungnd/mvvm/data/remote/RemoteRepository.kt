package com.dungnd.mvvm.data.remote

import com.dungnd.mvvm.data.remote.service.AnimeService
import com.dungnd.mvvm.data.remote.service.MangaService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteRepository @Inject constructor(
    private val animeService: AnimeService,
    private val mangaService: MangaService
) {
}
