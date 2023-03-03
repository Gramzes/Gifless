package com.gramzin.gifless.data.api.models

import com.squareup.moshi.Json

data class GifItem(
    @Json(name = "id")
    var id: Int,
    @Json(name = "description")
    var description: String,
    @Json(name = "gifURL")
    var gifURL: String)
