package com.kwdev.catty.domain

interface GetRandomImageUseCase {
    suspend operator fun invoke(): Result<String>
}
