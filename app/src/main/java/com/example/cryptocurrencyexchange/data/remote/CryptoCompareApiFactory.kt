package com.example.cryptocurrencyexchange.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CryptoCompareApiFactory {
    private const val BASE_URL: String = "https://min-api.cryptocompare.com/"

    fun create(): CryptoCompareApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CryptoCompareApi::class.java)
    }
}