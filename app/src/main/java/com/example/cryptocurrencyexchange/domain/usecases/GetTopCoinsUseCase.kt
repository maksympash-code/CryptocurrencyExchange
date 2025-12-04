package com.example.cryptocurrencyexchange.domain.usecases

import com.example.cryptocurrencyexchange.domain.Coin
import com.example.cryptocurrencyexchange.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class GetTopCoinsUseCase(private val repository: Repository) {
    operator fun invoke(): Flow<List<Coin>> = repository.getTopCoins()
}