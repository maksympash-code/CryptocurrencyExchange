package com.example.cryptocurrencyexchange.presentation.coinslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cryptocurrencyexchange.domain.usecases.GetTopCoinsUseCase
import com.example.cryptocurrencyexchange.domain.usecases.RefreshTopCoinsUseCase

class CoinsListViewModelFactory(
    private val getTopCoinsUseCase: GetTopCoinsUseCase,
    private val refreshTopCoinsUseCase: RefreshTopCoinsUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CoinsListViewModel::class.java)) {
            return CoinsListViewModel(
                getTopCoins = getTopCoinsUseCase,
                refreshTopCoins = refreshTopCoinsUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: $modelClass")
    }
}