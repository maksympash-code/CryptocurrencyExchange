package com.example.cryptocurrencyexchange.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TopListResponseDto(
    @SerializedName("Data")
    val data: List<CoinDataDto>?
)

data class CoinDataDto(
    @SerializedName("CoinInfo")
    val coinInfo: CoinInfoDto?,

    @SerializedName("RAW")
    val raw: RawDto?
)

data class CoinInfoDto(
    @SerializedName("Id")
    val id: String?,

    @SerializedName("Name")
    val name: String?,      // "BTC"

    @SerializedName("FullName")
    val fullName: String?,  // "Bitcoin"

    @SerializedName("ImageUrl")
    val imageUrl: String?   // "/media/37746251/btc.png"
)

data class RawDto(
    @SerializedName("USD")
    val usd: UsdRawDto?
)

data class UsdRawDto(
    @SerializedName("PRICE")
    val price: Double?,

    @SerializedName("LOW24HOUR")
    val low24h: Double?,

    @SerializedName("HIGH24HOUR")
    val high24h: Double?,

    @SerializedName("LASTUPDATE")
    val lastUpdateTs: Long? // unix time
)