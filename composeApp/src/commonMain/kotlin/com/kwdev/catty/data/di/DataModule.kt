package com.kwdev.catty.data.di

import com.kwdev.catty.data.network.di.NetworkModule
import com.kwdev.catty.domain.model.di.DatabaseModule
import org.koin.dsl.module

val DataModule = module {
    includes(
        NetworkModule,
        DatabaseModule,
    )
}
