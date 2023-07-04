package com.gramzin.gifless.di.module

import com.gramzin.gifless.common.Utils
import com.gramzin.gifless.data.api.giphy_api.GiphyGifsApi
import com.gramzin.gifless.data.api.tenor_api.TenorGifsApi
import com.gramzin.gifless.di.qualifiers.GiphyApi
import com.gramzin.gifless.di.qualifiers.TenorApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Module(includes = [NetworkBindModule::class])
object NetworkModule{

    @GiphyApi
    @Provides
    fun provideGiphyBaseUrl(): String = Utils.GIPHY_BASE_URL

    @TenorApi
    @Provides
    fun provideTenorBaseUrl(): String = Utils.TENOR_BASE_URL

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

    @GiphyApi
    @Provides
    fun provideGiphyRetrofit(@GiphyApi baseUrl: String, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @TenorApi
    @Provides
    fun provideTenorRetrofit(@TenorApi baseUrl: String, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideTenorGifsApi(@TenorApi retrofit: Retrofit): TenorGifsApi {
        return retrofit.create(TenorGifsApi::class.java)
    }

    @Singleton
    @Provides
    fun provideGiphyGifsApi(@GiphyApi retrofit: Retrofit): GiphyGifsApi {
        return retrofit.create(GiphyGifsApi::class.java)
    }
}