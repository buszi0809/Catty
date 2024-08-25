package com.kwdev.catty

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.kwdev.catty.di.initializeKoin
import com.kwdev.catty.init.AppInitializer

fun main() {
    initializeKoin {
        printLogger()
    }

    AppInitializer.initializeAll()

    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Catty",
        ) {
            App()
        }
    }
}
