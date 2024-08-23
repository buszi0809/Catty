package com.kwdev.catty.di

import com.kwdev.catty.Greeting
import com.kwdev.catty.ui.UiModule
import org.koin.dsl.module

internal val ApplicationModule = module {
    factory { Greeting() }
    includes(
        UiModule,
    )
}
