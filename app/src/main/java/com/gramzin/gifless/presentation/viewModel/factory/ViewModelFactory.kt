package com.gramzin.gifless.presentation.viewModel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gramzin.gifless.domain.usecases.GetNextProgrammingGifsUseCase
import com.gramzin.gifless.domain.usecases.GetNextTopGifsUseCase
import com.gramzin.gifless.presentation.viewModel.ProgrammingFragmentViewModel
import com.gramzin.gifless.presentation.viewModel.TopFragmentViewModel
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory @Inject constructor(
    private val viewModelProviders: @JvmSuppressWildcards Map<Class<out ViewModel>, Provider<ViewModel>>
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModelProviders[modelClass]?.get() as T
    }
}