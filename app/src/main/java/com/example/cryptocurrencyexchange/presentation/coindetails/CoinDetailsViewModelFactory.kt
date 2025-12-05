package com.example.cryptocurrencyexchange.presentation.coindetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cryptocurrencyexchange.domain.usecases.GetCoinDetailsUseCase

class CoinDetailsViewModelFactory(
    private val coinId: String,
    private val getCoinDetailsUseCase: GetCoinDetailsUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CoinDetailsViewModel::class.java)) {
            return CoinDetailsViewModel(
                coinId = coinId,
                getCoinDetailsUseCase = getCoinDetailsUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class $modelClass")
    }
}