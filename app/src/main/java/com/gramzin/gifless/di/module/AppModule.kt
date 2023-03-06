package com.gramzin.gifless.di.module

import com.gramzin.gifless.common.Utils
import com.gramzin.gifless.data.GifRepositoryImpl
import com.gramzin.gifless.data.api.GifRemoteDataStorageImpl
import com.gramzin.gifless.data.api.GifsApi
import com.gramzin.gifless.data.storage.GifRemoteDataStorage
import com.gramzin.gifless.domain.repository.GifRepository
import com.gramzin.gifless.domain.useusecases.GetNextTopGifsUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module(includes = [NetworkModule::class])
object AppModule

@Module(includes = [NetworkBindModule::class])
object NetworkModule{
    @Provides
    fun httpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit{
        return Retrofit.Builder()
            .baseUrl(Utils.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    fun provideGifsApi(retrofit: Retrofit): GifsApi{
        return retrofit.create(GifsApi::class.java)
    }
}

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