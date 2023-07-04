package com.gramzin.gifless.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gramzin.gifless.common.Resource
import com.gramzin.gifless.domain.models.Gif
import com.gramzin.gifless.domain.usecases.GetNextTopGifsUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class TopFragmentViewModel @Inject constructor(
    private val nextTopGifsUseCase: GetNextTopGifsUseCase
    ): ViewModel() {
    private var downloadedGifs = mutableListOf<Gif>()
    private var currentGifIndex = -1
    private var _currentGif = MutableLiveData<Gif>()
    private val _isErrorMessage = MutableLiveData(false)
    private var _isPossibleGoBack = MutableLiveData(false)
    val currentGif: LiveData<Gif> = _currentGif
    val isPossibleGoBack: LiveData<Boolean> = _isPossibleGoBack
    val isErrorMessage: LiveData<Boolean> = _isErrorMessage


    fun nextGif(){
        if (currentGifIndex>=downloadedGifs.size - 1) {
            nextTopGifsUseCase().onEach{
                when(it){
                    is Resource.Success ->{
                        it.data?.let { gifs ->
                            if(_isErrorMessage.value!!){
                                _isErrorMessage.value = false
                            }
                            currentGifIndex++
                            downloadedGifs.addAll(gifs)
                            _currentGif.value = downloadedGifs[currentGifIndex]
                            _isPossibleGoBack.value = currentGifIndex > 0
                        }
                    }
                    is Resource.Loading -> {}
                    else -> {
                        _isErrorMessage.value = true
                    }
                }
            }.launchIn(viewModelScope)
        }
        else{
            currentGifIndex++
            _currentGif.value = downloadedGifs[currentGifIndex]
            _isPossibleGoBack.value = currentGifIndex > 0
        }
    }

    fun previousGif(){
        if(currentGifIndex>0){
            _currentGif.value = downloadedGifs[--currentGifIndex]
        }
        if(currentGifIndex<=0){
            _isPossibleGoBack.value = false
        }
    }
}