package com.example.cryptocurrencyexchange.domain.usecases

import com.example.cryptocurrencyexchange.domain.CoinDetails
import com.example.cryptocurrencyexchange.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class GetCoinDetailsUseCase(
    private val repository: Repository
) {
    operator fun invoke(id: String): Flow<CoinDetails> = repository.getCoinDetails(id)
}