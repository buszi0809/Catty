package com.kwdev.catty.di

import com.kwdev.catty.data.di.DataModule
import com.kwdev.catty.domain.di.DomainModule
import com.kwdev.catty.ui.di.UiModule
import org.koin.dsl.module

internal val ApplicationModule = module {
    includes(
        UiModule,
        DomainModule,
        DataModule,
    )
}
