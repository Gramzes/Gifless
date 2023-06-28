package com.gramzin.gifless.di.module

import com.gramzin.gifless.common.Utils
import com.gramzin.gifless.data.api.giphy_api.GiphyGifsApi
import com.gramzin.gifless.data.api.tenor_api.TenorGifsApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

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
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Utils.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideTenorGifsApi(retrofit: Retrofit): TenorGifsApi {
        return retrofit.create(TenorGifsApi::class.java)
    }

    @Singleton
    @Provides
    fun provideGiphyGifsApi(retrofit: Retrofit): GiphyGifsApi {
        return retrofit.create(GiphyGifsApi::class.java)
    }
}