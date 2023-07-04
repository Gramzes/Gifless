package com.gramzin.gifless.data.api.giphy_api

import com.gramzin.gifless.data.api.giphy_api.model.GifApiResponse
import com.gramzin.gifless.data.model.GifData
import com.gramzin.gifless.data.storage.GifRemoteDataStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GiphyRemoteDataStorageImpl @Inject constructor(private val service: GiphyGifsApi): GifRemoteDataStorage {
    private var offsetInProgramming: Int = 0
    private var offsetInTop: Int = 0

    override suspend fun getProgrammingGifs(): List<GifData> {
        val result = withContext(Dispatchers.IO) {
            service.getProgrammingGifs(offsetInProgramming)
        }
        offsetInProgramming += GiphyGifsApi.LIMIT_COUNT
        return result.mapToGifData()
    }

    override suspend fun getTopGifs(): List<GifData> {
        val result = withContext(Dispatchers.IO) {
            service.getTopGifs(offsetInTop)
        }
        offsetInTop += GiphyGifsApi.LIMIT_COUNT
        return result.mapToGifData()
    }
}

fun GifApiResponse.mapToGifData(): List<GifData>{
    return data.map {
        GifData(
            url = it.images.original.url,
            description = it.title
        )
    }
}
