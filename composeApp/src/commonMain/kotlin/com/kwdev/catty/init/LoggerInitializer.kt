package com.kwdev.catty.init

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

class LoggerInitializer : AppInitializer {

    override fun init() {
        Napier.base(DebugAntilog())
    }
}
