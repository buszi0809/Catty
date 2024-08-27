package com.kwdev.catty.data.database

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import org.koin.core.module.Module
import org.koin.core.scope.Scope

expect fun Scope.getPlatformDatabaseBuilder(): RoomDatabase.Builder<CattyDatabase>

fun Module.databaseCreatorDefinition() {
    single {
        getPlatformDatabaseBuilder()
            .fallbackToDestructiveMigrationOnDowngrade(dropAllTables = true)
            .setDriver(BundledSQLiteDriver())
            .build()
    }
}
