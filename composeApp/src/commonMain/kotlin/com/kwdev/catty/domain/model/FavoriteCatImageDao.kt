package com.kwdev.catty.domain.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.kwdev.catty.data.database.favorite.FavoriteCatImageEntity
import com.kwdev.catty.data.database.favorite.FavoriteCatImageEntity.Companion.TABLE_NAME

@Dao
interface FavoriteCatImageDao {

    @Insert
    suspend fun insert(entity: FavoriteCatImageEntity)

    @Query("SELECT * FROM $TABLE_NAME")
    suspend fun getAll(): List<FavoriteCatImageEntity>

    @Delete
    suspend fun delete(entity: FavoriteCatImageEntity)
}
