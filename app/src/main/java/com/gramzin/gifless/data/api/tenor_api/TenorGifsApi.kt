package com.gramzin.gifless.data.api.tenor_api

import com.gramzin.gifless.data.api.tenor_api.models.GifsApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TenorGifsApi {
    companion object{
        private const val KEY = "AIzaSyBo2tub7ainYK9S5lEJ2-hCHJNo1wTzKXI"
    }

    @GET("v2/featured?key=$KEY&limit=8&media_filter=gif")
    suspend fun getTopGifs(@Query("pos") pos: String?) : GifsApiResponse

    @GET("v2/search?q=programming&key=$KEY&limit=8&media_filter=gif")
    suspend fun getProgrammingGifs(@Query("pos") pos: String?) : GifsApiResponse
}