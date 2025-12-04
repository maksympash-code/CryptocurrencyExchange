package com.example.cryptocurrencyexchange.data.remote

import com.example.cryptocurrencyexchange.data.remote.dto.TopListResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoCompareApi{
    @GET("data/top/totalvolfull")
    suspend fun getTopCoins(
        @Query("limit") limit: Int = 30,
        @Query("tsym") tsym: String = "USD",
    ): TopListResponseDto
}