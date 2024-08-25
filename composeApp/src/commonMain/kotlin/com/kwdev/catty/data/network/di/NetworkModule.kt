package com.kwdev.catty.data.network.di

import com.kwdev.catty.data.network.ClientParams
import com.kwdev.catty.data.network.NetworkRepository
import com.kwdev.catty.data.network.createHttpClient
import com.kwdev.catty.data.network.impl.NetworkRepositoryImpl
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val NetworkModule = module {
    single { ClientParams(baseUrl = "https://cataas.com/") }
    single { createHttpClient(get()) }

    factoryOf(::NetworkRepositoryImpl) bind NetworkRepository::class
}
