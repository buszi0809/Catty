package com.kwdev.catty.data.database

import androidx.room.Room
import androidx.room.RoomDatabase.Builder
import org.koin.core.scope.Scope
import java.io.File

actual fun Scope.getPlatformDatabaseBuilder(): Builder<CattyDatabase> {
    val dbFile = File(System.getProperty("java.io.tmpdir"), CattyDatabase.NAME).absolutePath
    return Room.databaseBuilder(name = dbFile)
}
