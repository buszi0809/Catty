package com.kwdev.catty

import androidx.compose.runtime.Composable
import com.kwdev.catty.ui.common.ProvideKamelConfig
import com.kwdev.catty.ui.navigation.CattyNavHost
import com.kwdev.catty.ui.theme.CattyTheme
import org.koin.compose.KoinContext

@Composable
fun App() {
    KoinContext {
        CattyTheme {
            ProvideKamelConfig {
                CattyNavHost()
            }
        }
    }
}
