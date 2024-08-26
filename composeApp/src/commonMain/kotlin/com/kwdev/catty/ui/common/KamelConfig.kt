package com.kwdev.catty.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.kwdev.catty.data.network.NapierKtorLogger
import io.kamel.core.config.DefaultCacheSize
import io.kamel.core.config.DefaultHttpCacheSize
import io.kamel.core.config.KamelConfig
import io.kamel.core.config.httpUrlFetcher
import io.kamel.core.config.stringMapper
import io.kamel.image.config.LocalKamelConfig
import io.kamel.image.config.animatedImageDecoder
import io.kamel.image.config.imageBitmapDecoder
import io.kamel.image.config.imageVectorDecoder
import io.kamel.image.config.svgDecoder
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging

private val CattyKamelConfig = KamelConfig {
    imageBitmapCacheSize = DefaultCacheSize
    imageVectorCacheSize = DefaultCacheSize
    svgCacheSize = DefaultCacheSize
    animatedImageCacheSize = DefaultCacheSize

    stringMapper()

    imageBitmapDecoder()
    imageVectorDecoder()
    svgDecoder()
    animatedImageDecoder()

    httpUrlFetcher {
        httpCache(DefaultHttpCacheSize)

        install(Logging) {
            level = LogLevel.INFO
            logger = NapierKtorLogger(tag = "KamelImageLoader")
        }
    }
}

@Composable
fun ProvideKamelConfig(content: @Composable () -> Unit) {
    CompositionLocalProvider(
        value = LocalKamelConfig provides CattyKamelConfig,
        content = content,
    )
}
