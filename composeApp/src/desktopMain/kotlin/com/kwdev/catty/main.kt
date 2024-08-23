package com.kwdev.catty

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.kwdev.catty.di.initializeKoin

fun main() {
    initializeKoin {
        printLogger()
    }

    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Catty",
        ) {
            App()
        }
    }
}
