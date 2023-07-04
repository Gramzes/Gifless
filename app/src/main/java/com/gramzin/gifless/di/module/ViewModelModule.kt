package com.gramzin.gifless.di.module

import androidx.lifecycle.ViewModel
import com.gramzin.gifless.di.mapkeys.ViewModelKey
import com.gramzin.gifless.presentation.viewModel.ProgrammingFragmentViewModel
import com.gramzin.gifless.presentation.viewModel.TopFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(ProgrammingFragmentViewModel::class)
    @Binds
    fun bindProgrammingViewModel(viewModel: ProgrammingFragmentViewModel): ViewModel

    @IntoMap
    @ViewModelKey(TopFragmentViewModel::class)
    @Binds
    fun bindTopViewModel(viewModel: TopFragmentViewModel): ViewModel
}