package com.kwdev.catty.domain.model.di

import com.kwdev.catty.data.database.CattyDatabase
import com.kwdev.catty.data.database.databaseCreatorDefinition
import com.kwdev.catty.domain.model.LocalFavoriteCatImagesRepository
import com.kwdev.catty.domain.model.impl.LocalFavoriteCatImagesRepositoryImpl
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val DatabaseModule = module {
    databaseCreatorDefinition()

    factory { get<CattyDatabase>().favoriteCatImageDao() }
    factoryOf(::LocalFavoriteCatImagesRepositoryImpl) bind LocalFavoriteCatImagesRepository::class
}
