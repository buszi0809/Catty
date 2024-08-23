package com.kwdev.catty.di

import com.kwdev.catty.Greeting
import org.koin.dsl.module

internal val ApplicationModule = module {
    factory { Greeting() }
}
