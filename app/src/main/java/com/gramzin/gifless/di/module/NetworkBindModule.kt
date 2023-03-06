package com.gramzin.gifless.di.module

import com.gramzin.gifless.data.repository.GifRepositoryImpl
import com.gramzin.gifless.data.api.GifRemoteDataStorageImpl
import com.gramzin.gifless.data.storage.GifRemoteDataStorage
import com.gramzin.gifless.domain.repository.GifRepository
import dagger.Binds
import dagger.Module

@Module
interface NetworkBindModule{

    @Binds
    fun GifRepositoryImpl_to_GifRepository(
        gifRepositoryImpl: GifRepositoryImpl
    ): GifRepository

    @Binds
    fun GifRemoteDataStorageImpl_to_GifRemoteDataStorage(
        gifRemoteDataStorageImpl: GifRemoteDataStorageImpl
    ): GifRemoteDataStorage
}