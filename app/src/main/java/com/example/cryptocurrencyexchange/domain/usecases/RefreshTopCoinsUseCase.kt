package com.example.cryptocurrencyexchange.domain.usecases

import com.example.cryptocurrencyexchange.domain.repository.Repository

class RefreshTopCoinsUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke() {
        repository.refreshTopCoins()
    }
}