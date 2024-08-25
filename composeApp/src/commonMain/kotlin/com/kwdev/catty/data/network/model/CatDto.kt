package com.kwdev.catty.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CatDto(
    @SerialName("_id") val id: String,
)
