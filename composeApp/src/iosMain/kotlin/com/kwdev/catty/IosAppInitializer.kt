package com.kwdev.catty

import com.kwdev.catty.di.initializeKoin
import com.kwdev.catty.init.AppInitializer

@Suppress("unused")
fun initialize() {
    initializeKoin {
        printLogger()
    }

    AppInitializer.initializeAll()
}
