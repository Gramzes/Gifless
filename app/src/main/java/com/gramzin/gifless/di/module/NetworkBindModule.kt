package com.gramzin.gifless.di.module

import com.gramzin.gifless.data.api.giphy_api.GiphyRemoteDataStorageImpl
import com.gramzin.gifless.data.api.tenor_api.TenorRemoteDataStorageImpl
import com.gramzin.gifless.data.repository.GifRepositoryImpl
import com.gramzin.gifless.data.storage.GifRemoteDataStorage
import com.gramzin.gifless.di.qualifiers.GiphyApi
import com.gramzin.gifless.di.qualifiers.TenorApi
import com.gramzin.gifless.domain.repository.GifRepository
import dagger.Binds
import dagger.Module

@Module
interface NetworkBindModule{

    @Binds
    fun GifRepositoryImpl_to_GifRepository(
        gifRepositoryImpl: GifRepositoryImpl
    ): GifRepository

    @GiphyApi
    @Binds
    fun GiphyRemoteDataStorageImpl_to_GifRemoteDataStorage(
        giphyRemoteDataStorageImpl: GiphyRemoteDataStorageImpl
    ): GifRemoteDataStorage

    @TenorApi
    @Binds
    fun TenorRemoteDataStorageImpl_to_GifRemoteDataStorage(
        tenorRemoteDataStorageImpl: TenorRemoteDataStorageImpl
    ): GifRemoteDataStorage
}