package com.kwdev.catty.domain.impl

import com.kwdev.catty.domain.MarkImageAsFavoriteUseCase
import com.kwdev.catty.domain.model.LocalFavoriteCatImagesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class MarkImageAsFavoriteUseCaseImpl(
    private val localRepository: LocalFavoriteCatImagesRepository,
    private val dispatcher: CoroutineDispatcher,
) : MarkImageAsFavoriteUseCase {

    override suspend fun invoke(url: String) = withContext(dispatcher) {
        localRepository.insert(url)
    }
}
