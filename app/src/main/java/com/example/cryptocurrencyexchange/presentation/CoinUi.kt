package com.example.cryptocurrencyexchange.presentation

data class CoinUi(
    val id: String,
    val name: String,
    val symbol: String,
    val priceText: String,
    val dayRangeText: String,
    val lastUpdateText: String,
    val imageUrl: String,
)