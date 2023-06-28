package com.gramzin.gifless.data.repository

import com.gramzin.gifless.data.api.tenor_api.models.toDomain
import com.gramzin.gifless.data.model.mapToDomain
import com.gramzin.gifless.data.storage.GifRemoteDataStorage
import com.gramzin.gifless.domain.models.Gif
import com.gramzin.gifless.domain.repository.GifRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GifRepositoryImpl @Inject constructor(private val remoteDataStorage: GifRemoteDataStorage): GifRepository {

    override suspend fun getProgrammingGifs(): List<Gif> {
        val result = remoteDataStorage.getProgrammingGifs()
        return result.map { it.mapToDomain() }
    }

    override suspend fun getTopGifs(): List<Gif> {
        val result = remoteDataStorage.getTopGifs()
        return result.map { it.mapToDomain() }
    }
}