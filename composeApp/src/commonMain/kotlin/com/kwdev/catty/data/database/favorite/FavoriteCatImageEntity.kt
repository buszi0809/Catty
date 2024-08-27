package com.kwdev.catty.data.database.favorite

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kwdev.catty.data.database.favorite.FavoriteCatImageEntity.Companion.TABLE_NAME
import com.kwdev.catty.domain.model.FavoriteCatImage

@Entity(tableName = TABLE_NAME)
data class FavoriteCatImageEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val imageUrl: String,
) {

    companion object {
        const val TABLE_NAME = "favorite_cat_images"

        fun FavoriteCatImage.toEntity() = FavoriteCatImageEntity(
            id = id,
            imageUrl = imageUrl,
        )

        fun FavoriteCatImageEntity.toModel() = FavoriteCatImage(
            id = id,
            imageUrl = imageUrl,
        )
    }
}
