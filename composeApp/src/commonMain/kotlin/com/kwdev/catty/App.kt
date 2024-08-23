package com.kwdev.catty

import androidx.compose.runtime.Composable
import com.kwdev.catty.ui.home.HomeScreen
import com.kwdev.catty.ui.theme.CattyTheme
import org.koin.compose.KoinContext

@Composable
fun App() {
    KoinContext {
        CattyTheme {
            HomeScreen()
        }
    }
}
