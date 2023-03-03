package com.gramzin.gifless.presentation.viewModel.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gramzin.gifless.MyApp
import com.gramzin.gifless.domain.useusecases.GetNextProgrammingGifsUseCase
import com.gramzin.gifless.domain.useusecases.GetNextTopGifsUseCase
import com.gramzin.gifless.presentation.viewModel.ProgrammingFragmentViewModel
import com.gramzin.gifless.presentation.viewModel.TopFragmentViewModel

class ViewModelFactory(private val context: Context): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = (context as MyApp).repository
        val viewModel = when(modelClass){
            TopFragmentViewModel::class.java -> TopFragmentViewModel(GetNextTopGifsUseCase(repository))
            else -> ProgrammingFragmentViewModel(GetNextProgrammingGifsUseCase(repository))
        }
        return viewModel as T
    }
}