package com.kwdev.catty.data.network

import io.github.aakira.napier.Napier
import io.ktor.client.plugins.logging.Logger

class NapierKtorLogger(private val tag: String) : Logger {

    override fun log(message: String) {
        Napier.d(message = message, tag = tag)
    }
}
