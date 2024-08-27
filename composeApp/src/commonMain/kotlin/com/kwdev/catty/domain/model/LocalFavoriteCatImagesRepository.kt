package com.kwdev.catty.domain.model

interface LocalFavoriteCatImagesRepository {

    suspend fun insert(url: String)

    suspend fun getAll(): List<FavoriteCatImage>

    suspend fun delete(model: FavoriteCatImage)
}
