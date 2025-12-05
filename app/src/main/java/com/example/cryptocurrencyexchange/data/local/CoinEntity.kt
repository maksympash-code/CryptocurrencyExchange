package com.example.cryptocurrencyexchange.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coins")
data class CoinEntity(
    @PrimaryKey
    val symbol: String,
    val id: String, // "1182" з API
    val name: String,
    val priceUsd: Double,
    val dayLowUsd: Double,
    val dayHighUsd: Double,
    val imageUrl: String,
    val lastUpdate: Long,
) {
}