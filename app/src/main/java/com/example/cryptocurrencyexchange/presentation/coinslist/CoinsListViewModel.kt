package com.example.cryptocurrencyexchange.presentation.coinslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencyexchange.domain.Coin
import com.example.cryptocurrencyexchange.domain.usecases.GetTopCoinsUseCase
import com.example.cryptocurrencyexchange.domain.usecases.RefreshTopCoinsUseCase
import com.example.cryptocurrencyexchange.presentation.CoinUi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.Locale

class CoinsListViewModel(
    private val getTopCoins: GetTopCoinsUseCase,
    private val refreshTopCoins: RefreshTopCoinsUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData(CoinsListUiState())
    val uiState: LiveData<CoinsListUiState> = _uiState

    private val priceFormatter = DecimalFormat("#,##0.00")
    private val timeFormatter = SimpleDateFormat("HH:mm", Locale.getDefault())

    init {
        observeCoins()
        refresh()
    }

    private fun observeCoins() {
        viewModelScope.launch {
            getTopCoins().collectLatest { coins ->
                val uiList = coins.map { it.toUi() }
                val current = _uiState.value ?: CoinsListUiState()
                _uiState.value = current.copy(coins = uiList)
            }
        }
    }

    fun refresh() {
        val cur = _uiState.value ?: CoinsListUiState()
        if (cur.isLoading) return

        viewModelScope.launch {
            _uiState.value = cur.copy(isLoading = true)
            refreshTopCoins()
            val updated = _uiState.value ?: cur
            _uiState.value = updated.copy(isLoading = false)
        }
    }

    private fun Coin.toUi(): CoinUi {
        val priceText = "Price: ${priceFormatter.format(priceUsd)} USD"

        val dayRangeText = "24h: -"

        val lastUpdateText =
            if (lastUpdate != 0L) {
                "Last update: ${timeFormatter.format(lastUpdate * 1000)}"
            } else {
                "Last update: -"
            }

        return CoinUi(
            id = id,
            name = name,
            symbol = symbol,
            priceText = priceText,
            dayRangeText = dayRangeText,
            lastUpdateText = lastUpdateText,
            imageUrl = imageUrl
        )
    }
}