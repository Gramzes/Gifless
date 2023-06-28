package com.gramzin.gifless.data.api.giphy_api.model

import com.squareup.moshi.Json

data class OriginalImageDto(
    @Json(name = "url")
    val url: String
)
