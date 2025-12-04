package com.example.cryptocurrencyexchange.domain

data class CoinDetails(
    val id: String,
    val name: String,
    val symbol: String,
    val priceUsd: Double,
    val dayLowUsd: Double,   // min 24h
    val dayHighUsd: Double,  // max 24h
    val imageUrl: String,
    val lastUpdate: Long
){
}