package com.gramzin.gifless.data.api.giphy_api.model

import com.squareup.moshi.Json

data class GifApiResponse(
    @Json(name = "data")
    val data: List<GifDto>
)
