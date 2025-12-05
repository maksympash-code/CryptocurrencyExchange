package com.example.cryptocurrencyexchange.data

import android.content.Context
import com.example.cryptocurrencyexchange.data.local.DatabaseProvider
import com.example.cryptocurrencyexchange.data.remote.CryptoCompareApi
import com.example.cryptocurrencyexchange.data.remote.CryptoCompareApiFactory
import com.example.cryptocurrencyexchange.data.repository.RepositoryImpl
import com.example.cryptocurrencyexchange.domain.repository.Repository
import com.example.cryptocurrencyexchange.domain.usecases.GetCoinDetailsUseCase
import com.example.cryptocurrencyexchange.domain.usecases.GetTopCoinsUseCase
import com.example.cryptocurrencyexchange.domain.usecases.RefreshTopCoinsUseCase

class ServiceLocator(private val appContext: Context) {
    private val api by lazy {
        CryptoCompareApiFactory.create()
    }

    private val db by lazy {
        DatabaseProvider.getDatabase(appContext)
    }

    private val coinDao by lazy {
        db.coinDao()
    }

    val repository: Repository by lazy {
        RepositoryImpl(api, coinDao)
    }

    val getTopCoinsUseCase by lazy {
        GetTopCoinsUseCase(repository)
    }

    val getCoinDetailsUseCase by lazy {
        GetCoinDetailsUseCase(repository)
    }

    val refreshTopCoinsUseCase by lazy {
        RefreshTopCoinsUseCase(repository)
    }

}