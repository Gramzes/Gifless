package com.gramzin.gifless.data.api.giphy_api.model

import com.squareup.moshi.Json

data class ImagesDto(
    @Json(name = "original")
    val original: OriginalImageDto
)
