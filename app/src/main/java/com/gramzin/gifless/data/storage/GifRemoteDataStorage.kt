package com.gramzin.gifless.data.storage

import com.gramzin.gifless.data.api.models.Gif

interface GifRemoteDataStorage {

    suspend fun getTopGifs() : List<Gif>

    suspend fun getProgrammingGifs() : List<Gif>

}