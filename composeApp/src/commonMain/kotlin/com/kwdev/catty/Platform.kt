package com.kwdev.catty

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
