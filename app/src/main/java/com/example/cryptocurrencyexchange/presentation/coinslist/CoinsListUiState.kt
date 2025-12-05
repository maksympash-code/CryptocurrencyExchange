package com.example.cryptocurrencyexchange.presentation.coinslist

import com.example.cryptocurrencyexchange.presentation.CoinUi

data class CoinsListUiState(
    val isLoading: Boolean = false,
    val coins: List<CoinUi> = emptyList(),
    val error: String? = null
)