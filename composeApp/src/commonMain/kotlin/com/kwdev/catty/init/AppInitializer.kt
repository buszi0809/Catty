package com.kwdev.catty.init

interface AppInitializer {
    fun init()

    companion object {
        fun initializeAll() {
            getAll().forEach { it.init() }
        }

        private fun getAll(): List<AppInitializer> = listOf(
            LoggerInitializer(), // TODO init only for debug
        )
    }
}
