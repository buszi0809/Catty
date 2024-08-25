package com.kwdev.catty.data.network.impl

import com.kwdev.catty.data.network.ClientParams
import com.kwdev.catty.data.network.NetworkRepository
import com.kwdev.catty.data.network.model.CatDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.http.ContentType.Application

internal class NetworkRepositoryImpl(
    private val httpClient: HttpClient,
    private val clientParams: ClientParams,
) : NetworkRepository {

    override suspend fun getRandomImage(): Result<String> = runCatching {
        httpClient.get("cat") {
            accept(Application.Json)
        }
            .body<CatDto>()
            .let { catDto ->
                clientParams.baseUrl + "cat/" + catDto.id
            }
    }
}
