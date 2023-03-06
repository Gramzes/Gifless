package com.gramzin.gifless.presentation.viewModel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gramzin.gifless.domain.useusecases.GetNextProgrammingGifsUseCase
import com.gramzin.gifless.domain.useusecases.GetNextTopGifsUseCase
import com.gramzin.gifless.presentation.viewModel.ProgrammingFragmentViewModel
import com.gramzin.gifless.presentation.viewModel.TopFragmentViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ViewModelFactory @Inject constructor(
    private val getNextTopGifsUseCase: GetNextTopGifsUseCase,
    private val getNextProgrammingGifsUseCase: GetNextProgrammingGifsUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = when(modelClass){
            TopFragmentViewModel::class.java -> TopFragmentViewModel(getNextTopGifsUseCase)
            else -> ProgrammingFragmentViewModel(getNextProgrammingGifsUseCase)
        }
        return viewModel as T
    }
}