package com.kwdev.catty.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import com.kwdev.catty.data.database.favorite.FavoriteCatImageEntity
import com.kwdev.catty.domain.model.FavoriteCatImageDao

@Database(
    version = 1,
    entities = [
        FavoriteCatImageEntity::class,
    ],
)
@ConstructedBy(CattyDatabaseConstructor::class)
abstract class CattyDatabase : RoomDatabase() {

    abstract fun favoriteCatImageDao(): FavoriteCatImageDao

    companion object {
        const val NAME = "catty.db"
    }
}
