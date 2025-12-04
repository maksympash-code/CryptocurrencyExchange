package com.example.cryptocurrencyexchange.domain.repository

import com.example.cryptocurrencyexchange.domain.Coin
import com.example.cryptocurrencyexchange.domain.CoinDetails
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getTopCoins(): Flow<List<Coin>>
    fun getCoinDetails(symbol: String): Flow<CoinDetails>
    suspend fun refreshTopCoins()
}