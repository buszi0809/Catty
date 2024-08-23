package com.kwdev.catty.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

internal fun initializeKoin(additionalSetup: KoinAppDeclaration) {
    startKoin {
        additionalSetup()
        modules(ApplicationModule)
    }
}
