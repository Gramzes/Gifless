package com.gramzin.gifless.di.component

import com.gramzin.gifless.di.module.NetworkModule
import com.gramzin.gifless.di.module.ViewModelModule
import com.gramzin.gifless.domain.repository.GifRepository
import com.gramzin.gifless.presentation.fragments.ProgrammingGifFragment
import com.gramzin.gifless.presentation.fragments.TopGifFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(programmingGifFragment: ProgrammingGifFragment)

    fun inject(programmingGifFragment: TopGifFragment)
}