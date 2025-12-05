package com.example.cryptocurrencyexchange.data.repository

import com.example.cryptocurrencyexchange.data.local.CryptoDatabase
import com.example.cryptocurrencyexchange.data.local.dao.CoinDao
import com.example.cryptocurrencyexchange.data.mapper.toDomainCoin
import com.example.cryptocurrencyexchange.data.mapper.toDomainDetails
import com.example.cryptocurrencyexchange.data.mapper.toEntity
import com.example.cryptocurrencyexchange.data.remote.CryptoCompareApi
import com.example.cryptocurrencyexchange.domain.Coin
import com.example.cryptocurrencyexchange.domain.CoinDetails
import com.example.cryptocurrencyexchange.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RepositoryImpl(
    private val api: CryptoCompareApi,
    private val dao: CoinDao,
): Repository {
    override fun getTopCoins(): Flow<List<Coin>> {
        return dao.getCoins().map {
            entity -> entity.map { it.toDomainCoin() }
        }
    }

    override fun getCoinDetails(symbol: String): Flow<CoinDetails> {
        return dao.getCoin(symbol).map {
            entity -> entity.toDomainDetails()
        }
    }

    override suspend fun refreshTopCoins() {
        val response = api.getTopCoins(30, "USD")

        val entities = response.data
            .orEmpty()
            .mapNotNull { dto -> dto.toEntity() }

        dao.clearAll()
        dao.insertAll(entities)
    }
}