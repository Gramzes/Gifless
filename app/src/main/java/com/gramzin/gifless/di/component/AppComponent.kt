package com.gramzin.gifless.di.component

import com.gramzin.gifless.di.module.AppModule
import com.gramzin.gifless.domain.repository.GifRepository
import com.gramzin.gifless.domain.useusecases.GetNextProgrammingGifsUseCase
import com.gramzin.gifless.domain.useusecases.GetNextTopGifsUseCase
import com.gramzin.gifless.presentation.ProgrammingGifFragment
import com.gramzin.gifless.presentation.TopGifFragment
import com.gramzin.gifless.presentation.viewModel.factory.ViewModelFactory
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(programmingGifFragment: ProgrammingGifFragment)

    fun inject(programmingGifFragment: TopGifFragment)

    val getRepository: GifRepository
}