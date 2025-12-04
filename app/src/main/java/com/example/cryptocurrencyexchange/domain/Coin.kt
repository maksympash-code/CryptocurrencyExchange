package com.example.cryptocurrencyexchange.domain

data class Coin(
    val id: String,
    val name: String,
    val symbol: String,
    val priceUsd: Double,
    val imageUrl: String,
    val lastUpdate: Long,
){

}