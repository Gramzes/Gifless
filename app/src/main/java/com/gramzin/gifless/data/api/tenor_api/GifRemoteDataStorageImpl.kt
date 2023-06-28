package com.gramzin.gifless.data.api.tenor_api

import com.gramzin.gifless.data.api.tenor_api.models.GifsApiResponse
import com.gramzin.gifless.data.model.GifData
import com.gramzin.gifless.data.storage.GifRemoteDataStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GifRemoteDataStorageImpl @Inject constructor(private val service: TenorGifsApi): GifRemoteDataStorage {
    private var nextPageInProgramming: String? = null
    private var nextPageInTop: String? = null

    override suspend fun getProgrammingGifs(): List<GifData> {
        val result = withContext(Dispatchers.IO) {
            service.getProgrammingGifs(nextPageInProgramming)
        }
        nextPageInProgramming = result.next

        return result.mapToGifData()
    }

    override suspend fun getTopGifs(): List<GifData> {
        val result = withContext(Dispatchers.IO) {
            service.getTopGifs(nextPageInTop)
        }
        nextPageInTop = result.next

        return result.mapToGifData()
    }
}

fun GifsApiResponse.mapToGifData(): List<GifData>{
    return results.map {
        GifData(
            url = it.media_formats.gif.url,
            description = it.content_description
        )
    }
}