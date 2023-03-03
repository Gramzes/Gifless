package com.gramzin.gifless.data.api

import com.gramzin.gifless.data.api.models.GifsApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GifsApi {
    companion object{
        private const val KEY = "AIzaSyBo2tub7ainYK9S5lEJ2-hCHJNo1wTzKXI"
    }

    @GET("v2/featured?key=$KEY&limit=8&media_filter=gif")
    suspend fun getTopGifs(@Query("pos") pos: String?) : GifsApiResponse

    @GET("v2/search?q=programming&key=$KEY&limit=8&media_filter=gif")
    suspend fun getProgrammingGifs(@Query("pos") pos: String?) : GifsApiResponse
}