package com.gramzin.gifless.data.api.tenor_api.models

import com.gramzin.gifless.domain.models.Gif as DomainGif

data class GifsApiResponse(val results: List<Result>, val next: String)

data class Result(val media_formats: Media, val content_description: String)

data class Media(val gif: Gif)

data class Gif(val url: String, var description: String? = null)

fun Gif.toDomain(): DomainGif {
    return DomainGif(description ?: "", url)
}