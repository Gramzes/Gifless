package com.gramzin.gifless.data.api.giphy_api

import com.gramzin.gifless.data.api.giphy_api.model.GifApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyGifsApi {

    companion object{
        private const val KEY = "Pm2nBzwB5oCXBwMqw01R278u4BBRcIJd"
        const val LIMIT_COUNT = 8
    }

    @GET("trending?api_key=$KEY&limit=$LIMIT_COUNT")
    suspend fun getTopGifs(@Query("offset") offset: Int?) : GifApiResponse

    @GET("search?api_key=$KEY&limit=$LIMIT_COUNT&q=programming")
    suspend fun getProgrammingGifs(@Query("offset") offset: Int?) : GifApiResponse
}