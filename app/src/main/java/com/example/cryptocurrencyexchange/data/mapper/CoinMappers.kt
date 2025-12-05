package com.example.cryptocurrencyexchange.data.mapper

import com.example.cryptocurrencyexchange.data.local.CoinEntity
import com.example.cryptocurrencyexchange.data.remote.dto.CoinDataDto
import com.example.cryptocurrencyexchange.domain.Coin
import com.example.cryptocurrencyexchange.domain.CoinDetails

private const val IMAGE_BASE_URL = "https://www.cryptocompare.com"

fun CoinDataDto.toEntity(): CoinEntity? {
    val info = coinInfo ?: return null
    val usd = raw?.usd ?: return null

    val symbol = info.name ?: return null
    val id = info.id ?: symbol
    val fullName = info.fullName ?: symbol
    val imgPart = info.imageUrl ?: ""

    val price = usd.price ?: return null
    val low = usd.low24h ?: price
    val high = usd.high24h ?: price
    val last = usd.lastUpdateTs ?: 0L

    return CoinEntity(
        symbol = symbol,
        id = id,
        name = fullName,
        priceUsd = price,
        dayLowUsd = low,
        dayHighUsd = high,
        imageUrl = IMAGE_BASE_URL + imgPart,
        lastUpdate = last
    )
}

fun CoinEntity.toDomainCoin(): Coin =
    Coin(
        id = id,
        name = name,
        symbol = symbol,
        priceUsd = priceUsd,
        imageUrl = imageUrl,
        lastUpdate = lastUpdate
    )

fun CoinEntity.toDomainDetails(): CoinDetails =
    CoinDetails(
        id = id,
        name = name,
        symbol = symbol,
        priceUsd = priceUsd,
        dayLowUsd = dayLowUsd,
        dayHighUsd = dayHighUsd,
        imageUrl = imageUrl,
        lastUpdate = lastUpdate
    )