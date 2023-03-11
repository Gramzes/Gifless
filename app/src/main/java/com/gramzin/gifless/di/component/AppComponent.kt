package com.gramzin.gifless.di.component

import com.gramzin.gifless.di.module.AppModule
import com.gramzin.gifless.domain.repository.GifRepository
import com.gramzin.gifless.presentation.fragments.ProgrammingGifFragment
import com.gramzin.gifless.presentation.fragments.TopGifFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(programmingGifFragment: ProgrammingGifFragment)

    fun inject(programmingGifFragment: TopGifFragment)

    val getRepository: GifRepository
}