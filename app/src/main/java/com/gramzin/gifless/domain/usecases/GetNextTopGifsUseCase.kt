package com.gramzin.gifless.domain.usecases

import com.gramzin.gifless.common.Resource
import com.gramzin.gifless.domain.models.Gif
import com.gramzin.gifless.domain.repository.GifRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetNextTopGifsUseCase @Inject constructor(private val gifRepository: GifRepository) {
    operator fun invoke(): Flow<Resource<List<Gif>>> = flow{
        try {
            emit(Resource.Loading())
            val gifs = gifRepository.getTopGifs()
            emit(Resource.Success(gifs))
        }
        catch (ex: Exception){
            emit(Resource.Error(ex))
        }
    }
}