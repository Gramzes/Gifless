package com.gramzin.gifless.data.api.giphy_api.model

import com.squareup.moshi.Json

data class GifDto(
    @Json(name = "id")
    val id: String,
    @Json(name = "images")
    val images: ImagesDto,
    @Json(name = "title")
    val title: String
)
