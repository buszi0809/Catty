package com.kwdev.catty.domain.model.impl

import com.kwdev.catty.data.database.favorite.FavoriteCatImageEntity
import com.kwdev.catty.data.database.favorite.FavoriteCatImageEntity.Companion.toEntity
import com.kwdev.catty.data.database.favorite.FavoriteCatImageEntity.Companion.toModel
import com.kwdev.catty.domain.model.FavoriteCatImage
import com.kwdev.catty.domain.model.FavoriteCatImageDao
import com.kwdev.catty.domain.model.LocalFavoriteCatImagesRepository

class LocalFavoriteCatImagesRepositoryImpl(
    private val dao: FavoriteCatImageDao,
) : LocalFavoriteCatImagesRepository {

    override suspend fun insert(url: String) {
        dao.insert(FavoriteCatImageEntity(imageUrl = url))
    }

    override suspend fun getAll(): List<FavoriteCatImage> =
        dao.getAll().map { it.toModel() }

    override suspend fun delete(model: FavoriteCatImage) {
        dao.delete(model.toEntity())
    }
}
