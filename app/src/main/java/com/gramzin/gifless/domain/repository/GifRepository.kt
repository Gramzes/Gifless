package com.gramzin.gifless.domain.repository

import com.gramzin.gifless.domain.models.Gif

interface GifRepository {
    suspend fun getProgrammingGifs() : List<Gif>

    suspend fun getTopGifs() : List<Gif>
}