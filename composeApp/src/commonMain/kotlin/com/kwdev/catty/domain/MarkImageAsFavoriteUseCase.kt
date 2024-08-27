package com.kwdev.catty.domain

interface MarkImageAsFavoriteUseCase {

    suspend operator fun invoke(url: String)
}
