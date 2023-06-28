package com.gramzin.gifless.data.model

import com.gramzin.gifless.domain.models.Gif

data class GifData(
    val url: String,
    var description: String? = null
)

fun GifData.mapToDomain(): Gif{
    return Gif(
        description = description ?: "",
        gifURL = url
    )
}
