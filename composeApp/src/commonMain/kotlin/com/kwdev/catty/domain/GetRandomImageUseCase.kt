package com.kwdev.catty.domain

interface GetRandomImageUseCase {
    suspend operator fun invoke(gif: Boolean): Result<String>
}
