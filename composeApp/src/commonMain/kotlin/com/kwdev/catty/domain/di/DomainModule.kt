package com.kwdev.catty.domain.di

import com.kwdev.catty.domain.GetRandomImageUseCase
import com.kwdev.catty.domain.MarkImageAsFavoriteUseCase
import com.kwdev.catty.domain.impl.GetRandomImageUseCaseImpl
import com.kwdev.catty.domain.impl.MarkImageAsFavoriteUseCaseImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val DomainModule = module {
    factory { Dispatchers.IO }

    factoryOf(::GetRandomImageUseCaseImpl) bind GetRandomImageUseCase::class
    factoryOf(::MarkImageAsFavoriteUseCaseImpl) bind MarkImageAsFavoriteUseCase::class
}
