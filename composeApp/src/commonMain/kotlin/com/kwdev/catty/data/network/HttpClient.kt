package com.kwdev.catty.data.network

import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpCallValidator
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

fun createHttpClient(params: ClientParams) = HttpClient {
    install(DefaultRequest) {
        url(params.baseUrl)
        accept(ContentType.Application.Json)
    }

    install(Logging) {
        logger = NapierLogger
        level = LogLevel.BODY
    }

    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
            isLenient = true
        })
    }

    install(HttpCallValidator) {
        validateResponse { response ->
            if (!response.status.isSuccess()) {
                throw Exception("Http error ${response.status} - ${response.bodyAsText()}")
            }
        }
    }
}

private object NapierLogger : Logger {

    override fun log(message: String) {
        Napier.d(message = message, tag = "KtorHttpClient")
    }
}
