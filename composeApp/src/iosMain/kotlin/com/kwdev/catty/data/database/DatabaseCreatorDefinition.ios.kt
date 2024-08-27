package com.kwdev.catty.data.database

import androidx.room.Room
import androidx.room.RoomDatabase.Builder
import kotlinx.cinterop.ExperimentalForeignApi
import org.koin.core.scope.Scope
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

@OptIn(ExperimentalForeignApi::class)
actual fun Scope.getPlatformDatabaseBuilder(): Builder<CattyDatabase> {
    val dbFile = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null,
    )
        ?.path + "/" + CattyDatabase.NAME

    return Room.databaseBuilder<CattyDatabase>(name = dbFile)
}
