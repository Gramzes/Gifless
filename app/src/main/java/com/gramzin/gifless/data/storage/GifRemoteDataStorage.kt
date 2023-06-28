package com.gramzin.gifless.data.storage

import com.gramzin.gifless.data.model.GifData


interface GifRemoteDataStorage {

    suspend fun getTopGifs() : List<GifData>

    suspend fun getProgrammingGifs() : List<GifData>

}