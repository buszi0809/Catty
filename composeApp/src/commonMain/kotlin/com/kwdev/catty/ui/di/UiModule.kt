package com.kwdev.catty.ui.di

import com.kwdev.catty.ui.home.HomeViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal val UiModule = module {
    factoryOf(::HomeViewModel)
}
