package com.gramzin.gifless.data.api

import com.gramzin.gifless.data.api.models.Gif
import com.gramzin.gifless.data.storage.GifRemoteDataStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GifRemoteDataStorageImpl @Inject constructor(private val service: GifsApi): GifRemoteDataStorage {
    private var nextPageInProgramming: String? = null
    private var nextPageInTop: String? = null

    override suspend fun getProgrammingGifs(): List<Gif> {
        val result = withContext(Dispatchers.IO) {
            service.getProgrammingGifs(nextPageInProgramming)
        }
        nextPageInProgramming = result.next

        val gifs = result.results.map { res ->
            res.media_formats.gif.apply {
                description = res.content_description
            }
        }
        return gifs
    }

    override suspend fun getTopGifs(): List<Gif> {
        val result = withContext(Dispatchers.IO) {
            service.getTopGifs(nextPageInTop)
        }
        nextPageInTop = result.next

        val gifs = result.results.map { res ->
            res.media_formats.gif.apply {
                description = res.content_description
            }
        }
        return gifs
    }

}