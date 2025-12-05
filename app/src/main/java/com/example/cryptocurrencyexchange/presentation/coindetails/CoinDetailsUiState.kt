package com.example.cryptocurrencyexchange.presentation.coindetails

import com.example.cryptocurrencyexchange.presentation.CoinUi

data class CoinDetailsUiState (
    val isLoading: Boolean = true,
    val coin: CoinUi? = null,
)