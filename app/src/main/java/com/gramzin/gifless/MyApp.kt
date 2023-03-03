package com.gramzin.gifless

import android.app.Application
import com.gramzin.gifless.data.GifRepositoryImpl
import com.gramzin.gifless.data.api.NetworkModule
import com.gramzin.gifless.data.api.GifRemoteDataStorageImpl
import com.gramzin.gifless.domain.repository.GifRepository

class MyApp: Application() {
    lateinit var repository: GifRepository

    override fun onCreate() {
        super.onCreate()
        val remoteStorage = GifRemoteDataStorageImpl(NetworkModule.gifsApiService)

        repository = GifRepositoryImpl(remoteStorage)
    }
}