package com.kwdev.catty.init

import coil3.ImageLoader
import coil3.SingletonImageLoader
import coil3.util.Logger
import coil3.util.Logger.Level
import io.github.aakira.napier.Napier

class ImageLoaderInitializer : AppInitializer {

    override fun init() {
        SingletonImageLoader.setSafe { context ->
            ImageLoader.Builder(context)
                .logger(NapierLogger())
                .build()
        }
    }

    private class NapierLogger : Logger {
        override var minLevel: Level = Level.Info

        override fun log(tag: String, level: Level, message: String?, throwable: Throwable?) {
            Napier.e(
                message = "Failed to load image",
                throwable = throwable,
                tag = "Coil3",
            )
        }
    }
}
