package com.kwdev.catty.data.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase.Builder
import org.koin.core.scope.Scope

actual fun Scope.getPlatformDatabaseBuilder(): Builder<CattyDatabase> {
    val context = get<Context>().applicationContext
    val fileName = context.getDatabasePath(CattyDatabase.NAME)
    return Room.databaseBuilder<CattyDatabase>(
        context = context,
        name = fileName.absolutePath,
    )
}
