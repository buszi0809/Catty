package com.kwdev.catty

import com.kwdev.catty.di.initializeKoin

@Suppress("unused")
fun initialize() {
    initializeKoin {
        printLogger()
    }
}
