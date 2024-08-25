package com.kwdev.catty.data.network

interface NetworkRepository {
    suspend fun getRandomImage(): Result<String>
}
