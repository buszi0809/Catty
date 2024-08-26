package com.kwdev.catty.domain.impl

import com.kwdev.catty.data.network.NetworkRepository
import com.kwdev.catty.domain.GetRandomImageUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class GetRandomImageUseCaseImpl(
    private val networkRepository: NetworkRepository,
    private val dispatcher: CoroutineDispatcher,
) : GetRandomImageUseCase {

    override suspend fun invoke(gif: Boolean): Result<String> = withContext(dispatcher) {
        networkRepository.getRandomImage(gif)
    }
}
