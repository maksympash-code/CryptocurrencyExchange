package com.example.cryptocurrencyexchange.presentation.coinslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cryptocurrencyexchange.domain.usecases.GetTopCoinsUseCase
import com.example.cryptocurrencyexchange.domain.usecases.RefreshTopCoinsUseCase

class CoinsListViewModelFactory(
    private val getTopCoins: GetTopCoinsUseCase,
    private val refreshTopCoins: RefreshTopCoinsUseCase
): ViewModelProvider.Factory {
    @Suppress
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CoinsListViewModel::class.java)){
            return CoinsListViewModel(getTopCoins, refreshTopCoins) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}