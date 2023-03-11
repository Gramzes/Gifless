package com.gramzin.gifless.domain.usecases

import com.gramzin.gifless.common.Resource
import com.gramzin.gifless.domain.models.Gif
import com.gramzin.gifless.domain.repository.GifRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetNextProgrammingGifsUseCase @Inject constructor(private val gifRepository: GifRepository) {
    operator fun invoke(): Flow<Resource<List<Gif>>> = flow{
        try {
            emit(Resource.Loading())
            val gifs = gifRepository.getProgrammingGifs()
            emit(Resource.Success(gifs))
        }
        catch (ex: HttpException){
            emit(Resource.Error(ex))
        }
        catch (ex: IOException){
            emit(Resource.Error(ex))
        }
    }
}